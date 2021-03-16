package cc.fyre.practice.kit.data

import com.google.gson.annotations.SerializedName
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.Potion
import org.bukkit.potion.PotionType

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class Kit(@SerializedName("_id")val id: String) {

    var armor = arrayOfNulls<ItemStack>(4)
    var inventory = arrayOfNulls<ItemStack>(27)

    fun apply(player: Player) {
        player.inventory.clear()

        player.inventory.armorContents = this.armor
        player.inventory.contents = this.inventory
    }

    fun countHealthPotions(): Int {
        return this.inventory.filterNotNull().filter{it.type == Material.POTION}.filter{Potion.fromItemStack(it).type == PotionType.INSTANT_HEAL}.count()
    }

}