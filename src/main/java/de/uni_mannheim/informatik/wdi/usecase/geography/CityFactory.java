package de.uni_mannheim.informatik.wdi.usecase.geography;

import org.w3c.dom.Node;

import de.uni_mannheim.informatik.wdi.MatchableFactory;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityFactory extends MatchableFactory<City> {

    @Override
    public City createModelFromElement(Node node, String provenanceInfo) {
        String identifier = node.getAttributes().getNamedItem("id").getNodeValue();
        City city = new City(identifier, provenanceInfo);
        
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

}
