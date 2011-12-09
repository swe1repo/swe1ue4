package at.swe1ue4.pluginHelpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	static Integer idTmp = 0;
	
	// final results
	static Map<String, Integer> streetMap = new HashMap<String, Integer>();
	static Map<String, Integer> buildupMap = new HashMap<String, Integer>(); 
	static Map<Integer, String> lookupMap = new HashMap<Integer, String>();
	
	// parsing variables
	static Stack<String> elementStack = new Stack<String>();
	static List<Integer> streetIdList = new ArrayList<Integer>();
	static List< Pair< List<Integer>, String > > relationList = new ArrayList< Pair< List<Integer>, String > >();
	static Map< Integer, String > streetNameIdPairs = new HashMap< Integer, String >();
	
	// control flow
	static boolean isCityRelation = false;
	static int adminLevelCount = 0;
	
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
	
	public boolean containsStreet(String street) {
		return streetMap.containsKey(street);
	}
	
	public boolean containsCity(String city) {
		return lookupMap.containsValue(city);
	}
	
	@Override
	public void startElement(String uri,
            String localName,
            String qName,
            Attributes attributes)
     throws SAXException {
		// using the rules from http://wiki.openstreetmap.org/wiki/OSM_tags_for_routing#City
		// to determine if a way is within a city
		
		// record city - street relations
		if(qName.equals("way")) {
			idTmp = Integer.parseInt( attributes.getValue("id") );
		} if(qName.equals("member") && elementStack.peek().equals("relation") && attributes.getValue("type").equals("way")) {
			streetIdList.add( Integer.parseInt( attributes.getValue("ref") ) );
		} else if(qName.equals("tag") && elementStack.peek().equals("way")) {
			// record is_in - relations
			if(attributes.getValue("k").equals("is_in") ) {
				cityTmp = attributes.getValue("v");
				
				// strip info after city
				int firstComma = cityTmp.indexOf(",");
				
				if(firstComma != -1) {
					cityTmp = cityTmp.substring(0, firstComma);
				}
				
				//System.out.println("Found city " + cityTmp);
			}else if(attributes.getValue("k").equals("name") ) {
				streetTmp = attributes.getValue("v");
				streetNameIdPairs.put(idTmp, streetTmp);
				
				//System.out.println("Found street " + streetTmp);
			}
			
			if(streetTmp != null && cityTmp != null) {
				streetMap.put(streetTmp, getIdForCity(cityTmp) );
				System.out.println("Found pair " + streetTmp + " / " + cityTmp);
			}
		} else if(qName.equals("tag") && elementStack.peek().equals("relation")) {
			if( attributes.getValue("k").equals("name") ) {
				cityTmp = attributes.getValue("v");
			} else if( attributes.getValue("k").equals("place") && attributes.getValue("v").equals("city") ) {
				isCityRelation = true;
			} else if( attributes.getValue("k").equals("admin_level") ||
					   attributes.getValue("k").equals("boundary") ) {
				adminLevelCount++;
				
				if( adminLevelCount == 2 ) {
					isCityRelation = true;
				}
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
		} else if(qName.equals("relation")) {
			if( isCityRelation == true ) {
				relationList.add(new Pair< List<Integer>, String >(streetIdList, cityTmp));
				getIdForCity(cityTmp);
			}
			
			streetIdList.clear();
			cityTmp = null;
			adminLevelCount = 0;
			isCityRelation = false;
		}
		
		elementStack.pop();
	}
	
	@Override
	public void endDocument()
            throws SAXException {
		for(Pair< List<Integer>, String > pair : relationList) {
			for(Integer id : pair.getFirst() ) {
				streetMap.put(getStreetForId(id), getIdForCity(pair.getSecond()) );
				System.out.println("Found pair " + getStreetForId(id) + " / " + pair.getSecond());
			}
		}
		
		relationList.clear();
		streetNameIdPairs.clear();
		
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
	
	String getStreetForId(Integer id) {
		return streetNameIdPairs.get(id);
	}
}
