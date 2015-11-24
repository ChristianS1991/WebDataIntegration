package de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country;

import de.uni_mannheim.informatik.wdi.identityresolution.matching.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.Country;

public class CountryPopulationComparatorPercentage extends Comparator<Country>{

    PercentageSimilarity sim = new PercentageSimilarity(0.05d);
    
    @Override
    public double compare(Country record1, Country record2) {
        Double first = record1.getPopulation();
        Double second = record2.getPopulation();

        return sim.calculate(first, second);
    }

}
