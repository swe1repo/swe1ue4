package at.swe1ue4.pluginHelpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OsmHandler extends DefaultHandler {
	static Integer idCount = 0;
	static String cityTmp = null;
	static String streetTmp = null;
	static Map<String, Integer> streetMap = new HashMap<String, Integer>();
	static Map<String, Integer> buildupMap = new HashMap<String, Integer>(); 
	static Map<Integer, String> lookupMap = new HashMap<Integer, String>();
	static Stack<String> elementStack = new Stack<String>();
	
	String getCityForStreet(String street) {
		Integer id = streetMap.get(street);
		
		return lookupMap.get(id);
	}
	
	public int getStreetCount() {
		return streetMap.size();
	}
	
	public int getCityCount() {
		return lookupMap.size();
	}
	
	@Override
	public void startElement(String uri,
            String localName,
            String qName,
            Attributes attributes)
     throws SAXException {
		// using the rules from http://wiki.openstreetmap.org/wiki/OSM_tags_for_routing#City
		// to determine if a way is within a city
		if(qName.equals("tag") && elementStack.peek().equals("way")) {
			
			if(attributes.getValue("k").equals("is_in") ) {
				cityTmp = attributes.getValue("v");
				
				// strip info after city
				int firstComma = cityTmp.indexOf(",");
				
				if(firstComma != -1) {
					cityTmp = cityTmp.substring(0, firstComma);
				}
				
				System.out.println("Found city " + cityTmp);
			}
			
			if(attributes.getValue("k").equals("name") ) {
				streetTmp = attributes.getValue("v");
				System.out.println("Found street " + streetTmp);
			}
			
			if(streetTmp != null && cityTmp != null) {
				streetMap.put(streetTmp, getIdForCity(cityTmp) );
				System.out.println("Found pair " + streetTmp + " / " + cityTmp);
			}
		}
		
		elementStack.push(qName);
	}
	
	@Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException {
		
		if(qName.equals("way")) {
			cityTmp = null;
			streetTmp = null;
		}
		
		elementStack.pop();
	}
	
	@Override
	public void endDocument()
            throws SAXException {
		FileWriter f = null;
		FileWriter f2 = null;
		try {
			f = new FileWriter("/Users/patrick/Documents/workspace/swe1ue4/data/streetdump");
			f2 = new FileWriter("/Users/patrick/Documents/workspace/swe1ue4/data/citydump");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Entry<String, Integer> entry : streetMap.entrySet()) {
			try {
				f.write(entry.getKey() + " " + entry.getValue() + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Entry<String, Integer> entry : buildupMap.entrySet()) {
			try {
				f2.write(entry.getKey() + " " + entry.getValue() + "\n");
				lookupMap.put(entry.getValue(), entry.getKey());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		buildupMap = new HashMap<String, Integer>();
	}
	
	Integer getIdForCity(String city) {
		Integer retVal = 0;
		
		if( (retVal = buildupMap.get(city)) != null) {
			return retVal;
		} else {
			buildupMap.put(city, idCount);
			return idCount++; 
		}
	}
}
