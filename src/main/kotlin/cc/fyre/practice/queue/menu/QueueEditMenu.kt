package cc.fyre.practice.queue.menu

import cc.fyre.practice.queue.data.Queue
import cc.fyre.practice.queue.menu.button.blank.BlankButton
import cc.fyre.practice.queue.menu.button.edit.QueueEditMapsButton
import cc.fyre.practice.queue.menu.button.edit.QueueEditMatchTypeButton
import net.frozenorb.qlib.menu.Button
import net.frozenorb.qlib.menu.Menu
import org.bukkit.entity.Player

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class QueueEditMenu(private val queue: Queue): Menu() {

    override fun size(buttons: MutableMap<Int, Button>?): Int {
        return 3*9
    }

    override fun getTitle(player: Player?): String {
        return this.queue.displayName
    }

    override fun getButtons(player: Player): MutableMap<Int, Button> {
        val toReturn = HashMap<Int,Button>()

        toReturn[10] = QueueEditMatchTypeButton(this.queue)

        toReturn[12] = QueueEditMapsButton(this.queue)

        for(i in 0 until this.size(toReturn)) {

            if(toReturn.containsKey(i)) {
                continue
            }

            toReturn[i] = BlankButton()
        }

        return toReturn
    }

}