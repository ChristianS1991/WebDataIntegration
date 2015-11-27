package de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Node;

import de.uni_mannheim.informatik.wdi.MatchableFactory;
import de.uni_mannheim.informatik.wdi.datafusion.FusableFactory;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;

public class FusableCountryFactory extends MatchableFactory<FusableCountry> implements FusableFactory<FusableCountry>{

	@Override
	public FusableCountry createModelFromElement(Node node, String provenanceInfo) {
		String identifier = node.getAttributes().getNamedItem("id").getNodeValue();
		
		// create the object with id and provenance information
		FusableCountry country = new FusableCountry(identifier, provenanceInfo);
		
		// fill the attributes
		 String name = getValueFromChildElement(node, "country_name");
	        if(name != null){
	            country.setName(name);;
	        }
	        
	        
	        String code = getValueFromChildElement(node, "country_code");
	        if(code != null){
	            country.setCode(code);
	        }
	        
	        
	        String areaString = getValueFromChildElement(node, "country_area");
	        if(areaString != null){
	            double area = Double.parseDouble(areaString);
	            country.setArea(area);
	        }
	        
	        
	        String populationString = getValueFromChildElement(node, "country_population");
	        if(populationString != null){
	            double population = Double.parseDouble(populationString);
	            country.setPopulation(population);
	        }
	        
	        
	        String populationDensityString = getValueFromChildElement(node, "country_population_density");
	        if(populationDensityString != null){
	            double populationDensity = Double.parseDouble(populationDensityString);
	            country.setPopulationDensity(populationDensity);            
	        }

	        
	        String giniString = getValueFromChildElement(node, "country_gini");
	        if(giniString != null){
	            double gini = Double.parseDouble(giniString);
	            country.setGini(gini);            
	        }

	        
	        String gdpString = getValueFromChildElement(node, "country_gdp");
	        if(gdpString != null){
	            double gdp = Double.parseDouble(gdpString);
	            country.setGdp(gdp);            
	        }

	        
	        String longtitudeString = getValueFromChildElement(node, "country_longtitude");
	        if (longtitudeString != null) {
	            double longtitude = Double.parseDouble(longtitudeString);
	            country.setLongitude(longtitude);
	        }
	        
	        String latitudeString = getValueFromChildElement(node, "country_latitude");
	        if (latitudeString != null) {
	            double latitude = Double.parseDouble(latitudeString);
	            country.setLatitude(latitude);
	        }
			
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
