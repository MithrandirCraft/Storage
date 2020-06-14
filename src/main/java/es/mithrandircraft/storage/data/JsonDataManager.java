package es.mithrandircraft.storage.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.entity.HumanEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import es.mithrandircraft.storage.Storage;
import es.mithrandircraft.storage.configuration.PluginConfiguration;

public class JsonDataManager {

	private File location;
	private Storage plugin;

	/**
	 * Create the instance
	 * 
	 * @param plugin plugin
	 */
	private JsonDataManager(Storage plugin) {
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
		File playerFile = new File(this.location, player.getUniqueId() + ".json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			if (playerFile.createNewFile()) {
				this.plugin.info("DataStorage created for {}", storage.getName());
			}
			gson.toJson(storage, new FileWriter(playerFile, false));
			return true;
		} catch (JsonIOException | IOException e) {
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
		File playerFile = new File(this.location, player.getUniqueId() + ".json");
		if (playerFile.exists()) {
			try (BufferedReader bf = new BufferedReader(new FileReader(playerFile))) {
				Gson gson = new Gson();
				StorageContent result = gson.fromJson(bf, StorageContent.class);
				if (result != null) {
					return result;
				}
				return new StorageContent(player);
			} catch (IOException e) {
				this.plugin.error("Error reading the {} uuid: {} storage", e, player.getName(), player.getUniqueId());
			}
		}
		return new StorageContent(player);
	}

	// ===================== STATICS =================================
	private static JsonDataManager instance;

	public static JsonDataManager getInstance(Storage plugin) {
		if (instance == null) {
			instance = new JsonDataManager(plugin);
		}
		return instance;
	}
}
