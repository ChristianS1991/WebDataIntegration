package de.uni_mannheim.informatik.wdi.usecase.geography;

import java.util.LinkedList;
import java.util.List;

import de.uni_mannheim.informatik.wdi.Record;

public class Country extends Record{

    private String name;
    private String code;
    private double area;
    private double population;
    private double populationDensity;
    private double gini;
    private double gdp;
    private double longitude;
    private double latitude;
    private List<City> cities = new LinkedList<City>();
    
    
    public Country(String identifier, String provenance) {
        super(identifier, provenance);
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public double getArea() {
        return area;
    }


    public void setArea(double area) {
        this.area = area;
    }


    public double getPopulation() {
        return population;
    }


    public void setPopulation(double population) {
        this.population = population;
    }


    public double getPopulationDensity() {
        return populationDensity;
    }


    public void setPopulationDensity(double populationDensity) {
        this.populationDensity = populationDensity;
    }


    public double getGini() {
        return gini;
    }


    public void setGini(double gini) {
        this.gini = gini;
    }


    public double getGdp() {
        return gdp;
    }


    public void setGdp(double gdp) {
        this.gdp = gdp;
    }


    public double getLongitude() {
        return longitude;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public List<City> getCities() {
        return cities;
    }


    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    
    
    

    
    
}
