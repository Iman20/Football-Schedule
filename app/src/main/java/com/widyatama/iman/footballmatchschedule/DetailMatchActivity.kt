package com.widyatama.iman.footballmatchschedule

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.widyatama.iman.footballclubwithapi.util.invisible
import com.widyatama.iman.footballclubwithapi.util.visible
import com.widyatama.iman.footballmatchschedule.api.ApiRepository
import com.widyatama.iman.footballmatchschedule.model.Match
import com.widyatama.iman.footballmatchschedule.presenter.DetailMatchPresenters
import com.widyatama.iman.footballmatchschedule.presenter.MatchInterface
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import java.util.*

class DetailMatchActivity: AppCompatActivity(), MatchInterface{

    private lateinit var presenter: DetailMatchPresenters
    private lateinit var progressBar: ProgressBar
    private lateinit var homeTeam: TextView
    private lateinit var awayTeam: TextView
    private lateinit var dateMatch: TextView
    private lateinit var scoreHome: TextView
    private lateinit var scoreAway: TextView

    private lateinit var goalHome: TextView
    private lateinit var goalAway: TextView
    private lateinit var lineHome: TextView
    private lateinit var lineAway: TextView
    private lateinit var shootHome: TextView
    private lateinit var shootAway: TextView
    private lateinit var gkHome: TextView
    private lateinit var gkAway: TextView
    private lateinit var difHome: TextView
    private lateinit var difAway: TextView
    private lateinit var midHome: TextView
    private lateinit var midAway: TextView
    private lateinit var forHome: TextView
    private lateinit var forAway: TextView
    private lateinit var subHome: TextView
    private lateinit var subAway: TextView
    private lateinit var logoHome: ImageView
    private lateinit var logoAway: ImageView
    private lateinit var content: LinearLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailMatchUI().setContentView(this)

        progressBar = find(R.id.progressBar)
        logoHome = find(R.id.imageHome)
        logoAway = find(R.id.imageAway)
        homeTeam = find(R.id.team1)
        awayTeam = find(R.id.team2)
        dateMatch = find(R.id.date)
        scoreHome = find(R.id.score_team1)
        scoreAway = find(R.id.score_team2)
        goalHome = find(R.id.goalHome)
        goalAway = find(R.id.goalAway)
        shootHome = find(R.id.shootHome)
        shootAway = find(R.id.shootAway)
        lineHome = find(R.id.lineupHome)
        lineAway = find(R.id.lineupAway)
        gkHome = find(R.id.gkHome)
        gkAway = find(R.id.gkAway)
        difHome = find(R.id.difHome)
        difAway = find(R.id.difAway)
        midHome = find(R.id.midHome)
        midAway = find(R.id.midAway)
        forHome = find(R.id.forHome)
        forAway = find(R.id.forAway)
        subHome = find(R.id.subHome)
        subAway = find(R.id.subAway)
        content = find(R.id.layContent)
        toolbar = find(R.id.toolbar)

        val request = ApiRepository()
        val gson = Gson()
        val matchs = intent.extras.get("match") as Match
        presenter = DetailMatchPresenters(this, request, gson)
        presenter.getById(matchs.id)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun showLoading() {
        progressBar.visible()
        content.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        content.visible()
    }

