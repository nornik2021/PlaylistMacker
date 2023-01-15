package com.example.playlistmaker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.text.SimpleDateFormat
import java.util.*

class SearchAdapter(
    private var tracks: List<Track>
): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setTrackList(tracks: List<Track>) {
        this.tracks = tracks
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.group_card, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    class SearchViewHolder(private val view: View):RecyclerView.ViewHolder(view) {

        private val imagePoster: ImageView
        private val trackName: TextView
        private val artistNameAndTime: TextView


        init {
            imagePoster = view.findViewById(R.id.imagePoster)
            trackName = view.findViewById(R.id.trackName)
            artistNameAndTime = view.findViewById(R.id.artistNameAndTime)

        }

        fun bind(track: Track) {
            trackName.text = track.trackName
            Glide.with(view.context)
                .load(track.artworkUrl100)
                .centerCrop()
                .transform(RoundedCorners(2))
                // .placeholder(R.drawable.ic_placeholder)
                .into(imagePoster)
            val template = view.context.getString(R.string.title_and_time)
            artistNameAndTime.text = String.format(template,track.artistName, track.trackTimeMillis)
            convertTime(artistNameAndTime.toString())

        }
        private fun convertTime(trackime: String): String =
            SimpleDateFormat("mm:ss", Locale.getDefault()).format(trackime.toLong())
    }

}


