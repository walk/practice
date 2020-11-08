package cc.fyre.practice.match.data

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author Brew
 *
 * @date 11/6/2020
 * @project Practice
 */
class Match(@SerializedName("_id") val id: UUID = UUID.randomUUID()) {

    val state = MatchState.STARTING

    var isRanked: Boolean = false


    enum class MatchType(val displayName: String) {



    }

    enum class MatchState {
        STARTING,
        ACTIVE,
        ENDING,
        FINISHED
    }

}