package cc.fyre.practice.engine

import cc.fyre.practice.Practice
import cc.fyre.practice.engine.scoreboard.PracticeScoreboardGetter
import net.frozenorb.qlib.scoreboard.FrozenScoreboardHandler
import net.frozenorb.qlib.scoreboard.ScoreboardConfiguration
import net.frozenorb.qlib.scoreboard.TitleGetter

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class EngineHandler(private val instance: Practice) {

    init {
        FrozenScoreboardHandler.setConfiguration(this.createScoreboard())
    }

    fun createScoreboard(): ScoreboardConfiguration {
        val configuration = ScoreboardConfiguration()

        configuration.titleGetter = TitleGetter(this.instance.config.getString("scoreboard.title"))
        configuration.scoreGetter = PracticeScoreboardGetter(this.instance)

        return configuration
    }

}