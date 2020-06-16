package ca.ranguli.windrose

import org.bukkit.plugin.java.JavaPlugin


import org.bukkit.entity.Player

import org.bukkit.command.CommandSender
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor

class Windrose : JavaPlugin(), CommandExecutor {

    override fun onEnable() {
        logger.info(description.name + " has been enabled.")
        getCommand("loc")?.setExecutor(this)
    }	

    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender is Player) {
            logger.info(sender.name)
        } else {
            sender.sendMessage("This command can only be run by a player.")
        }
        return true
    }

    override fun onDisable() {
        logger.info(description.name + " has been disabled.")
    }	
	
}