package es.mithrandircraft.storage.commands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class OpenCommand implements CommandExecutor,  TabCompleter {
	
	private String permission;
	private Logger log;
	private HashMap<String, Inventory> inventories;
	
	public OpenCommand(PluginCommand command, Logger log) {
		this.permission = command.getPermission();
		this.log = log;
		this.inventories = new HashMap<>();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String argumment2, String[] arguments) {
		
		// Validation of sender's type
		if(!(sender instanceof Player)) {
			log.info("This command is not allowed from console");
			return false;
		}
		
		// Checking permission
		if(sender.hasPermission(this.permission)) {
			Player player = (Player) sender;
			Inventory inventory = inventories.get(player.getName());
			if(inventory == null) {
				inventory = Bukkit.createInventory(player, 9, "Almacenamiento transversal");
				inventories.put(player.getName(), inventory);
			}
			player.openInventory(inventory);
			return true;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete (CommandSender sender, Command command,
		 String alias, String[] args) {
		return new LinkedList<>();
	}
}
