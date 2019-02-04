package com.widyatama.iman.footballmatchschedule.presenter

import com.google.gson.Gson
import com.widyatama.iman.footballmatchschedule.api.ApiRepository
import com.widyatama.iman.footballmatchschedule.api.TheMatch
import com.widyatama.iman.footballmatchschedule.model.MatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenters(private val view: MatchInterface,
                          private val api: ApiRepository,
                          private val gson: Gson
){
    fun getList(list: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(api.doRequest(TheMatch.getMatchNext(list)),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if (!data.events.isEmpty()){
                    view.showMatchList(data.events)
                }
            }
        }
    }
}