package es.mithrandircraft.storage;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Interface that abstract public methods that can be use across the plugin
 * 
 * @author andrescol24
 *
 */
public interface Storage {

	/**
	 * Write a console message
	 * 
	 * @param message the message
	 */
	void log(String message);

	/**
	 * Write a console warning message
	 * 
	 * @param message Message to write
	 */
	void warn(String message);

	/**
	 * Write a severe error to console
	 * 
	 * @param message   Message
	 * @param exception the throws exception
	 */
	void error(String message, Throwable exception);

	/**
	 * Get the file configuration
	 * 
	 * @return the configuration
	 */
	FileConfiguration getFileConfiguration();

	/**
	 * Get the folder plugin
	 * 
	 * @return Plugin's folder
	 */
	File getPluginFolder();
}
