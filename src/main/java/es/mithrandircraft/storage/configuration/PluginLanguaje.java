/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package es.mithrandircraft.storage.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.bukkit.ChatColor;

import es.mithrandircraft.storage.Storage;

/**
 * Singleton that contains the languaje configuration
 * 
 * @author andrescol24
 */

public class PluginLanguaje {

	private Storage plugin;

	/**
	 * Create the instance
	 * 
	 * @param plugin
	 */
	private PluginLanguaje(Storage plugin) {
		this.plugin = plugin;
	}

	/**
	 * Get the languaje property
	 * 
	 * @param key key of the propertie
	 * @return property
	 */
	private String getString(String key) {
		Properties properties = new Properties();
		File langFile = new File(plugin.getPluginFolder(), "lang.properties");
		try (InputStream input = new FileInputStream(langFile)) {
			properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
			return properties.getProperty(key);
		} catch (IOException e) {
			plugin.error("Can not load the languaje property: " + key, e);
		}
		return null;
	}

	/**
	 * Get a complete message replacing the parameters
	 * 
	 * @param property     the property
	 * @param replacements arguments
	 * @return Message
	 */
	public String getMessage(LanguajeProperty property, Object... replacements) {
		String key = property.getKey();
		String message = this.getString(key);
		if (message != null) {
			for (Object replace : replacements) {
				message = message.replaceFirst("\\{\\}", replace.toString());
			}
			message = ChatColor.translateAlternateColorCodes('&', message);
			return message;
		}
		plugin.warn("The propertiy " + key + " was not found, check it!");
		return "Please check the " + key + " languaje configuration";

	}

	// ============================ STATICS ===========================

	private static PluginLanguaje instance;

	public static PluginLanguaje getInstance(Storage plugin) {
		if (instance == null) {
			instance = new PluginLanguaje(plugin);
		}
		return instance;
	}

	/**
	 * Enum for the list of messages
	 * 
	 * @author andrescol24
	 *
	 */
	public enum LanguajeProperty {
		OPEN_PLAYER_NOTFOUND, STORAGE_NAME;

		public String getKey() {
			return this.name().replace("_", ".").toLowerCase();
		}
	}
}
