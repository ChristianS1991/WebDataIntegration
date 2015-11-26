package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.uni_mannheim.informatik.wdi.datafusion.XMLFormatter;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityXMLFormatter extends XMLFormatter<City>{
	
	@Override
	public Element createRootElement(Document doc) {
		return doc.createElement("cities");
	}

	@Override
	public Element createElementFromRecord(City record, Document doc) {
		Element city = doc.createElement("city");
		
		city.appendChild(createTextElement("country", record.getName(), doc));
		
		return city;
	}

}
