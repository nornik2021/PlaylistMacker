package com.example.playlistmaker

sealed class SearchScreenStatus{
    class Result(val list: List<Track>): SearchScreenStatus()
    object NothingFound: SearchScreenStatus()
    object NetworkProblem: SearchScreenStatus()
}
