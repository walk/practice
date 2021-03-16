package cc.fyre.practice.map.prompt

import cc.fyre.practice.Practice
import cc.fyre.practice.map.data.Map
import org.bukkit.*
import org.bukkit.conversations.ConversationContext
import org.bukkit.conversations.Prompt
import org.bukkit.conversations.StringPrompt
import org.bukkit.entity.Player
import org.bukkit.generator.ChunkGenerator

/**
 * @author Brew
 *
 * @date 3/4/2021
 * @project practice
 */
class MapCreatePrompt : StringPrompt() {

    override fun getPromptText(conversation: ConversationContext): String {
        return "${ChatColor.YELLOW}Please type a name for this map, or type ${ChatColor.RED}\"cancel\"${ChatColor.YELLOW} to cancel."
    }

    override fun acceptInput(conversation: ConversationContext, input: String): Prompt? {

        if(input.equals("cancel",true)) {
            conversation.forWhom.sendRawMessage("${ChatColor.RED}Cancelled creating a map.")
            return Prompt.END_OF_CONVERSATION
        }

        if(Practice.instance.mapHandler.findById(input) != null) {
            conversation.forWhom.sendRawMessage("${ChatColor.RED}A map with that name already exists.")
            return Prompt.END_OF_CONVERSATION
        }

        val map = Map(input)

        Practice.instance.mapHandler.maps.add(map)

        conversation.forWhom.sendRawMessage("${ChatColor.GREEN}You're creating a new map, to complete this you must do the following:")
        conversation.forWhom.sendRawMessage(" ")
        conversation.forWhom.sendRawMessage("${ChatColor.WHITE}Paste in the schematic of your map in the world you were just teleported too")
        conversation.forWhom.sendRawMessage("${ChatColor.WHITE}Type /map claim ${map.id} and select two corners")
        conversation.forWhom.sendRawMessage("${ChatColor.WHITE}Type /map setspawn <one|two> to set the spawn locations for either or teams.")
        conversation.forWhom.sendRawMessage("${ChatColor.WHITE}Once you've done all that, type /map save to write the world to the container &")
        conversation.forWhom.sendRawMessage("${ChatColor.WHITE}cache it.")

        val creator = WorldCreator(map.id)

        creator.generator(object : ChunkGenerator() {
            override fun generate(world: World, random: java.util.Random, x: Int, z: Int): ByteArray {
                return ByteArray(32768) //Empty byte array
            }
        })

        val world = Practice.instance.server.createWorld(creator)

        val location = Location(world,0.0,50.0,0.0)

        world.setSpawnLocation(0,50,0)

        location.block.type = Material.BEDROCK

        (conversation.forWhom as Player).teleport(world.spawnLocation)

        return Prompt.END_OF_CONVERSATION
    }
}