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
		String id = getValueFromChildElement(node, "id");
		
		// create the object with id and provenance information
		FusableCity city = new FusableCity(id, provenanceInfo);
		
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
