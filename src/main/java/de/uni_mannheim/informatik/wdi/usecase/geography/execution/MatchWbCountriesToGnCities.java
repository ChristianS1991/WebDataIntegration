package de.uni_mannheim.informatik.wdi.usecase.geography.execution;

import java.io.File;
import java.util.List;

import de.uni_mannheim.informatik.wdi.DataSet;
import de.uni_mannheim.informatik.wdi.identityresolution.blocking.CrossProductBlocker;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.Correspondence;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.LinearCombinationMatchingRule;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.MatchingEngine;
import de.uni_mannheim.informatik.wdi.usecase.geography.Country;
import de.uni_mannheim.informatik.wdi.usecase.geography.CountryFactory;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryCodeComperatorDictionary;

public class MatchWbCountriesToGnCities {

    public static void main(String[] args) throws Exception{
        
        LinearCombinationMatchingRule<Country> rule = new LinearCombinationMatchingRule<Country>(0.99);
        
        rule.addComparator(new CountryCodeComperatorDictionary(), 1d);
        
        CrossProductBlocker<Country> blocker = new CrossProductBlocker<Country>();

        
        MatchingEngine<Country> engine = new MatchingEngine<Country>(rule, blocker);
        
        
        DataSet<Country> ds1 = new DataSet<>();
        DataSet<Country> ds2 = new DataSet<>();
        ds1.loadFromXML(
                new File("usecase/geography/input/worldbank_countries.xml"),
                new CountryFactory(), "/countries/country");
        ds2.loadFromXML(
                new File("usecase/geography/input/geonames_cities.xml"),
                new CountryFactory(), "/countries/country");
        
        List<Correspondence<Country>> correspondences = engine
                .runMatching(ds1, ds2);
        
        engine.writeCorrespondences(correspondences, new File("usecase/geography/output/wb_countries_gn_cities_correspondences.csv"));
        
       
    }
}
