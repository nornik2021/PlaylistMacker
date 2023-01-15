package com.example.playlistmaker

class Converter {
    fun convert(convert: Tracks?) = Track(
        trackName = convert?.trackName.toString(),
        artistName = convert?.artistName.toString(),
        trackTimeMillis = convert?.trackTimeMillis.toString(),
        artworkUrl100 = convert?.artworkUrl100.toString()
    )
}