package de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country;

import de.uni_mannheim.informatik.wdi.identityresolution.matching.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.string.WinklerSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.Country;

public class CountryNameComperatorWinkler extends Comparator<Country>{

    private WinklerSimilarity sim = new WinklerSimilarity();
    
    @Override
    public double compare(Country record1, Country record2) {
        //preprocessing
        String first = record1.getName().replaceAll("[^\\w^\\s]", "").toLowerCase();
        String second = record2.getName().replaceAll("[^\\w^\\s]", "").toLowerCase();
        
        return sim.calculate(first, second);
    }

    
    
}
