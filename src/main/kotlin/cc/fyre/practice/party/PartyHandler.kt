package cc.fyre.practice.party

import cc.fyre.carnage.Carnage
import cc.fyre.practice.Practice
import cc.fyre.practice.party.command.*
import cc.fyre.practice.party.data.Party
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
        Carnage.instance.commandHandler.registerClass(PartyHelpCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyInviteCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyJoinCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyKickCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyLeaveCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyPromoteCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyLeaderCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyDisbandCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyOpenCommand::class.java)
        Carnage.instance.commandHandler.registerClass(PartyCloseCommand::class.java)
    }

    fun findById(id: UUID): Party {
        return this.cache.stream().filter{it.members.contains(id)}.findFirst().orElse(Party(id))
    }

}