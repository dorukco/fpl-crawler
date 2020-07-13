package org.dodo.fpl.crawler.service

import java.time.ZonedDateTime

data class BootStrapStaticResponse(
        val elements: List<Element>
)

data class Element(
        val id: Long?,
        val first_name: String?,
        val second_name: String?,
        val web_name: String?,
        val assists: Int?,
        val goals_scored: Int?,
        val goals_conceded: Int?,
        val clean_sheets: Int?,
        val saves: Int?,
        val bonus: Int?,
        val bps: Int?,
        val minutes: Int?,
        val team: Int?,
        val creativity: Double?,
        val ict_index: Double?,
        val influence: Double?,
        val form: Double?,
        val points_per_game: Double?,
        var updateTime: ZonedDateTime? = null
)

data class FixtureResponse(
        val id: Long?,
        val event: Int?,
        val finished: Boolean?,
        val finished_provisional: Boolean?,
        val kickoff_time: ZonedDateTime?,
        val team_a: Int?,
        val team_a_score: Int?,
        val team_h: Int?,
        val team_h_score: Int?,
        val team_h_difficulty: Int?,
        val team_a_difficulty: Int?,
        var updateTime: ZonedDateTime? = null
)