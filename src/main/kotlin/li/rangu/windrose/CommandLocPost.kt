package li.rangu.windrose

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandLocPost(plugin: Windrose?, name: String) : CommandBase<Windrose?>(plugin) {
    override fun runCommand(sender: CommandSender, rootCommand: Command, label: String, args: Array<String>): Boolean {
        // Get the player location
        // Do the discord webhook here
        return true
    }
}
