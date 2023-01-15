package com.example.playlistmaker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

import java.text.SimpleDateFormat
import java.util.*

class AdapterTracks : RecyclerView.Adapter<AdapterTracks.ListViewHolder>() {

    private var tracks = listOf<Track>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTrackList(tracks: List<Track>) {
        this.tracks = tracks
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val photoTrack: ImageView = view.findViewById(R.id.imagePoster)
    private val trackArtistTime: TextView = view.findViewById(R.id.artistNameAndTime)
    private val trackName: TextView = view.findViewById(R.id.trackName)

        fun bind(track: Track) {
            trackName.text = track.trackName
            Glide.with(view.context)
                .load(track.artworkUrl100)
                .fitCenter()
                .transform(RoundedCorners(5))
                .placeholder(R.drawable.ic_placeholder)
                .into(photoTrack)
            val example = view.context.getString(R.string.title_and_time)
            trackArtistTime.text = String.format(example, convertTime(track.trackTimeMillis?: ""), track.artistName)
        }
        private fun convertTime(trackTime: String): String = SimpleDateFormat("mm:ss", Locale.getDefault()).format(trackTime.toLong())
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_card, parent, false)
        return ListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(tracks[position])
    }
    override fun getItemCount(): Int {
        return tracks.size
    }


}