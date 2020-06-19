package li.rangu.windrose

import org.bukkit.command.Command
import org.bukkit.entity.Player
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

class CommandLoc(plugin: Windrose?, name: String) : CommandBase<Windrose?>(plugin) {


    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val coordinates = Location.getPlayerCoordinates(sender)

            sender.sendMessage("${ChatColor.GREEN} ${coordinates.x}, ${coordinates.y}")
        } else {
            sender.sendMessage("This command can only be run by a player.")
        }
        return true
    }

    override fun runCommand(sender: CommandSender, rootCommand: Command, label: String, args: Array<String>): Boolean {
        sender.sendMessage("This is the /check command")
        return false
    }
}
