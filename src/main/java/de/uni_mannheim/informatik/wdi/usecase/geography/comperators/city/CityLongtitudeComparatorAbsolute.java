package de.uni_mannheim.informatik.wdi.usecase.geography.comperators.city;

import de.uni_mannheim.informatik.wdi.identityresolution.matching.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.AbsoluteDifferenceSimilarity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityLongtitudeComparatorAbsolute extends Comparator<City>{

    AbsoluteDifferenceSimilarity sim = new AbsoluteDifferenceSimilarity(0.25d);
    
    @Override
    public double compare(City record1, City record2) {
        Double first = record1.getLongitude();
        Double second = record2.getLongitude();
        
        if(first < 0 || second < 0){
            first = Math.abs(first);
            second = Math.abs(second);
        }
        
        return sim.calculate(first, second);
    }

}