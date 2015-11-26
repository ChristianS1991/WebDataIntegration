package de.uni_mannheim.informatik.wdi.usecase.geography;


import de.uni_mannheim.informatik.wdi.Record;

public class Country extends Record{

    private String name;
    private String code;
    private Double area;
    private Double population;
    private Double populationDensity;
    private Double gini;
    private Double gdp;
    private Double longitude;
    private Double latitude;
    
    
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


    public Double getArea() {
        return area;
    }


    public void setArea(Double area) {
        this.area = area;
    }


    public Double getPopulation() {
        return population;
    }


    public void setPopulation(Double population) {
        this.population = population;
    }


    public Double getPopulationDensity() {
        return populationDensity;
    }


    public void setPopulationDensity(Double populationDensity) {
        this.populationDensity = populationDensity;
    }


    public Double getGini() {
        return gini;
    }


    public void setGini(Double gini) {
        this.gini = gini;
    }


    public Double getGdp() {
        return gdp;
    }


    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }


    public Double getLongitude() {
        return longitude;
    }


    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public Double getLatitude() {
        return latitude;
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    
    

    
    
}
