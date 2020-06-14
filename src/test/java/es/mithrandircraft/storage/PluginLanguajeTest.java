package es.mithrandircraft.storage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.mithrandircraft.storage.configuration.PluginLanguaje.LanguajeProperty;

public class PluginLanguajeTest {

	@Test
	public void testPropertiesKey() {
		LanguajeProperty property = LanguajeProperty.OPEN_PLAYER_NOTFOUND;
		String key = property.getKey();
		assertEquals("open.player.notfound", key);
	}
}
