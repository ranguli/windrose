package ca.ranguli.windrose

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import kotlin.math.roundToInt

class Windrose : JavaPlugin(), CommandExecutor {

    override fun onEnable() {
        logger.info(description.name + " has been enabled.")
        getCommand("loc")?.setExecutor(this)
    }

    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val x = sender.location.x.roundToInt()
            val y = sender.location.y.roundToInt()

            sender.sendMessage("${ChatColor.GREEN}$x, $y")
        } else {
            sender.sendMessage("This command can only be run by a player.")
        }
        return true
    }

    override fun onDisable() {
        logger.info(description.name + " has been disabled.")
    }
}
