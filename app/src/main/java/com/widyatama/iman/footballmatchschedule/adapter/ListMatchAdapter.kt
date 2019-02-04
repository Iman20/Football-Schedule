package com.widyatama.iman.footballclubwithapi.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.widyatama.iman.footballmatchschedule.model.Match
import com.widyatama.iman.footballmatchschedule.R.id.date
import com.widyatama.iman.footballmatchschedule.R.id.team1
import com.widyatama.iman.footballmatchschedule.R.id.team2
import com.widyatama.iman.footballmatchschedule.R.id.score_team1
import com.widyatama.iman.footballmatchschedule.R.id.score_team2
import org.jetbrains.anko.*

class ListMatchAdapter(private val teams: List<Match>, val clickListener: (Match) -> Unit): RecyclerView.Adapter<ListMatchAdapter.TeamHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamHolder {
        return TeamHolder(MatchUI().createView(AnkoContext.Companion.create(p0.context, p0)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(p0: TeamHolder, p1: Int) {
        p0.bindItem(teams[p1], clickListener)
    }

    class TeamHolder(val view: View): RecyclerView.ViewHolder(view){
        private val matchDate: TextView = view.find(date)
        private val teamHome: TextView = view.find(team1)
        private val teamAway: TextView = view.find(team2)
        private val scoreTeamHome: TextView = view.find(score_team1)
        private val scoreTeamAway: TextView = view.find(score_team2)
        fun bindItem(match: Match, clickListener: (Match) -> Unit){
            matchDate.text = match.date
            teamHome.text = match.homeTeam
            teamAway.text = match.awayTeam
            scoreTeamHome.text = match.homeScore
            scoreTeamAway.text = match.awayScore
            view.setOnClickListener { clickListener(match) }


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
                            id = date
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
                            id = team1
                            textSize = 14F
                            gravity = Gravity.CENTER
                        }.lparams{
                            width = 0
                            weight = 1F
                            height = wrapContent
                            margin = dip(1)
                        }

                        textView {
                            id = score_team1
                            textSize = 16F
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
                            id = score_team2
                            textSize = 16F
                            textColor = Color.BLUE
                            gravity = Gravity.CENTER
                        }.lparams{
                            width = 0
                            weight = 1F
                            height = wrapContent
                            margin = dip(1)
                        }

                        textView {
                            id = team2
                            textSize = 14F
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