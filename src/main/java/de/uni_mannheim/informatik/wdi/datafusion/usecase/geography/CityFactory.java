package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography;

import org.w3c.dom.Node;

import de.uni_mannheim.informatik.wdi.MatchableFactory;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityFactory extends MatchableFactory<City>{
	@Override
	public City createModelFromElement(Node node, String provenanceInfo) {
		String id = getValueFromChildElement(node, "id");
		
		// create the object with id and provenance information
		City city = new City(id, provenanceInfo);
		
		// fill the attributes
		city.setName(getValueFromChildElement(node, "name"));
		city.setRegion(getValueFromChildElement(node, "region"));
		city.setPopulation(Double.parseDouble(getValueFromChildElement(node, "population")));
		city.setPopulationDensity(Double.parseDouble(getValueFromChildElement(node, "populationDensity")));
		city.setLatitude(Double.parseDouble(getValueFromChildElement(node, "latitude")));
		city.setLongitude(Double.parseDouble(getValueFromChildElement(node, "longitude")));
		city.setElevation(Double.parseDouble(getValueFromChildElement(node, "elevation")));
		city.setRainfall(Double.parseDouble(getValueFromChildElement(node, "rainfall")));
		
		return city;
	}

}
