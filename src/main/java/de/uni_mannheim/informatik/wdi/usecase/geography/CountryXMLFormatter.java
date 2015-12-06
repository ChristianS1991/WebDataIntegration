package de.uni_mannheim.informatik.wdi.usecase.geography;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.uni_mannheim.informatik.wdi.datafusion.XMLFormatter;

public class CountryXMLFormatter extends XMLFormatter<FusableCountry> {

	CityXMLFormatter cityFormatter = new CityXMLFormatter();
	
	@Override
	public Element createRootElement(Document doc) {
		return doc.createElement("countries");
	}

	@Override
	public Element createElementFromRecord(FusableCountry record, Document doc) {
		Element country = doc.createElement("country");

		country.appendChild(createTextElement("id", record.getIdentifier(), doc));
		
		country.appendChild(createTextElementWithProvenance("name", record.getName(), record.getMergedAttributeProvenance(FusableCountry.NAME),doc));
		country.appendChild(createTextElementWithProvenance("code", record.getCode(), record.getMergedAttributeProvenance(FusableCountry.CODE),doc));
		country.appendChild(createTextElementWithProvenance("area", String.valueOf(record.getArea()), record.getMergedAttributeProvenance(FusableCountry.AREA),doc));
		country.appendChild(createTextElementWithProvenance("population", String.valueOf(record.getPopulation()), record.getMergedAttributeProvenance(FusableCountry.POPULATION),doc));
		country.appendChild(createTextElementWithProvenance("population_density", String.valueOf(record.getPopulationDensity()), record.getMergedAttributeProvenance(FusableCountry.POPULATION_DENSITY),doc));
		country.appendChild(createTextElementWithProvenance("gini", String.valueOf(record.getGini()), record.getMergedAttributeProvenance(FusableCountry.GINI),doc));
		country.appendChild(createTextElementWithProvenance("gdp", String.valueOf(record.getGdp()), record.getMergedAttributeProvenance(FusableCountry.GDP),doc));
//		country.appendChild(createTextElementWithProvenance("latitude", String.valueOf(record.getLatitude()), record.getMergedAttributeProvenance(FusableCountry.LATITUDE),doc));
//		country.appendChild(createTextElementWithProvenance("longitude", String.valueOf(record.getLongitude()), record.getMergedAttributeProvenance(FusableCountry.LONGITUDE),doc));
		country.appendChild(createCitiesElement(record, doc));
		
		return country;
	}

	protected Element createTextElementWithProvenance(String name, String value, String provenance, Document doc) {
		Element elem = createTextElement(name, value, doc);
		elem.setAttribute("provenance", provenance);
		return elem;
	}
	
	protected Element createCitiesElement(FusableCountry record, Document doc) {
		Element cityRoot = cityFormatter.createRootElement(doc);
		cityRoot.setAttribute("provenance", record.getMergedAttributeProvenance(FusableCountry.CITIES));
		
		for(City c : record.getCities()) {
			cityRoot.appendChild(cityFormatter.createElementFromRecord(c, doc));
		}
		
		return cityRoot;
	}

	
	
}
