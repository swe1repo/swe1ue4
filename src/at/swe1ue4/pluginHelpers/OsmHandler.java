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
	static Map<String, Integer> streetMap = new HashMap<String, Integer>();
	static Map<String, Integer> buildupMap = new HashMap<String, Integer>(); 
	static Map<Integer, String> lookupMap = new HashMap<Integer, String>();
	static Stack<String> elementStack = new Stack<String>();
	
	String getCityForStreet(String street) {
		Integer id = streetMap.get(street);
		
		return lookupMap.get(id);
	}
	
	@Override
	public void startElement(String uri,
            String localName,
            String qName,
            Attributes attributes)
     throws SAXException {
		if(qName.equals("tag") && elementStack.peek().equals("way")) {
			streetMap.put(attributes.getValue("addr:street"), getIdForCity( attributes.getValue("addr:city") ));
		}
		
		elementStack.push(qName);
	}
	
	@Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException {
      elementStack.pop();
	}
	
	@Override
	public void endDocument()
            throws SAXException {
		FileWriter f = null;
		FileWriter f2 = null;
		try {
			f = new FileWriter("/Users/patrick/streetdump");
			f2 = new FileWriter("/Users/patrick/citydump");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Entry<String, Integer> entry : streetMap.entrySet()) {
			try {
				f.write(entry.getKey() + " " + entry.getValue());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Entry<String, Integer> entry : buildupMap.entrySet()) {
			try {
				f2.write(entry.getKey() + " " + entry.getValue());
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
