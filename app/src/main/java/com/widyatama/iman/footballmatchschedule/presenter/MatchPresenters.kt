package com.widyatama.iman.footballmatchschedule.presenter

import android.util.Log
import com.google.gson.Gson
import com.widyatama.iman.footballmatchschedule.api.ApiRepository
import com.widyatama.iman.footballmatchschedule.api.TheMatch
import com.widyatama.iman.footballmatchschedule.model.MatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenters(private val view: MatchInterface,
                      private val api: ApiRepository,
                      private val gson: Gson){
    fun getList(list: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(api.doRequest(TheMatch.getMatchLast(list)),
                MatchResponse::class.java
            )

            Log.i("Result", data.toString())

            uiThread {
                view.hideLoading()
                if (!data.events.isEmpty()){
                    view.showMatchList(data.events)
                }
            }
        }
    }
}