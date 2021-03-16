package cc.fyre.practice.map.menu.button.create

import cc.fyre.practice.Practice
import cc.fyre.practice.map.prompt.MapCreatePrompt
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
 * @date 3/4/2021
 * @project practice
 */
class MapCreateButton : Button() {

    override fun getName(player: Player): String {
        return "${ChatColor.GREEN}Create Map"
    }

    override fun getDescription(player: Player): MutableList<String> {
        val toReturn = ArrayList<String>()

        toReturn.add("")
        toReturn.add("${ChatColor.GRAY}Create a new map & follow the steps.")

        return toReturn
    }

    override fun getMaterial(player: Player): Material {
        return Material.NETHER_STAR
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType) {

        player.closeInventory()
        player.beginConversation(ConversationFactory(Practice.instance).withModality(true).withPrefix(NullConversationPrefix()).withFirstPrompt(MapCreatePrompt()).withEscapeSequence("/no").withLocalEcho(false).withTimeout(60).thatExcludesNonPlayersWithMessage("Go away evil console!").buildConversation(player))
    }

}