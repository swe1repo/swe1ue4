package at.swe1ue4.unitTests;

import static org.junit.Assert.*;
import at.swe1ue4.network.*;
import org.junit.*;

public class NetworkTest {
	
	@Test
	public void testGetPort() {
		MainServer ms = new MainServer(1001);
		assertEquals(ms.getPort(), 1001);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetConfigFilepath() {
		MainServer ms = new MainServer(1001);
		ms.configFilePath="./config/";
		assertEquals(ms.getConfigFilepath(), ms.configFilePath);
	}
}
