package de.uni_mannheim.informatik.wdi.usecase.geography.execution;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.uni_mannheim.informatik.wdi.DataSet;
import de.uni_mannheim.informatik.wdi.identityresolution.blocking.CrossProductBlocker;
import de.uni_mannheim.informatik.wdi.identityresolution.blocking.SortedNeighbourhoodBlocker;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.GoldStandard;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.MatchingEvaluator;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.Performance;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.Correspondence;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.LinearCombinationMatchingRule;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.MatchingEngine;
import de.uni_mannheim.informatik.wdi.identityresolution.model.DefaultRecordCSVFormatter;
import de.uni_mannheim.informatik.wdi.usecase.geography.Country;
import de.uni_mannheim.informatik.wdi.usecase.geography.CountryFactory;
import de.uni_mannheim.informatik.wdi.usecase.geography.blockingfunctions.CountryPopulationBlockingFunction;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryAreaComparatorPercentage;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryGdpComparatorPercentage;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryGiniComparatorPercentage;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryNameComparatorLevenshtein;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryNameComparatorOverlapLevenshtein;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryNameComperatorDisSem;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryPopulationComparatorPercentage;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryPopulationDensityComparatorPercentage;
import de.uni_mannheim.informatik.wdi.usecase.movies.Movie;

public class MatchDbpCountriesToWbCountries {

    public static void main(String[] args) throws Exception{
        
        LinearCombinationMatchingRule<Country> rule = new LinearCombinationMatchingRule<Country>(0.9);
        
        rule.addComparator(new CountryNameComparatorLevenshtein(), 1);
//        rule.addComparator(new CountryNameComperatorDisSem(), 1);
//        rule.addComparator(new CountryPopulationComperatorPercentage(), 0.3);
//        rule.addComparator(new CountryAreaComperatorPercentage(), 0.3);
//        rule.addComparator(new CountryGdpComperatorPercentage(), 0.1);
//        rule.addComparator(new CountryGiniComperatorPercentage(), 0.1);
//        rule.addComparator(new CountryPopulationDensityComperatorPercentage(), 0.2);
        
//        CrossProductBlocker<Country> blocker = new CrossProductBlocker<Country>();
        SortedNeighbourhoodBlocker<Country> blocker = new SortedNeighbourhoodBlocker<Country>(new CountryPopulationBlockingFunction(), 20);
        
        MatchingEngine<Country> engine = new MatchingEngine<Country>(rule, blocker);
        
        
        DataSet<Country> ds1 = new DataSet<>();
        DataSet<Country> ds2 = new DataSet<>();
        ds1.loadFromXML(
                new File("usecase/geography/input/dbp_countries.xml"),
                new CountryFactory(), "/countries/country");
        ds2.loadFromXML(
                new File("usecase/geography/input/worldbank_countries.xml"),
                new CountryFactory(), "/countries/country");
        
        List<Correspondence<Country>> correspondences = engine
                .runMatching(ds1, ds2);
        
        
        
        printCorrespondences(correspondences);
        
     // load the gold standard (test set)
        GoldStandard gsTest = new GoldStandard();
        gsTest.loadFromCSVFile(new File(
                "usecase/geography/goldstandard/dbp_countries_wb_countries.csv"));

        // evaluate the result
        MatchingEvaluator<Country> evaluator = new MatchingEvaluator<>(true);
        Performance perfTest = evaluator.evaluateMatching(correspondences, gsTest);
        
        // print the evaluation result
        System.out.println(String.format(
                "Precision: %.4f\nRecall: %.4f\nF1: %.4f", perfTest.getPrecision(),
                perfTest.getRecall(), perfTest.getF1()));
        
        
        engine.generateTrainingDataForLearning(ds1, ds2, gsTest).writeCSV(new File("training/countries.csv"), new DefaultRecordCSVFormatter());
    }
    
    
    private static void printCorrespondences(List<Correspondence<Country>> correspondences) {
        // sort the correspondences
        
        for(
                Correspondence<Country> corr : correspondences){
            System.out.println(corr.getFirstRecord().getName() +"(" +corr.getFirstRecord().getIdentifier()+ ")\t" + corr.getSimilarityScore() + "\t" + corr.getSecondRecord().getName() + "(" +corr.getSecondRecord().getIdentifier()+ ")");
        }
    }
    
}
