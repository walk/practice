package cc.fyre.practice.queue.menu

import cc.fyre.practice.Practice
import cc.fyre.practice.queue.menu.button.QueueButton
import cc.fyre.practice.queue.menu.button.create.QueueCreateButton
import net.frozenorb.qlib.menu.Button
import net.frozenorb.qlib.menu.pagination.PaginatedMenu
import org.bukkit.entity.Player

/**
 * @author Brew
 *
 * @date 11/9/2020
 * @project practice
 */
class QueueEditorMenu : PaginatedMenu() {

    override fun getPrePaginatedTitle(player: Player): String {
        return "Queue Editor"
    }

    override fun getGlobalButtons(player: Player): MutableMap<Int, Button> {
        val toReturn = HashMap<Int, Button>()

        toReturn[4] = QueueCreateButton()

        return toReturn
    }

    override fun getAllPagesButtons(player: Player): MutableMap<Int, Button> {
        val toReturn = HashMap<Int, Button>()

        Practice.instance.queueHandler.cache.stream().forEach{toReturn[toReturn.size] = QueueButton(it)}

        return toReturn
    }

}