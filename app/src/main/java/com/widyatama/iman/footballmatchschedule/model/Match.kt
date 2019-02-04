package com.widyatama.iman.footballmatchschedule.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Match(
    @SerializedName("idEvent")
    var id: String? = null,

    @SerializedName("dateEvent")
    var date: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    var goalHome: String? = null,

    @SerializedName("strAwayGoalDetails")
    var goalAway: String? = null,

    @SerializedName("intHomeShots")
    var shootHome: String? = null,

    @SerializedName("intAwayShots")
    var shootAway: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var gkHome: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var gkAway: String? = null,

    @SerializedName("strHomeLineupDefense")
    var difHome: String? = null,

    @SerializedName("strAwayLineupDefense")
    var difAway: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var midHome: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var midAway: String? = null,

    @SerializedName("strHomeLineupForward")
    var forHome: String? = null,

    @SerializedName("strAwayLineupForward")
    var forAway: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var subHome: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var subAway: String? = null


) : Serializable