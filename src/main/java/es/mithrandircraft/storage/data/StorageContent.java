package es.mithrandircraft.storage.data;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Class with the information of storage
 * 
 * @author andrescol24
 *
 */
public class StorageContent {

	private String name;
	private UUID uuid;
	private ItemStack[] content;
	private int hashCode;

	/**
	 * Create a instance of this class, assigning the name, content and hashCode
	 * 
	 * @param player    Player
	 * @param inventory inventory
	 */
	public StorageContent(HumanEntity player, Inventory inventory) {
		this.name = player.getName();
		this.content = inventory.getContents();
		this.hashCode = Arrays.hashCode(this.content);
		this.uuid = player.getUniqueId();
	}

	/**
	 * Create a instance of this class, assigning the name, content and hashCode
	 * 
	 * @param player    Player
	 * @param inventory inventory
	 */
	public StorageContent(HumanEntity player) {
		this.name = player.getName();
		this.uuid = player.getUniqueId();
	}

	public String getName() {
		return name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public ItemStack[] getContent() {
		return content;
	}

	public int getHashCode() {
		return hashCode;
	}

	public void setContent(ItemStack[] content) {
		this.content = content;
		this.hashCode = Arrays.hashCode(this.content);
	}

	/**
	 * Verify if the inventory content has changed
	 * 
	 * @param inventory Inventory
	 * @return <code>true</code> if it has changed
	 */
	public boolean hasChanged(Inventory inventory) {
		return this.hashCode != Arrays.hashCode(inventory.getContents());
	}
}
