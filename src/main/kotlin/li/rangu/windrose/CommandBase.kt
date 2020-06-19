package li.rangu.windrose

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.HashMap

/**
* A simple class for implementing sub commands.
*
* Just register one of these as the executor for your plugin's root command and then register sub commands to this
* CommandBase with {@link #registerSubCommand(String, CommandExecutor)}.
*
* @param <P> The implementing plugin.
*/

abstract class CommandBase<P : Plugin?>

/**
* Creates a new CommandBase for the given plugin.
*
* @param plugin The plugin that owns this command.
*/

(plugin: P, name: String) : CommandExecutor, TabCompleter {
    private val subCommands = HashMap<String, CommandExecutor>()

    /**
     * Returns the plugin that owns this command.
     */

    val plugin: P
    val name: String

    init {
        this.name = name
        this.plugin = plugin
    }

    /**
     * Registers a sub command to this command.
     *
     * @param label The label for the sub command.
     * @param subCommand The sub command to register which can either be a plain CommandExecutor or another
     * CommandBase if further command nesting is desired.
     */

    fun registerSubCommand(label: String, subCommand: CommandExecutor) {
        subCommands.put(label.toLowerCase(), subCommand)
    }


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.size > 0) {
            val child = subCommands.get(args[0].toLowerCase())
            if (child != null) {
                var label = args[0]
                val newArgs = arrayOfNulls<String>(args.size - 1)
                System.arraycopy(args, 1, newArgs, 0, newArgs.size)
                return child.onCommand(sender, command, label, newArgs)
            }
        }
        return runCommand(sender, command, label, args)
    }

    override fun onTabComplete(sender: CommandSender, cmd: Command, commandLabel: String, args: Array<String>): List<String>? {
        val list: MutableList<String> = mutableListOf()

        if (sender is Player) {
            // TODO: Should be able to dynamically know the name of the root
            // command without defining it here. This is an issue!
            if (cmd.getName().equals("loc", ignoreCase = true)) {
                // If the player has not typed anything in
                if (args.size == 0) {
                    list.addAll(subCommands.keys)
                    return list.sorted()
                    // If player has typed one word in.
                    // This > "/command hello " does not count as one
                    // argument because of the space after the hello.
                } else if (args.size == 1) {
                    list.addAll(subCommands?.keys)

                    for (s in list) {
                        // Remove suggestions that don't match the players input
                        if (!s.toLowerCase().startsWith(args[0].toLowerCase())) {
                            list.remove(s)
                            break
                        }
                    }
                    return list.sorted()
                }
            }
        }
        return null
    }

    /**
     * Executes the given commands and returns its success.
     *
     * Note that the success returned may propagate up to the root command.
     *
     * @param sender Source of the command.
     * @param rootCommand The top level command that was executed.
     * @param label Alias of the command that was used - the sub command label being used.
     * @param args Arguments for the sub command.
     * @return true if a valid command, false otherwise.
     */

    abstract fun runCommand(sender: CommandSender, rootCommand: Command, label: String, args: Array<String>): Boolean
}
