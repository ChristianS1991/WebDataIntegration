package de.uni_mannheim.informatik.wdi.usecase.geography;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;

import de.uni_mannheim.informatik.wdi.MatchableFactory;

public class CountryFactory extends MatchableFactory<Country>{

    @Override
    public Country createModelFromElement(Node node, String provenanceInfo) {
        String identifier = node.getAttributes().getNamedItem("id").getNodeValue();
        Country country = new Country(identifier, provenanceInfo);
        
        String name = getValueFromChildElement(node, "country_name");
        if(name != null){
            country.setName(name);
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
        
        
        String populationDensityString = getValueFromChildElement(node, "country_popoulation_density");
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
        
        country.setCities(getObjectListFromChildElement(node, "cities", "city", new CityFactory(), provenanceInfo));
        
        return country;
    }

}
