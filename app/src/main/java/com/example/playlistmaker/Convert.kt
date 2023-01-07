package com.example.playlistmaker

class Convert {
    fun convert(con: Tracks?) = Track(
        trackName = con?.trackName.toString(),
        artistName = con?.artistName.toString(),
        trackTimeMillis = con?.trackTimeMillis.toString(),
        artworkUrl100 = con?.artistName.toString()
    )
}