    override fun showMatchList(data: List<Match>) {
        var data: Match = data.get(0)
        logoHome.setImageResource(resources.getIdentifier(data.homeTeam!!.toLowerCase().replace(" ", ""), "drawable", ctx.packageName))
        logoAway.setImageResource(resources.getIdentifier(data.awayTeam!!.toLowerCase().replace(" ", ""), "drawable", ctx.packageName))
        homeTeam.text = data.homeTeam
        homeTeam.text = data.homeTeam
        homeTeam.text = data.homeTeam
        awayTeam.text = data.awayTeam
        dateMatch.text = data.date
        scoreHome.text = data.homeScore
        scoreAway.text = data.awayScore
        goalHome.text = data.goalHome
        goalAway.text = data.goalAway
        shootHome.text = data.shootHome
        shootAway.text = data.shootAway
        gkHome.text = data.shootAway
        gkAway.text = data.shootAway
        difHome.text = data.difHome
        difAway.text = data.difAway
        midHome.text = data.midHome
        midAway.text = data.midAway
        forHome.text = data.forHome
        forAway.text = data.forAway
        subHome.text = data.subHome
        subAway.text = data.subAway

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    class DetailMatchUI : AnkoComponent<DetailMatchActivity> {
        override fun createView(ui: AnkoContext<DetailMatchActivity>) = with(ui) {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)

                toolbar {
                    id = R.id.toolbar
                    lparams(width = matchParent, height = 100)
                    setTitleTextColor(Color.WHITE)
                    setBackgroundColor(resources.getColor(R.color.customColorPrimary))
                }

                scrollView {
                    lparams(width = matchParent, height = matchParent)

                    relativeLayout {
                        lparams(width = matchParent, height = matchParent)

                        progressBar {
                            id = R.id.progressBar
                        }.lparams{
                            width = wrapContent
                            height = wrapContent
                            centerInParent()
                        }

                        verticalLayout {
                            id = R.id.layContent
                            lparams(width = matchParent, height = matchParent)
                            padding = dip(16)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER
                            visibility = View.GONE

                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER

                                textView {
                                    id = R.id.date
                                    textColor = Color.DKGRAY
                                }.lparams {
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

                                linearLayout {
                                    padding = dip(2)
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    orientation = LinearLayout.VERTICAL

                                    imageView {
                                        id = R.id.imageHome
                                    }.lparams{
                                        width = 120
                                        height = 120
                                    }

                                    textView {
                                        id = R.id.team1
                                        textSize = 12F
                                        textColor = Color.BLACK
                                        gravity = Gravity.CENTER
                                    }.lparams {
                                        margin = dip(1)
                                    }
                                }.lparams{
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.score_team1
                                    text = "-"
                                    textSize = 12F
                                    textColor = Color.BLUE
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    text = "VS"
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.score_team2
                                    text = "-"
                                    textSize = 12F
                                    textColor = Color.BLUE
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                linearLayout {
                                    padding = dip(2)
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    orientation = LinearLayout.VERTICAL

                                    imageView {
                                        id = R.id.imageAway
                                    }.lparams{
                                        width = 120
                                        height = 120
                                    }

                                    textView {
                                        id = R.id.team2
                                        textSize = 12F
                                        textColor = Color.BLACK
                                        gravity = Gravity.CENTER
                                    }.lparams {
                                        margin = dip(1)
                                    }
                                }.lparams{
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                            }

                            //Lineup
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3F

                                textView {
                                    id = R.id.lineupHome
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.LEFT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                                textView {
                                    text = "Lineup"
                                    textSize = 18F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.lineupAway
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.RIGHT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                            }

                            //goals
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3F
                                backgroundColor = Color.parseColor("#E0E0E0")

                                textView {
                                    id = R.id.goalHome
                                    textSize = 12F
                                    gravity = Gravity.LEFT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                                textView {
                                    text = "Goals"
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.goalAway
                                    textSize = 12F
                                    gravity = Gravity.RIGHT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                            }

                            //Shoots
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3F

                                textView {
                                    id = R.id.shootHome
                                    textSize = 12F
                                    gravity = Gravity.LEFT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                                textView {
                                    text = "Shoots"
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.shootAway
                                    textSize = 12F
                                    gravity = Gravity.RIGHT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                            }

                            //Goal Kipeer
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3F
                                backgroundColor = Color.parseColor("#E0E0E0")

                                textView {
                                    id = R.id.gkHome
                                    textSize = 12F
                                    gravity = Gravity.LEFT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                                textView {
                                    text = "Goal Kiper"
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.gkAway
                                    textSize = 12F
                                    gravity = Gravity.RIGHT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                            }

                            //Defender
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3F

                                textView {
                                    id = R.id.difHome
                                    textSize = 12F
                                    gravity = Gravity.LEFT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                                textView {
                                    text = "Difender"
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.difAway
                                    textSize = 12F
                                    gravity = Gravity.RIGHT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                            }

                            //Midfield
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3F
                                backgroundColor = Color.parseColor("#E0E0E0")

                                textView {
                                    id = R.id.midHome
                                    textSize = 12F
                                    gravity = Gravity.LEFT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                                textView {
                                    text = "Midfield"
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.midAway
                                    textSize = 12F
                                    gravity = Gravity.RIGHT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                            }

                            //Forward
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3F

                                textView {
                                    id = R.id.forHome
                                    textSize = 12F
                                    gravity = Gravity.LEFT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                                textView {
                                    text = "Forward"
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.forAway
                                    textSize = 12F
                                    gravity = Gravity.RIGHT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                            }

                            //Substitution
                            linearLayout {
                                lparams(width = matchParent, height = wrapContent)
                                padding = dip(2)
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL
                                weightSum = 3F
                                backgroundColor = Color.parseColor("#E0E0E0")

                                textView {
                                    id = R.id.subHome
                                    textSize = 12F
                                    gravity = Gravity.LEFT
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }


                                textView {
                                    text = "Substitution"
                                    textSize = 12F
                                    textColor = Color.BLACK
                                    gravity = Gravity.CENTER
                                }.lparams {
                                    width = 0
                                    weight = 1F
                                    height = wrapContent
                                    margin = dip(1)
                                }

                                textView {
                                    id = R.id.subAway
                                    textSize = 12F
                                    gravity = Gravity.RIGHT
                                }.lparams {
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

    }
}