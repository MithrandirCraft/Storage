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

	private Properties properties;

	/**
	 * Create the instance
	 * 
	 * @param plugin
	 */
	private PluginLanguaje(Storage plugin) {
		properties = new Properties();
		File langFile = new File(plugin.getPluginFolder(), "lang.properties");
		try (InputStream input = new FileInputStream(langFile)) {
			properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
		} catch (IOException e) {
			plugin.error("Can not load the languaje", e);
		}
	}

	/**
	 * Return a complete message applying the format
	 * 
	 * @param message message
	 * @return Message with the format
	 */
	public String formatMessage(String message) {
		String prefix = properties.getProperty("prefix");
		message = prefix + message;
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	/**
	 * Get the languaje property
	 * 
	 * @param key key of the propertie
	 * @return property
	 */
	public String getString(String key) {
		return ChatColor.translateAlternateColorCodes('&', properties.getProperty(key));
	}

	/**
	 * Get a complete message replacing the parameters
	 * 
	 * @param key          Key of the property
	 * @param replacements arguments
	 * @return Message
	 */
	public String getMessage(String key, Object... replacements) {
		String message = this.formatMessage(this.getString(key));
		for (Object replace : replacements) {
			message = message.replaceFirst("\\{\\}", replace.toString());
		}
		return message;
	}

	// ============================ STATICS ===========================

	private static PluginLanguaje instance;

	public static PluginLanguaje getInstance(Storage plugin) {
		if (instance == null) {
			instance = new PluginLanguaje(plugin);
		}
		return instance;
	}
}
