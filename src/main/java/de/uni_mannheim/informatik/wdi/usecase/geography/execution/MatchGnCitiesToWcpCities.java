package de.uni_mannheim.informatik.wdi.usecase.geography.execution;

import java.io.File;
import java.util.List;

import de.uni_mannheim.informatik.wdi.DataSet;
import de.uni_mannheim.informatik.wdi.identityresolution.blocking.SortedNeighbourhoodBlocker;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.GoldStandard;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.MatchingEvaluator;
import de.uni_mannheim.informatik.wdi.identityresolution.evaluation.Performance;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.Correspondence;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.LinearCombinationMatchingRule;
import de.uni_mannheim.informatik.wdi.identityresolution.matching.MatchingEngine;
import de.uni_mannheim.informatik.wdi.identityresolution.model.DefaultRecord;
import de.uni_mannheim.informatik.wdi.identityresolution.model.DefaultRecordCSVFormatter;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;
import de.uni_mannheim.informatik.wdi.usecase.geography.CityFactory;
import de.uni_mannheim.informatik.wdi.usecase.geography.blockingfunctions.CityLongtitudeBlockingFunction;
import de.uni_mannheim.informatik.wdi.usecase.geography.comperators.city.CityLatitudeComparatorAbsolute;
import de.uni_mannheim.informatik.wdi.usecase.geography.comperators.city.CityLongtitudeComparatorAbsolute;
import de.uni_mannheim.informatik.wdi.usecase.geography.comperators.city.CityNameComparatorLevenshtein;

public class MatchGnCitiesToWcpCities {

    public static void main(String[] args) throws Exception {
        
        LinearCombinationMatchingRule<City> rule = new LinearCombinationMatchingRule<City>(0.8);
        
        rule.addComparator(new CityNameComparatorLevenshtein(), 0.3);
        rule.addComparator(new CityLatitudeComparatorAbsolute(), 0.3d);
        rule.addComparator(new CityLongtitudeComparatorAbsolute(), 0.3d);
        
        SortedNeighbourhoodBlocker<City> blocker = new SortedNeighbourhoodBlocker<City>(new CityLongtitudeBlockingFunction(), 20);
        
        MatchingEngine<City> engine = new MatchingEngine<City>(rule, blocker);
        
        
        DataSet<City> ds1 = new DataSet<City>();
        DataSet<City> ds2 = new DataSet<City>();
        ds1.loadFromXML(
                new File("usecase/geography/input/geonames_cities.xml"),
                new CityFactory(), "/countries/country/cities/city");
        ds2.loadFromXML(
                new File("usecase/geography/input/wcp_cities.xml"),
                new CityFactory(), "/countries/country/cities/city");
        
        List<Correspondence<City>> correspondences = engine
                .runMatching(ds1, ds2);
        
        engine.writeCorrespondences(correspondences, new File("usecase/geography/output/wcp_cities_geonames_cities_correspondences.csv"));
//        printCorrespondences(correspondences);
//        System.out.println(correspondences.size());
        
     // load the gold standard (test set)
        GoldStandard gsTest = new GoldStandard();
        gsTest.loadFromCSVFile(new File(
                "usecase/geography/goldstandard/wcp_cities_geonames_cities.csv"));

        // evaluate the result
        MatchingEvaluator<City> evaluator = new MatchingEvaluator<>(true);
        Performance perfTest = evaluator.evaluateMatching(correspondences, gsTest);
        
        // print the evaluation result
        System.out.println(String.format(
                "Precision: %.4f\nRecall: %.4f\nF1: %.4f", perfTest.getPrecision(),
                perfTest.getRecall(), perfTest.getF1()));
        
        DataSet<DefaultRecord> features = engine.generateTrainingDataForLearning(ds1, ds2, gsTest);
        features.writeCSV(
        		new File("usecase/geography/output/optimisation/wcp_cities_geonames_cities_features.csv"), 
        		new DefaultRecordCSVFormatter());
    }
    
    
    private static void printCorrespondences(List<Correspondence<City>> correspondences) {
        // sort the correspondences
        
        for(Correspondence<City> corr : correspondences){
            if(corr.getSimilarityScore()< 0.8)
            System.out.println(corr.getFirstRecord().getName() +"(" +corr.getFirstRecord().getIdentifier()+ ")\t" + corr.getSimilarityScore() + "\t" + corr.getSecondRecord().getName() + "(" +corr.getSecondRecord().getIdentifier()+ ")");
        }
    }

}
