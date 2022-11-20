package com.example.playlistmaker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchAdapter(
    private val tracks: List<Track>
): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

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
                .into(imagePoster)
            val template = view.context.getString(R.string.title_and_time)
            artistNameAndTime.text = String.format(template,track.artistName, track.trackTime)

        }
    }

}
