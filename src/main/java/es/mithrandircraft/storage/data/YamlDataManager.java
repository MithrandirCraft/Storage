package es.mithrandircraft.storage.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;

import es.mithrandircraft.storage.Storage;
import es.mithrandircraft.storage.configuration.PluginConfiguration;

public class YamlDataManager {

	private File location;
	private Storage plugin;

	/**
	 * Create the instance
	 * 
	 * @param plugin plugin
	 */
	private YamlDataManager(Storage plugin) {
		PluginConfiguration configuration = PluginConfiguration.getInstance(plugin);
		String path = configuration.getStoragePath().replace("PLUGIN_DATA_FOLDER", plugin.getPluginFolder().getPath());
		this.location = new File(path);
		this.plugin = plugin;
		if (!this.location.exists()) {
			boolean created = this.location.mkdir();
			if (created) {
				this.plugin.info("The Storage dir was created in {}", this.location.getAbsolutePath());
			}
		}
	}

	/**
	 * Save the storage information
	 * 
	 * @param storage Storage
	 */
	public boolean put(HumanEntity player, StorageContent storage) {
		File playerFile = new File(this.location, player.getUniqueId() + ".yml");
		try {
			YamlConfiguration yaml = storage.toYaml();
			yaml.save(playerFile);
			return true;
		} catch (IOException e) {
			this.plugin.error("The data of {} cannot be saved", e, storage.getName());
			return false;
		}
	}

	/**
	 * Read a File and load to object
	 * 
	 * @param player the player
	 * @return the playerStorage or null if not exist
	 */
	public StorageContent get(HumanEntity player) {
		File playerFile = new File(this.location, player.getUniqueId() + ".yml");
		if (playerFile.exists()) {
			try {
				YamlConfiguration yaml = new YamlConfiguration();
				yaml.load(playerFile);
				return new StorageContent(yaml);
			} catch (IOException | InvalidConfigurationException e) {
				this.plugin.error("Error reading the {} uuid: {} storage", e, player.getName(), player.getUniqueId());
			}
		}
		return new StorageContent(player);
	}

	// ===================== STATICS =================================
	private static YamlDataManager instance;

	public static YamlDataManager getInstance(Storage plugin) {
		if (instance == null) {
			instance = new YamlDataManager(plugin);
		}
		return instance;
	}
}
