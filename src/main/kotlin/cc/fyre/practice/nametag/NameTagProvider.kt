//package cc.fyre.practice.nametag
//
//import cc.fyre.carnage.Carnage
//import cc.fyre.carnage.nametag.data.NameTagEntry
//import cc.fyre.carnage.nametag.data.adapter.NameTagAdapter
//import cc.fyre.practice.Practice
//import cc.fyre.venom.Venom
//import cc.fyre.venom.grant.event.GrantApplyEvent
//import cc.fyre.venom.grant.event.GrantRemoveEvent
//import org.bukkit.ChatColor
//import org.bukkit.entity.Player
//import org.bukkit.event.EventHandler
//import org.bukkit.event.EventPriority
//import org.bukkit.event.Listener
//
///**
// * @author Brew
// *
// * @date 11/5/2020
// * @project Practice
// */
//class NameTagProvider(private val instance: Practice) : NameTagAdapter, Listener {
//
//    init {
//        this.instance.server.pluginManager.registerEvents(this,this.instance)
//    }
//
//    override fun getNameTag(player: Player, target: Player): NameTagEntry {
//        val color = Venom.instance.api.grantHandler.findBestRank(player.uniqueId).color.replace("&","§",false)
//
//        return NameTagEntry(color,color,"")
//    }
//
//    override fun getWeight(): Int {
//        return 0
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR)
//    private fun onGrantApply(event: GrantApplyEvent) {
//        Carnage.instance.nameTagHandler.update(event.player)
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR)
//    private fun onGrantRemove(event: GrantRemoveEvent) {
//        Carnage.instance.nameTagHandler.update(event.player)
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR)
//    private fun onGrantExpire(event: GrantRemoveEvent) {
//        Carnage.instance.nameTagHandler.update(event.player)
//    }
//}