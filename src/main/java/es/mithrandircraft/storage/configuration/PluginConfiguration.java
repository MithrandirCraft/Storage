package es.mithrandircraft.storage.configuration;

import org.bukkit.configuration.file.FileConfiguration;

import es.mithrandircraft.storage.Storage;

/**
 * Singleton class that save the configuration
 * 
 * @author andrescol24
 *
 */
public class PluginConfiguration {

	// Configuration
	private String version;
	private String storagePath;
	
	private final Storage plugin;

	/**
	 * Create the instance for the configuration
	 * 
	 * @param plugin
	 */
	private PluginConfiguration(Storage plugin) {
		this.plugin = plugin;
		this.loadConfiguration(plugin);
	}

	public String getVersion() {
		return version;
	}

	public String getStoragePath() {
		return storagePath;
	}

	/**
	 * Load the configuracion fron the plugin config file
	 * 
	 * @param plugin
	 */
	private void loadConfiguration(Storage plugin) {
		FileConfiguration configFile = plugin.getFileConfiguration();
		this.version = configFile.getString("version");
		this.storagePath = configFile.getString("storage-path");
	}

	// ===================== STATICS ================================

	private static PluginConfiguration instance;

	/**
	 * Get the instance for the configuration
	 * 
	 * @param plugin Plugin
	 * @return instance
	 */
	public static PluginConfiguration getInstance(Storage plugin) {
		if (instance == null) {
			instance = new PluginConfiguration(plugin);
		}
		return instance;
	}

	/**
	 * Reload the configuration
	 */
	public void reloadConfig() {
		this.plugin.reloadConfig();
		this.loadConfiguration(this.plugin);
	}

}
