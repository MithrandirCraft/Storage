package es.mithrandircraft.storage.eventhandler;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import es.mithrandircraft.storage.Storage;
import es.mithrandircraft.storage.StorageHolder;
import es.mithrandircraft.storage.data.StorageContent;
import es.mithrandircraft.storage.data.YamlDataManager;

/**
 * Listener for the inventories interaction. It validate if the invetory is a
 * Storage
 * 
 * @author andrescol24
 *
 */
public class StorageInteractListener implements Listener {

	private final Storage plugin;

	/**
	 * Create the listener
	 * 
	 * @param plugin plugin
	 */
	public StorageInteractListener(Storage plugin) {
		this.plugin = plugin;
	}

	/**
	 * The event have low priority because no all inventories are Storage. The
	 * storage inventories are few. But this event can be cancelled
	 * 
	 * @param event Event
	 */
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void storageInteract(InventoryCloseEvent event) {
		Inventory inventory = event.getInventory();
		InventoryHolder holder = inventory.getHolder();
		if (holder instanceof StorageHolder) {
			// Save the content
			HumanEntity player = event.getPlayer();
			YamlDataManager dataManager = YamlDataManager.getInstance(plugin);
			StorageContent savedContent = dataManager.get(player);

			// If the content has changet, save it
			if (savedContent.hasChanged(inventory)) {
				this.plugin.info("Se guarda");
				savedContent.setContent(inventory.getContents());
				dataManager.put(player, savedContent);
			}
		}
	}
}
