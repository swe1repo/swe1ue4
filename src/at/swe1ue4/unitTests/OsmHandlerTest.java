package at.swe1ue4.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import at.swe1ue4.plugins.*;
import at.swe1ue4.textparser.*;
import at.swe1ue4.network.*;

public class OsmHandlerTest {
	static NaviPlugin plugin = new NaviPlugin();
	static TextParser parser = new TextParser();

	@Test
	public void testIsRebuild() {		
		assertTrue( plugin.isReload( parser.readText("Rebuild the street map") ) );
		assertFalse( plugin.isReload( parser.readText("Where is Arbeiterstrandbadstrasse?") ) );
	}
	
	@Test
	public void testRebuild() {
		MainServer.setOsmFilepath("/Users/patrick/Documents/workspace/swe1ue4/data/austria.osm");
		
		String[] input = parser.readText("Rebuild the street map");
		
		String output = plugin.getMessageForString(input);
		
		assertTrue(output.startsWith("I have successfully rebuilt the street map"));
	}
	
	@Test
	public void testGetCityCount() {
		assertTrue( plugin.helper.getCityCount() == 4515);
	}
	
	@Test
	public void testGetStreetCount() {
		assertTrue( plugin.helper.getStreetCount() == 35600);
	}
	
	@Test
	public void testGetReloadTime() {
		assertTrue( plugin.helper.getReloadTime() > 0);
	}
	
	@Test
	public void testGetCityForStreet() {
		System.out.println(plugin.helper.getCityForStreet("neusiedlweg"));
		assertTrue( plugin.helper.getCityForStreet("neusiedlweg") == "Lassing");
	}
	
	@Test
	public void testIsCity() {
		assertTrue( plugin.helper.isCity("Gemeinde Ohlsdorf") );
	}
	
	@Test
	public void testIsStreet() {
		assertTrue( plugin.helper.isStreet("engelsweg") );
	}
}
