package de.uni_mannheim.informatik.wdi.usecase.geography;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.joda.time.DateTime;
import org.xml.sax.SAXException;

import de.uni_mannheim.informatik.wdi.DataSet;
import de.uni_mannheim.informatik.wdi.datafusion.CorrespondenceSet;
import de.uni_mannheim.informatik.wdi.datafusion.DataFusionEngine;
import de.uni_mannheim.informatik.wdi.datafusion.DataFusionStrategy;
import de.uni_mannheim.informatik.wdi.datafusion.FusableDataSet;
import de.uni_mannheim.informatik.wdi.datafusion.evaluation.DataFusionEvaluator;
import de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities.ElevationEvaluationRule;
import de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities.LatitudeEvaluationRule;
import de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities.LongitudeEvaluationRule;
import de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities.NameEvaluationRule;
import de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities.PopulationDensityEvaluationRule;
import de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities.PopulationEvaluationRule;
import de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities.RegionEvaluationRule;
import de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities.ElevationFuser;
import de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities.LatitudeFuser;
import de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities.LongitudeFuser;
import de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities.NameFuser;
import de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities.PopulationDensityFuser;
import de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities.PopulationFuser;
import de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities.RegionFuser;

public class Cities_Main {
	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException {
		// load the data sets
		FusableDataSet<FusableCity> ds1 = new FusableDataSet<>();
		FusableDataSet<FusableCity> ds2 = new FusableDataSet<>();
		FusableDataSet<FusableCity> ds3 = new FusableDataSet<>();
		ds1.loadFromXML(
				new File("usecase/geography/input/dbp_cities.xml"),
				new FusableCityFactory(), "/countries/country/cities/city");
		ds2.loadFromXML(
				new File("usecase/geography/input/wcp_cities.xml"), 
				new FusableCityFactory(), 
				"/countries/country/cities/city");
		ds3.loadFromXML(
				new File("usecase/geography/input/geonames_cities.xml"),
				new FusableCityFactory(), "/countries/country/cities/city");
	
		
		// set dataset metadata
		ds1.setScore(1.0);
		ds2.setScore(2.0);
		ds3.setScore(3.0);
		ds1.setDate(DateTime.parse("2012-01-01"));
		ds2.setDate(DateTime.parse("2010-01-01"));
		ds3.setDate(DateTime.parse("2008-01-01"));
		
		// print dataset density
		System.out.println("dbp_cities.xml");
		ds1.printDataSetDensityReport();
		System.out.println("geonames_cities.xml");
		ds2.printDataSetDensityReport();
		System.out.println("wcp_cities.xml");
		ds3.printDataSetDensityReport();
		
		// load the correspondences
		CorrespondenceSet<FusableCity> correspondences = new CorrespondenceSet<>();
		correspondences.loadCorrespondences(new File("usecase/geography/correspondences/dbp_cities_geonames_cities_correspondences.csv"), ds1, ds3);
		correspondences.loadCorrespondences(new File("usecase/geography/correspondences/dbp_cities_wcp_cities_correspondences.csv"), ds1, ds2);
		
		// write group size distribution
		correspondences.writeGroupSizeDistribution(new File("usecase/geography/output/group_size_distribution_cities.csv"));
		
		// define the fusion strategy
		DataFusionStrategy<FusableCity> strategy = new DataFusionStrategy<>(new FusableCityFactory());
		// add attribute fusers
		// Note: The attribute name is only used for printing the reports
		strategy.addAttributeFuser("Name", new NameFuser(), new NameEvaluationRule());
		strategy.addAttributeFuser("Region", new RegionFuser(), new RegionEvaluationRule());
		strategy.addAttributeFuser("Population", new PopulationFuser(), new PopulationEvaluationRule());
		strategy.addAttributeFuser("PopulationDensity", new PopulationDensityFuser(), new PopulationDensityEvaluationRule());
		strategy.addAttributeFuser("Latitude", new LatitudeFuser(), new LatitudeEvaluationRule());
		strategy.addAttributeFuser("Longitude", new LongitudeFuser(), new LongitudeEvaluationRule());
		strategy.addAttributeFuser("Elevation", new ElevationFuser(), new ElevationEvaluationRule());
		
		// create the fusion engine
		DataFusionEngine<FusableCity> engine = new DataFusionEngine<>(strategy);
		
		// calculate cluster consistency
		engine.printClusterConsistencyReport(correspondences);
		
		// run the fusion
		FusableDataSet<FusableCity> fusedDataSet = engine.run(correspondences);
		
		// write the result
		fusedDataSet.writeXML(new File("usecase/geography/output/CitiesFusedWithoutCountryIdentifier.xml"), new CityXMLFormatter());
		
		/*// load the gold standard
		DataSet<FusableCity> gs = new FusableDataSet<>();
		gs.loadFromXML(
				new File("usecase/movie/goldstandard/fused.xml"),
				new FusableCityFactory(), "/movies/movie");
		
		// evaluate
		DataFusionEvaluator<FusableCity> evaluator = new DataFusionEvaluator<>(strategy);
		evaluator.setVerbose(true);
		double accuracy = evaluator.evaluate(fusedDataSet, gs);
		
		System.out.println(String.format("Accuracy: %.2f", accuracy));*/
		
	}

}
