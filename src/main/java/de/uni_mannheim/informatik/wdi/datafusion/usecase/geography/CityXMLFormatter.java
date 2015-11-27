package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.uni_mannheim.informatik.wdi.datafusion.XMLFormatter;

public class CityXMLFormatter extends XMLFormatter<FusableCity>{
	
	//CountryXMLFormatter countryFormatter = new CountryXMLFormatter();
	
	@Override
	public Element createRootElement(Document doc) {
		return doc.createElement("cities");
	}

	@Override
	public Element createElementFromRecord(FusableCity record, Document doc) {
		Element city = doc.createElement("city");
		
		city.appendChild(createTextElement("id", record.getIdentifier(), doc));
		//city.appendChild(createTextElement("name", record.getName(), doc));
		
		city.appendChild(createTextElementWithProvenance("name", record.getName(), record.getMergedAttributeProvenance(FusableCity.NAME),doc));
		city.appendChild(createTextElementWithProvenance("region", record.getRegion(), record.getMergedAttributeProvenance(FusableCity.REGION),doc));
		city.appendChild(createTextElementWithProvenance("population",String.valueOf(record.getPopulation()), record.getMergedAttributeProvenance(FusableCity.POPULATION),doc));
		city.appendChild(createTextElementWithProvenance("population_densitiy", String.valueOf(record.getPopulationDensity()), record.getMergedAttributeProvenance(FusableCity.POPULATION_DENSITY),doc));
		city.appendChild(createTextElementWithProvenance("latitude", String.valueOf(record.getLatitude()), record.getMergedAttributeProvenance(FusableCity.LATITUDE),doc));
		city.appendChild(createTextElementWithProvenance("longitude", String.valueOf(record.getLongitude()), record.getMergedAttributeProvenance(FusableCity.LONGITUDE),doc));
		city.appendChild(createTextElementWithProvenance("elevation", String.valueOf(record.getElevation()), record.getMergedAttributeProvenance(FusableCity.ELEVATION),doc));

		return city;
	}
	
	protected Element createTextElementWithProvenance(String name, String value, String provenance, Document doc) {
		Element elem = createTextElement(name, value, doc);
		elem.setAttribute("provenance", provenance);
		return elem;
	}

}
