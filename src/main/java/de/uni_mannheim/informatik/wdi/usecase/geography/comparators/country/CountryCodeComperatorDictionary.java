package de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country;

import de.uni_mannheim.informatik.wdi.identityresolution.matching.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.geo.CountryCodeSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.Country;

public class CountryCodeComperatorDictionary extends Comparator<Country>{

    CountryCodeSimilarity sim = new CountryCodeSimilarity();
    
    @Override
    public double compare(Country record1, Country record2) {
        String first = record1.getCode();
        String second = record2.getCode();

        return sim.calculate(first, second);
    }

}
