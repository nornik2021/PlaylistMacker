package com.example.playlistmaker

import android.annotation.SuppressLint
import android.content.Context
import retrofit2.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

class ActivitySearch : AppCompatActivity() {

    lateinit var textInput: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var networkError: LinearLayout
    lateinit var notFound: LinearLayout
    lateinit var adapterTracks: AdapterTracks
    lateinit var buttonRefresh: Button
    lateinit var textInputLayout: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        this.installView()
        installAdapter()

        buttonRefresh.setOnClickListener {
            makeAllInvisible()
            sendARequest()
        }

        val clearButton = findViewById<ImageView>(R.id.clear)
        textInput.requestFocus()
        hideKeyboard(textInput)
        textInput.setOnEditorActionListener { _, actionId, _ ->
            makeAllInvisible()
            if (actionId == EditorInfo.IME_ACTION_DONE && textInput.text.isNotEmpty()) {
                sendARequest()
                true
            } else false
        }

        textInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                clearButton.visibility = View.GONE
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                clearButton.visibility = View.VISIBLE
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        clearButton.setOnClickListener {
            textInput.text.clear()
            hideKeyboard(textInput)
            makeAllInvisible()
            clearButton.visibility = View.GONE
        }
    }

    private fun installAdapter() {
        adapterTracks = AdapterTracks()
        recyclerView.adapter = adapterTracks
    }

    private fun installView() {
        recyclerView = findViewById(R.id.search_track_list)
        textInput = findViewById(R.id.search_input_text)
        networkError = findViewById(R.id.search_request_status_image)
        notFound = findViewById(R.id.ll_nothing_found)
        buttonRefresh = findViewById(R.id.btn_update)
        textInputLayout = findViewById(R.id.til_search)
    }

    private fun sendARequest() {
        ApiURL.apiServ.search(textInput.text.toString())
            .enqueue(object : Callback<ApiResponseApp> {
                override fun onResponse(
                    call: Call<ApiResponseApp>,
                    response: Response<ApiResponseApp>
                ) {
                    when {
                        response.code() == 200 -> {
                            if (response.body()?.resultCount != 0) {
                                val convert = Convert()
                                response.body()
                                    ?.results
                                    ?.map { convert.convert(it) }
                                    ?.apply { installScreen(SearchScreenStatus.Result(this)) }

                            } else {
                                installScreen(SearchScreenStatus.NothingFound)
                            }
                        }
                        response.code() in 400..599 -> {
                            installScreen(SearchScreenStatus.NetworkProblem)
                        }
                    }
                    Log.d("TAGS", "${response.body()}")
                }

                override fun onFailure(call: Call<ApiResponseApp>, t: Throwable) {
                    installScreen(SearchScreenStatus.NetworkProblem)
                    Log.d("TAGS", "${t.stackTrace}")
                }
            })
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        private val EDIT_TEXT_KEY = "EDIT_TEXT_KEY"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val editTextString = textInput.text.toString()
        outState.putString(EDIT_TEXT_KEY, editTextString)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val editTextString = savedInstanceState.getString(EDIT_TEXT_KEY, "")
        textInput.setText(editTextString, TextView.BufferType.EDITABLE)
    }

    private fun installScreen(state: SearchScreenStatus) {
        makeAllInvisible()
        when (state) {
            is SearchScreenStatus.Result -> {
                recyclerView.visibility = View.VISIBLE
                adapterTracks.setTrackList(state.list)
            }
            is SearchScreenStatus.NetworkProblem -> {
                networkError.visibility = View.VISIBLE
            }
            is SearchScreenStatus.NothingFound -> {
                notFound.visibility = View.VISIBLE
            }
        }
    }

    private fun makeAllInvisible() {
        recyclerView.visibility = View.GONE
        networkError.visibility = View.GONE
        notFound.visibility = View.GONE
    }
}









