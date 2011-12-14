package at.swe1ue4.pluginHelpers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import at.swe1ue4.network.MainServer;
import org.xml.sax.SAXException;

public class OsmHelper {
	OsmHandler handler;
	long lastReloadTime = 0L;
	
	public OsmHelper() {
		handler = new OsmHandler();
	}
	
	public long getReloadTime() {
		return lastReloadTime / 1000;
	}
	
	public int getStreetCount() {
		return handler.getStreetCount();
	}
	
	public boolean isCity(String text) {
		return handler.containsCity(text);
	}
	
	public boolean isStreet(String text) {
		return handler.containsStreet(text);
	}
	
	public int getCityCount() {
		return handler.getCityCount();
	}
	
	public String getCityForStreet(String street) {
		return handler.getCityForStreet(street);
	}
	
	public void rebuildOsmIndex() {
		synchronized( MainServer.getOsmFilepath() ) {
			long start = System.currentTimeMillis();
			
			// obtain a SAXParserFactory
			SAXParserFactory pf = SAXParserFactory.newInstance();
			
			// obtain a SAXParser
			SAXParser parser = null;
			try {
				parser = pf.newSAXParser();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// start parsing the file
			try {
				parser.parse(new File( MainServer.getOsmFilepath() ), handler);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			lastReloadTime = System.currentTimeMillis() - start;
		}
	}
}
