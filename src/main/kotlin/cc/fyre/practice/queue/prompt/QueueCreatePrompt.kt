package cc.fyre.practice.queue.prompt

import cc.fyre.practice.Practice
import cc.fyre.practice.queue.data.Queue
import cc.fyre.practice.queue.menu.QueueEditMenu
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.conversations.ConversationContext
import org.bukkit.conversations.Prompt
import org.bukkit.conversations.StringPrompt
import org.bukkit.entity.Player

/**
 * @author Brew
 *
 * @date 11/9/2020
 * @project practice
 */
class QueueCreatePrompt : StringPrompt() {
    override fun getPromptText(conversation: ConversationContext): String {
        return "${ChatColor.YELLOW}Please type a name for this queue, or type ${ChatColor.RED}\"cancel\"${ChatColor.YELLOW} to cancel."
    }

    override fun acceptInput(conversation: ConversationContext, input: String): Prompt? {

        if(input.equals("cancel",true)) {
            conversation.forWhom.sendRawMessage("${ChatColor.RED}Cancelled creating a queue.")
            return Prompt.END_OF_CONVERSATION
        }

        if(Practice.instance.queueHandler.findById(input) != null) {
            conversation.forWhom.sendRawMessage("${ChatColor.RED}A queue with that name already exists.")
            return Prompt.END_OF_CONVERSATION
        }

        val queue = Queue(input)

        Practice.instance.queueHandler.cache.add(queue)

        Bukkit.getServer().scheduler.runTaskAsynchronously(Practice.instance) {
            Practice.instance.queueHandler.update(queue)
        }

        QueueEditMenu(queue).openMenu(conversation.forWhom as Player)

        return Prompt.END_OF_CONVERSATION
    }
}