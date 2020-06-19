package li.rangu.windrose

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

        val locCommand = CommandLoc(this, "loc")

        val commandLocPost = CommandLocPost(this, "post")

        locCommand.registerSubCommand("post", commandLocPost)

        // Register "/check this out" with the sub command "/check this".
        // checkThisCommand.registerSubCommand("out", CheckThisOutCommand())

        // Register "/check" command executor with Bukkit.
        getCommand("loc")?.setExecutor(locCommand)
    }

    override fun onDisable() {
        logger.info(description.name + " has been disabled.")
    }

}
