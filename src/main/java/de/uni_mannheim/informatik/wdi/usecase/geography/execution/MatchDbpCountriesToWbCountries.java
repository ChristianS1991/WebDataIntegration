package de.uni_mannheim.informatik.wdi.usecase.geography.execution;

import java.io.File;
import java.util.List;

import de.uni_mannheim.informatik.wdi.DataSet;
import de.uni_mannheim.informatik.wdi.identityresolution.blocking.CrossProductBlocker;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.GoldStandard;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.MatchingEvaluator;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.Performance;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.Correspondence;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.LinearCombinationMatchingRule;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.MatchingEngine;
import de.uni_mannheim.informatik.wdi.identityresolution.model.Pair;
import de.uni_mannheim.informatik.wdi.usecase.geography.Country;
import de.uni_mannheim.informatik.wdi.usecase.geography.CountryFactory;
import de.uni_mannheim.informatik.wdi.usecase.geography.comparators.country.CountryNameComparatorLevenshtein;

public class MatchDbpCountriesToWbCountries {

    public static void main(String[] args) throws Exception{
        
        LinearCombinationMatchingRule<Country> rule = new LinearCombinationMatchingRule<Country>(0.6);
        
        rule.addComparator(new CountryNameComparatorLevenshtein(), 1d);
//        rule.addComparator(new CountryNameComperatorDisSem(), 1);
//        rule.addComparator(new CountryPopulationComparatorPercentage(), 1);
//        rule.addComparator(new CountryAreaComparatorPercentage(), 1);
//        rule.addComparator(new CountryGdpComparatorPercentage(), 1);
//        rule.addComparator(new CountryGiniComperatorPercentage(), 0.1);
//        rule.addComparator(new CountryPopulationDensityComperatorPercentage(), 0.2);
        
        CrossProductBlocker<Country> blocker = new CrossProductBlocker<Country>();
//        SortedNeighbourhoodBlocker<Country> blocker = new SortedNeighbourhoodBlocker<Country>(new CountryPopulationBlockingFunction(), 20);
        
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
        
//        engine.writeCorrespondences(correspondences, new File("usecase/geography/output/dbp_countries_wb_countries_correspondences.csv"));
        
        printCorrespondences(correspondences);
        
     // load the gold standard (test set)
        GoldStandard gsTest = new GoldStandard();
        gsTest.loadFromCSVFile(new File(
                "usecase/geography/goldstandard/identityresolution/dbp_countries_wb_countries.csv"));

        List<Pair<String,String>> negs = gsTest.getPositiveExamples();
        
        for(Pair<String,String> neg : negs){
            if(gsTest.containsNegative(neg.getFirst(), neg.getSecond())){
                System.out.println(neg.getFirst() + " + " +  neg.getSecond());
            }
        }
        
        
        // evaluate the result
        MatchingEvaluator<Country> evaluator = new MatchingEvaluator<>(true);
        Performance perfTest = evaluator.evaluateMatching(correspondences, gsTest);
        
        // print the evaluation result
        System.out.println(String.format(
                "Precision: %.4f\nRecall: %.4f\nF1: %.4f", perfTest.getPrecision(),
                perfTest.getRecall(), perfTest.getF1()));
        
        
//        DataSet<DefaultRecord> features = engine.generateTrainingDataForLearning(ds1, ds2, gsTest);
//        features.writeCSV(
//        		new File("usecase/geography/output/optimisation/dbp_countries_wb_countries_features.csv"), 
//        		new DefaultRecordCSVFormatter());
    }
    
    
    private static void printCorrespondences(List<Correspondence<Country>> correspondences) {
        // sort the correspondences
        
        for(Correspondence<Country> corr : correspondences){
            if(corr.getSimilarityScore() < 0.9)
            System.out.println(corr.getFirstRecord().getName() +"(" +corr.getFirstRecord().getIdentifier()+ ")\t" + corr.getSimilarityScore() + "\t" + corr.getSecondRecord().getName() + "(" +corr.getSecondRecord().getIdentifier()+ ")");
        }
    }
    
}
