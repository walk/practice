package cc.fyre.practice.match.data.team

import java.util.*
import kotlin.collections.HashMap

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class MatchTeam(val members: HashMap<UUID,MemberStatus>) {

    fun findAlive(): MutableSet<UUID> {
        return this.members.filter{it.value == MemberStatus.ALIVE}.map{it.key}.toMutableSet()
    }

    fun setDead(uuid: UUID) {
        this.members.replace(uuid,MemberStatus.DEAD)
    }

    enum class MemberStatus {

        ALIVE,
        DEAD

    }

}