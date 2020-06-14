package es.mithrandircraft.storage;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import es.mithrandircraft.storage.command.OpenStorageCommand;

/**
 * plugin's main class
 * 
 * @author xX_andrescol_Xx
 *
 */
public class StoragePlugin extends JavaPlugin implements Storage {

	@Override
	public void onEnable() {
		this.chargeConfiguration();
		this.getCommand("sopen").setExecutor(new OpenStorageCommand(this));
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void log(String message) {
		this.getLogger().info(message);
	}

	@Override
	public void warn(String message) {
		this.getLogger().warning(message);

	}

	@Override
	public void error(String message, Throwable exception) {
		this.getLogger().log(Level.SEVERE, message, exception);

	}

	@Override
	public FileConfiguration getFileConfiguration() {
		return this.getConfig();
	}

	@Override
	public File getPluginFolder() {
		return this.getDataFolder();
	}

	/**
	 * Load and save the configuration files
	 */
	private void chargeConfiguration() {
		getConfig().options().copyDefaults(true);
		File config = new File(getDataFolder(), "config.yml");
		File lang = new File(getDataFolder(), "lang.properties");
		try {
			if (!config.exists()) {
				saveDefaultConfig();
			}
			if (!lang.exists()) {
				saveResource("lang.properties", false);
			}
		} catch (Exception e) {
			this.error("Can not load the configuration", e);
		}
	}
}
