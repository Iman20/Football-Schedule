package com.widyatama.iman.footballmatchschedule.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.widyatama.iman.footballclubwithapi.adapter.ListMatchAdapter
import com.widyatama.iman.footballmatchschedule.R
import com.widyatama.iman.footballmatchschedule.model.Match
import org.jetbrains.anko.*

class NextMatchAdapter(private val teams: List<Match>): RecyclerView.Adapter<NextMatchAdapter.MatchHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchHolder {
        return MatchHolder(ListMatchAdapter.MatchUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(p0: MatchHolder, p1: Int) {
        p0.bindItem(teams[p1])
    }

    class MatchHolder(view: View): RecyclerView.ViewHolder(view){
        private val matchDate: TextView = view.find(R.id.date)
        private val teamHome: TextView = view.find(R.id.team1)
        private val teamAway: TextView = view.find(R.id.team2)
        fun bindItem(match: Match){
            matchDate.text = match.date
            teamHome.text = match.homeTeam
            teamAway.text = match.awayTeam
        }

    }

    class MatchUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER

                    //date
                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(2)
                        gravity = Gravity.CENTER

                        textView {
                            id = R.id.date
                            textColor = Color.DKGRAY
                        }.lparams{
                            margin = dip(1)
                        }
                    }

                    //team
                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(2)
                        gravity = Gravity.CENTER
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 5F

                        textView {
                            id = R.id.team1
                            textSize = 20F
                            textColor = Color.BLACK
                            gravity = Gravity.CENTER
                        }.lparams{
                            width = 0
                            weight = 1F
                            height = wrapContent
                            margin = dip(1)
                        }

                        textView {
                            id = R.id.score_team1
                            text = "-"
                            textSize = 20F
                            textColor = Color.BLUE
                            gravity = Gravity.CENTER
                        }.lparams{
                            width = 0
                            weight = 1F
                            height = wrapContent
                            margin = dip(1)
                        }

                        textView {
                            text = "VS"
                            textSize = 20F
                            textColor = Color.BLACK
                            gravity = Gravity.CENTER
                        }.lparams{
                            width = 0
                            weight = 1F
                            height = wrapContent
                            margin = dip(1)
                        }

                        textView {
                            id = R.id.score_team2
                            text = "-"
                            textSize = 20F
                            textColor = Color.BLUE
                            gravity = Gravity.CENTER
                        }.lparams{
                            width = 0
                            weight = 1F
                            height = wrapContent
                            margin = dip(1)
                        }

                        textView {
                            id = R.id.team2
                            textSize = 20F
                            textColor = Color.BLACK
                            gravity = Gravity.CENTER
                        }.lparams{
                            width = 0
                            weight = 1F
                            height = wrapContent
                            margin = dip(1)
                        }

                    }
                }
            }
        }

    }

}