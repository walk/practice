package cc.fyre.practice.party

import cc.fyre.practice.Practice
import cc.fyre.practice.party.command.*
import cc.fyre.practice.party.data.Party
import cc.fyre.practice.party.listener.PartyListener
import net.frozenorb.qlib.command.FrozenCommandHandler
import java.util.*
import kotlin.collections.HashSet

/**
 * @author Brew
 *
 * @date 11/5/2020
 * @project Practice
 */
class PartyHandler(private val instance: Practice) {

    val cache = HashSet<Party>()

    init {
        this.instance.server.pluginManager.registerEvents(PartyListener(this.instance), this.instance)

        FrozenCommandHandler.registerClass(PartyCloseCommand::class.java)
        FrozenCommandHandler.registerClass(PartyDisbandCommand::class.java)
        FrozenCommandHandler.registerClass(PartyHelpCommand::class.java)
        FrozenCommandHandler.registerClass(PartyInviteCommand::class.java)
        FrozenCommandHandler.registerClass(PartyJoinCommand::class.java)
        FrozenCommandHandler.registerClass(PartyKickCommand::class.java)
        FrozenCommandHandler.registerClass(PartyLeaderCommand::class.java)
        FrozenCommandHandler.registerClass(PartyLeaveCommand::class.java)
        FrozenCommandHandler.registerClass(PartyOpenCommand::class.java)
        FrozenCommandHandler.registerClass(PartyPromoteCommand::class.java)

    }

    fun findById(id: UUID): Party {
        return this.cache.stream().filter{it.members.contains(id)}.findFirst().orElse(Party(id))
    }

}