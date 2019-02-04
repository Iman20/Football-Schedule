package com.widyatama.iman.footballmatchschedule.presenter

import com.widyatama.iman.footballmatchschedule.model.Match

interface MatchInterface{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}