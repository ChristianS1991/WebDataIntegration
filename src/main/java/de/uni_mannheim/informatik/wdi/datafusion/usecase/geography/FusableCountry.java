package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import de.uni_mannheim.informatik.wdi.datafusion.Fusable;
import de.uni_mannheim.informatik.wdi.usecase.geography.Country;

public class FusableCountry extends Country implements Fusable{
	
	public static final String NAME = "Name";
	public static final String CODE = "Code";
	public static final String AREA = "Area";
	public static final String POPULATION = "Population";
	public static final String POPULATION_DENSITY = "PopulationDensity";
	public static final String GINI = "GINI";
	public static final String GDP = "GDP";
	public static final String LATITUDE = "Latitude";
	public static final String LONGITUDE = "Longitude";
	public static final String CITIES = "Cities";
	
private Map<String, Collection<String>> provenance = new HashMap<>();
	
	public void setRecordProvenance(Collection<String> provenance) {
		this.provenance.put("RECORD", provenance);
	}
	public Collection<String> getRecordProvenance() {
		return provenance.get("RECORD");
	}
	
	public void setAttributeProvenance(String attribute, Collection<String> provenance) {
		this.provenance.put(attribute, provenance);
	}
	public Collection<String> getAttributeProvenance(String attribute) {
		return provenance.get(attribute);
	}
	public String getMergedAttributeProvenance(String attribute) {
		Collection<String> prov = provenance.get(attribute);
		
		if(prov!=null) {
			return StringUtils.join(prov, "+");
		} else {
			return "";
		}
	}
	
	public FusableCountry(String identifier, String provenance) {
		super(identifier, provenance);
	}

	@Override
	public Collection<String> getAttributeNames() {
		return Arrays.asList(new String[] {NAME, CODE, AREA, POPULATION, POPULATION_DENSITY, GINI, GDP, LATITUDE, LONGITUDE});
	}

	@Override
	public boolean hasValue(String attributeName) {
		switch (attributeName) {
		case NAME:
			return getName()!=null && !getName().isEmpty();
		case CODE:
			return getCode()!=null && !getCode().isEmpty();
		case AREA:
			return getArea()!=null;
		case POPULATION:
			return getPopulation()!=null;
		case POPULATION_DENSITY:
			return getPopulationDensity()!=null;
		case GINI:
			return getGini()!=null;
		case GDP:
			return getGdp()!=null;
		case LATITUDE:
			return getLatitude()!=null;
		case LONGITUDE:
			return getLongitude()!=null;
		case CITIES:
			return getCities()!=null && getCities().size()>0;
		default:
			return false;
		}
	}
	
	@Override
	public String toString() {
		return String.format("[Country: %s / %s / %s / %s / %s / %s / %s / %s / %s / &s]", getName(), getCode(), getArea(), getPopulation(), getPopulationDensity(), getGini(), getGdp(), getLatitude(), getLongitude());
	}


	

}
