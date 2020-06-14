package es.mithrandircraft.storage.listener;

import java.util.Arrays;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import es.mithrandircraft.storage.Storage;
import es.mithrandircraft.storage.other.StorageHolder;

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
			plugin.log("Yeah!! hashCode: " + Arrays.hashCode(event.getInventory().getContents()));
		}
	}
}
