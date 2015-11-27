package de.uni_mannheim.informatik.wdi.usecase.geography;

import de.uni_mannheim.informatik.wdi.Record;

public class City extends Record{
     
    private String name;
    private String region;
    private Double population;
    private Double populationDensity;
    private Double latitude;
    private Double longitude;
    private Double elevation;
    private Double rainfall;
    
    public City(String identifier, String provenance) {
        super(identifier, provenance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }
    
    @Override
	public boolean equals(Object obj) {
		// simplified equality checking for the union conflict resolution
		// you should use the IDs here which you also used in the identity resolution
		if(obj instanceof City) {
			City c = (City)obj;
			return getName().equals(c.getName());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}
