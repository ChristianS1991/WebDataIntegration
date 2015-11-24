package de.uni_mannheim.informatik.wdi.usecase.geography.comperators.city;

import de.uni_mannheim.informatik.wdi.identityresolution.matching.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityPopulationDensityComparatorPercentage extends Comparator<City>{

    PercentageSimilarity sim = new PercentageSimilarity(0.05d);
    
    @Override
    public double compare(City record1, City record2) {
        Double first = record1.getPopulationDensity();
        Double second = record2.getPopulationDensity();
        
        return sim.calculate(first, second);
    }

}