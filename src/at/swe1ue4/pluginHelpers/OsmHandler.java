package at.swe1ue4.pluginHelpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OsmHandler extends DefaultHandler {
	static Map<String, Integer> streetMap = new HashMap<String, Integer>();
	static Map<Integer, String> cityMap = new HashMap<Integer, String>();
	static Stack<String> elementStack = new Stack<String>();
	
	String getCityForStreet(String street) {
		Integer cityId = streetMap.get( street );
		
		return cityMap.get( cityId );
	}
	
	@Override
	public void startElement(String uri,
            String localName,
            String qName,
            Attributes attributes)
     throws SAXException {
		if(qName.equals("tag") && elementStack.peek().equals("way")) {
			;
		}
		
		elementStack.push(qName);
	}
	
	@Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException {
      elementStack.pop();
    }
}
