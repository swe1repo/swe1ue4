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
	
	public OsmHelper() {
		handler = new OsmHandler();
	}
	
	public String getCityForStreet(String street) {
		return handler.getCityForStreet(street);
	}
	
	public void rebuildOsmIndex() {
		synchronized( MainServer.getOsmFilepath() ) {
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
		}
	}
}
