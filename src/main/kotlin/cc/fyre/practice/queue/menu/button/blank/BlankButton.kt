package cc.fyre.practice.queue.menu.button.blank

import net.frozenorb.qlib.menu.Button
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class BlankButton : Button() {

    override fun getMaterial(player: Player): Material {
        return Material.STAINED_GLASS_PANE
    }

    override fun getName(player: Player): String {
        return " "
    }

    override fun getDescription(player: Player): MutableList<String> {
        return mutableListOf()
    }

    override fun getDamageValue(player: Player): Byte {
        return 15
    }

}