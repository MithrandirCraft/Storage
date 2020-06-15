package es.mithrandircraft.storage.data;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
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

	public StorageContent(YamlConfiguration yaml) {
		this.name = yaml.getString("name");
		this.uuid = UUID.fromString(yaml.getString("uuid"));
		this.hashCode = yaml.getInt("hashCode");
		List<?> list = yaml.getList("content");
		ItemStack[] inventory = new ItemStack[list.size()];
		for(int i = 0; i < list.size(); i++) {
			Object aux = list.get(i);
			inventory[i] = aux == null ? null : (ItemStack) aux;
		}
		this.content = inventory;
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
	
	public YamlConfiguration toYaml() {
		YamlConfiguration yaml = new YamlConfiguration();
		yaml.set("name", this.name);
		yaml.set("uuid", this.uuid.toString());
		yaml.set("hashCode", this.hashCode);
		yaml.set("content", this.content);
		return yaml;
	}
}
