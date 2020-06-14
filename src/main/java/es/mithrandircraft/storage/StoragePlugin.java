package es.mithrandircraft.storage;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import es.mithrandircraft.storage.commands.OpenCommand;

/**
 * plugin's main class
 * @author xX_andrescol_Xx
 *
 */
public class StoragePlugin extends JavaPlugin {

	@Override
    public void onEnable() {
		PluginCommand openStorage = this.getCommand("openstorage");
		openStorage.setExecutor(new OpenCommand(openStorage, this.getLogger()));
    }
	
    @Override
    public void onDisable() {
    }
}
