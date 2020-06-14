package es.mithrandircraft.storage;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Holder than represent a Storage. It is used for diference a normal chest to a
 * Storage
 * 
 * @author andres.morales
 *
 */
public class StorageHolder implements InventoryHolder {

	@Override
	public Inventory getInventory() {
		throw new UnsupportedOperationException();
	}
}
