package cc.fyre.practice.map.menu

import cc.fyre.practice.Practice
import cc.fyre.practice.map.menu.button.MapEditButton
import cc.fyre.practice.map.menu.button.create.MapCreateButton
import net.frozenorb.qlib.menu.Button
import net.frozenorb.qlib.menu.pagination.PaginatedMenu
import org.bukkit.entity.Player

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class MapEditorMenu : PaginatedMenu() {

    override fun getPrePaginatedTitle(player: Player): String {
        return "Map Editor"
    }

    override fun getGlobalButtons(player: Player?): MutableMap<Int, Button> {
        val toReturn = HashMap<Int,Button>()

        toReturn[4] = MapCreateButton()

        return toReturn
    }

    override fun getAllPagesButtons(player: Player): MutableMap<Int, Button> {
        val toReturn = HashMap<Int,Button>()

        Practice.instance.mapHandler.maps.forEach{toReturn[toReturn.size] = MapEditButton(it)}

        return toReturn
    }

}