package com.widyatama.iman.footballmatchschedule.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import com.widyatama.iman.footballclubwithapi.adapter.ListMatchAdapter
import com.widyatama.iman.footballclubwithapi.util.invisible
import com.widyatama.iman.footballclubwithapi.util.visible
import com.widyatama.iman.footballmatchschedule.DetailMatchActivity
import com.widyatama.iman.footballmatchschedule.MainActivity
import com.widyatama.iman.footballmatchschedule.R
import com.widyatama.iman.footballmatchschedule.api.ApiRepository
import com.widyatama.iman.footballmatchschedule.model.Match
import com.widyatama.iman.footballmatchschedule.presenter.MatchInterface
import com.widyatama.iman.footballmatchschedule.presenter.NextMatchPresenters
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.io.Serializable

class NextMatchFragment : Fragment(), MatchInterface {
    private lateinit var presenter: NextMatchPresenters
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: ListMatchAdapter
    private var match: MutableList<Match> = mutableListOf()

    companion object {
        fun newInstance():NextMatchFragment{
            val fragment = NextMatchFragment()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            linearLayout {
                lparams (width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefreshLayout = swipeRefreshLayout{
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listMatch = recyclerView {
                            lparams (width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams{
                            centerHorizontally()
                        }
                    }
                }


            }
        }.view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = NextMatchPresenters(this, request, gson)
        presenter.getList("4328")

        adapter = ListMatchAdapter(match, {match: Match ->
            startActivity<DetailMatchActivity>("match" to match as Serializable)
        })
        listMatch.adapter = adapter


        swipeRefreshLayout.onRefresh {
            presenter.getList("4328")
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefreshLayout.isRefreshing = false
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

}