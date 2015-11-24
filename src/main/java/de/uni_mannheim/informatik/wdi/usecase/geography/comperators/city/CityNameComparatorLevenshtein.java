package de.uni_mannheim.informatik.wdi.usecase.geography.comperators.city;

import de.uni_mannheim.informatik.wdi.identityresolution.matching.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.string.LevenshteinSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityNameComparatorLevenshtein extends Comparator<City>{

    LevenshteinSimilarity sim = new LevenshteinSimilarity();
    
    @Override
    public double compare(City record1, City record2) {
        String first = record1.getName().replaceAll("[^\\w^\\s]", "").toLowerCase();
        String second = record2.getName().replaceAll("[^\\w^\\s]", "").toLowerCase();
        
        return sim.calculate(first, second);
    }

}