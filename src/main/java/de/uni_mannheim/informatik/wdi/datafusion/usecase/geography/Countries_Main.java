package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography;

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
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.AreaEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.CitiesEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.CodeEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.GdpEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.GiniEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.LatitudeEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.LongitudeEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.NameEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.PopulationDensityEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries.PopulationEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.AreaFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.CitiesFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.CodeFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.GdpFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.GiniFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.LatitudeFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.LongitudeFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.NameFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.PopulationDensityFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.countries.PopulationFuser;

public class Countries_Main {
	
	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException {
		// load the data sets
		FusableDataSet<FusableCountry> ds1 = new FusableDataSet<>();
		FusableDataSet<FusableCountry> ds2 = new FusableDataSet<>();
		FusableDataSet<FusableCountry> ds3 = new FusableDataSet<>();
		ds1.loadFromXML(
				new File("usecase/geography/input/dbp_countries.xml"),
				new FusableCountryFactory(), 
				"/countries/country");
		ds2.loadFromXML(
				new File("usecase/geography/input/worldbank_countries.xml"),
				new FusableCountryFactory(),
				"/countries/country");
		ds3.loadFromXML(
				new File("usecase/geography/input/dbp_cities.xml"),
				new FusableCountryFactory(),
				"/countries/country");
		
		// set dataset metadata
		ds1.setScore(1.0);
		ds2.setScore(2.0);
		ds3.setScore(3.0);
		ds1.setDate(DateTime.parse("2012-01-01"));
		ds2.setDate(DateTime.parse("2010-01-01"));
		ds3.setDate(DateTime.parse("2008-01-01"));
		
		// print dataset density
		System.out.println("dbp_countries.xml");
		ds1.printDataSetDensityReport();
		System.out.println("worldbank_countries.xml");
		ds2.printDataSetDensityReport();
		System.out.println("dbp_cities.xml");
		ds3.printDataSetDensityReport();
		
		// load the correspondences
		CorrespondenceSet<FusableCountry> correspondences = new CorrespondenceSet<>();
		correspondences.loadCorrespondences(new File("usecase/geography/correspondences/dbp_countries_wb_countries_correspondences.csv"), ds1, ds2);
		correspondences.loadCorrespondences(new File("usecase/geography/correspondences/dbp_countries_dbp_cities_correspondences.csv"), ds1, ds3);

		// write group size distribution
		correspondences.writeGroupSizeDistribution(new File("usecase/geography/output/group_size_distribution.csv"));
		
		// define the fusion strategy
		DataFusionStrategy<FusableCountry> strategy = new DataFusionStrategy<>(new FusableCountryFactory());
		// add attribute fusers
		// Note: The attribute name is only used for printing the reports
		strategy.addAttributeFuser("Name", new NameFuser(), new NameEvaluationRule());
		strategy.addAttributeFuser("Code", new CodeFuser(), new CodeEvaluationRule());
		strategy.addAttributeFuser("Area", new AreaFuser(), new AreaEvaluationRule());
		strategy.addAttributeFuser("Population", new PopulationFuser(), new PopulationEvaluationRule());
		strategy.addAttributeFuser("PopulationDensity", new PopulationDensityFuser(), new PopulationDensityEvaluationRule());
		strategy.addAttributeFuser("GINI", new GiniFuser(), new GiniEvaluationRule());
		strategy.addAttributeFuser("GDP", new GdpFuser(), new GdpEvaluationRule());
		strategy.addAttributeFuser("Longitude", new LongitudeFuser(), new LongitudeEvaluationRule());
		strategy.addAttributeFuser("Latitude", new LatitudeFuser(), new LatitudeEvaluationRule());
		strategy.addAttributeFuser("Cities", new CitiesFuser(), new CitiesEvaluationRule());
		// create the fusion engine
		DataFusionEngine<FusableCountry> engine = new DataFusionEngine<>(strategy);
		
		// calculate cluster consistency
		engine.printClusterConsistencyReport(correspondences);
		
		// run the fusion
		FusableDataSet<FusableCountry> fusedDataSet = engine.run(correspondences);
		
		// write the result
		fusedDataSet.writeXML(new File("usecase/geography/output/fusedinklcities.xml"), new CountryXMLFormatter());
		
	/*	// load the gold standard
		DataSet<FusableCountry> gs = new FusableDataSet<>();
		gs.loadFromXML(
				new File("usecase/geography/goldstandard/fused.xml"),
				new FusableCountryFactory(), "/geography/city");
		
		// evaluate
		DataFusionEvaluator<FusableCountry> evaluator = new DataFusionEvaluator<>(strategy);
		evaluator.setVerbose(true);
		double accuracy = evaluator.evaluate(fusedDataSet, gs);
		
		System.out.println(String.format("Accuracy: %.2f", accuracy));*/
		
	}
}
