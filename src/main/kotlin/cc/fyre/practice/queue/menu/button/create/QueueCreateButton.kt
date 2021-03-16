package cc.fyre.practice.queue.menu.button.create

import cc.fyre.practice.Practice
import cc.fyre.practice.queue.prompt.QueueCreatePrompt
import net.frozenorb.qlib.menu.Button
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.conversations.ConversationFactory
import org.bukkit.conversations.NullConversationPrefix
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType

/**
 * @author Brew
 *
 * @date 11/9/2020
 * @project practice
 */
class QueueCreateButton : Button() {

    override fun getName(p0: Player?): String {
        return "${ChatColor.GREEN}Create Queue"
    }

    override fun getMaterial(p0: Player?): Material {
        return Material.NETHER_STAR
    }

    override fun getDescription(p0: Player?): MutableList<String> {
        return mutableListOf()
    }

    override fun clicked(player: Player,slot: Int,clickType: ClickType) {
        player.closeInventory()
        player.beginConversation(ConversationFactory(Practice.instance).withModality(true).withPrefix(NullConversationPrefix()).withFirstPrompt(QueueCreatePrompt()).withEscapeSequence("/no").withLocalEcho(false).withTimeout(60).thatExcludesNonPlayersWithMessage("Go away evil console!").buildConversation(player))
    }

}