package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Node;

import de.uni_mannheim.informatik.wdi.MatchableFactory;
import de.uni_mannheim.informatik.wdi.datafusion.FusableFactory;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class FusableCountryFactory extends MatchableFactory<FusableCountry> implements FusableFactory<FusableCountry>{

	@Override
	public FusableCountry createModelFromElement(Node node, String provenanceInfo) {
		String id = getValueFromChildElement(node, "id");
		
		// create the object with id and provenance information
		FusableCountry country = new FusableCountry(id, provenanceInfo);
		
		// fill the attributes
		country.setName(getValueFromChildElement(node, "name"));
		country.setCode(getValueFromChildElement(node, "code"));
		try {
			String area = getValueFromChildElement(node, "area");
			if(area!=null && !area.isEmpty()){ 
				country.setArea(Double.valueOf(area));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String population = getValueFromChildElement(node, "population");
			if(population!=null && !population.isEmpty()){ 
				country.setPopulation(Double.valueOf(population));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String populationDensity = getValueFromChildElement(node, "population density");
			if(populationDensity!=null && !populationDensity.isEmpty()){ 
				country.setPopulationDensity(Double.valueOf(populationDensity));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String gini = getValueFromChildElement(node, "gini");
			if(gini!=null && !gini.isEmpty()){ 
				country.setGini(Double.valueOf(gini));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String gdp = getValueFromChildElement(node, "gdp");
			if(gdp!=null && !gdp.isEmpty()){ 
				country.setPopulationDensity(Double.valueOf(gdp));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String latitude = getValueFromChildElement(node, "latitude");
			if(latitude!=null && !latitude.isEmpty()){ 
				country.setPopulationDensity(Double.valueOf(latitude));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String longitude = getValueFromChildElement(node, "longitude");
			if(longitude!=null && !longitude.isEmpty()){ 
				country.setPopulationDensity(Double.valueOf(longitude));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		// load the list of actors
		List<City> cities = getObjectListFromChildElement(node, "cities", "city", new CityFactory(), provenanceInfo);
		country.setCities(cities);
		
		return country;
	}

	@Override
	public FusableCountry createInstanceForFusion(
			RecordGroup<FusableCountry> cluster) {
		
		List<String> ids = new LinkedList<>();
		
		for(FusableCountry c : cluster.getRecords()) {
			ids.add(c.getIdentifier());
		}
		
		Collections.sort(ids);
		
		String mergedId = StringUtils.join(ids, '+');
		
		return new FusableCountry(mergedId, "fused");
	}
}
