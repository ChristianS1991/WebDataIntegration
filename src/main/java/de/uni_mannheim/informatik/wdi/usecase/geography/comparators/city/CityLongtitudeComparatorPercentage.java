package de.uni_mannheim.informatik.wdi.usecase.geography.comparators.city;

import de.uni_mannheim.informatik.wdi.identityresolution.matching.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityLongtitudeComparatorPercentage extends Comparator<City>{

    PercentageSimilarity sim = new PercentageSimilarity(0.005d);
    
    @Override
    public double compare(City record1, City record2) {
        Double first = record1.getLongitude();
        Double second = record2.getLongitude();
        
        if(first < 0 && second < 0){
            first = Math.abs(first);
            second = Math.abs(second);
        }
        else if ((first < 0 && second > 0) || (first > 0 && second < 0)){
            return 0d;
        }
        
        
        return sim.calculate(first, second);
    }

}