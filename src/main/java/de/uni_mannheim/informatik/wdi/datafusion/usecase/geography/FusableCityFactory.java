package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Node;

import de.uni_mannheim.informatik.wdi.MatchableFactory;
import de.uni_mannheim.informatik.wdi.datafusion.FusableFactory;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;


public class FusableCityFactory extends MatchableFactory<FusableCity> implements FusableFactory<FusableCity>{

	@Override
	public FusableCity createModelFromElement(Node node, String provenanceInfo) {
		String identifier = node.getAttributes().getNamedItem("id").getNodeValue();
		
		// create the object with id and provenance information
		FusableCity city = new FusableCity(identifier, provenanceInfo);
		
		// fill the attributes
		 String name = getValueFromChildElement(node, "city_name");
	        if (name != null) {
	            city.setName(name);
	        }
	        
	        String region = getValueFromChildElement(node, "city_region");
	        if (region != null) {
	            city.setRegion(region);
	        }
	        
	        String populationString = getValueFromChildElement(node, "city_population");
	        if (populationString != null) {
	            double population = Double.parseDouble(populationString);
	            city.setPopulation(population);
	        }
	        
	        String populationDensityString = getValueFromChildElement(node, "city_population_density");
	        if (populationDensityString != null) {
	            double populationDensity = Double.parseDouble(populationDensityString);
	            city.setPopulationDensity(populationDensity);
	        }
	        
	        String latitudeString = getValueFromChildElement(node, "city_latitude");
	        if (latitudeString != null) {
	            double latitude = Double.parseDouble(latitudeString);
	            city.setLatitude(latitude);
	        }
	        
	        String longtitudeString = getValueFromChildElement(node, "city_longtitude");
	        if (longtitudeString != null) {
	            double longtitude = Double.parseDouble(longtitudeString);
	            city.setLongitude(longtitude);
	        }
	        
	        String elevationString = getValueFromChildElement(node, "city_elevation");
	        if (elevationString != null) {
	            double elevation = Double.parseDouble(elevationString);
	            city.setElevation(elevation);
	        }
	        
	        String rainfallString = getValueFromChildElement(node, "city_rainfall");
	        if (rainfallString != null) {
	            double rainfall = Double.parseDouble(rainfallString);
	            city.setRainfall(rainfall);
	        }
		return city;
	}
	
	@Override
	public FusableCity createInstanceForFusion(RecordGroup<FusableCity> cluster) {
		
		List<String> ids = new LinkedList<>();
		
		for(FusableCity c : cluster.getRecords()) {
			ids.add(c.getIdentifier());
		}
		
		Collections.sort(ids);
		
		String mergedId = StringUtils.join(ids, '+');
		
		return new FusableCity(mergedId, "fused");
	}

	

}
