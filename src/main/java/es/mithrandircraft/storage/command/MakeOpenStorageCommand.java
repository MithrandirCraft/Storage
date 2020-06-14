package es.mithrandircraft.storage.command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import es.mithrandircraft.storage.Storage;
import es.mithrandircraft.storage.configuration.PluginLanguaje;

/**
 * This command executor make that the player open the inventory. This command
 * can be executed by players and console
 * 
 * @author andrescol24
 *
 */
public class MakeOpenStorageCommand extends StorageCommand implements CommandExecutor, TabCompleter {

	public MakeOpenStorageCommand(Storage plugin) {
		super(plugin);
		this.inventories = new HashMap<>();
	}

	private HashMap<String, Inventory> inventories;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String argumment2, String[] arguments) {

		// Checking permission or argumment 2 null or empty
		if (sender.hasPermission(command.getPermission())) {
			PluginLanguaje languaje = PluginLanguaje.getInstance(plugin);
			
			String name = this.getArgument(0, arguments);
			// Incorrect usage
			if (name == null) {
				String message = languaje.formatMessage(command.getUsage());
				this.sendMessage(sender, message);
				return true;
			}

			Player player = Bukkit.getPlayer(name);
			// if the player is offline
			if (player == null) {
				String message = languaje.getMessage("makeopenstorage.notfound", name);
				this.sendMessage(sender, message);
			} else {
				// Create the inventory o read and existent inventory
				Inventory inventory = inventories.get(player.getName());
				if (inventory == null) {
					inventory = Bukkit.createInventory(player, 9, languaje.getString("storage.name"));
					inventories.put(player.getName(), inventory);
				}
				player.openInventory(inventory);
			}
			return true;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> names = new LinkedList<>();
		Bukkit.getOnlinePlayers().forEach(player -> names.add(player.getName()));
		return names;
	}

}
