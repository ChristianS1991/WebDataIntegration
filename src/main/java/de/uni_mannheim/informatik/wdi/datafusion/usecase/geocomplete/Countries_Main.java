package de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete;

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
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.AreaEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.CitiesEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.CodeEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.GdpEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.GiniEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.LatitudeEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.LongitudeEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.NameEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.PopulationDensityEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation.PopulationEvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.AreaFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.CitiesFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.CodeFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.GdpFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.GiniFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.LatitudeFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.LongitudeFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.NameFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.PopulationDensityFuser;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers.PopulationFuser;

public class Countries_Main {
	
	public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException {
		// load the data sets
		FusableDataSet<FusableCountry> ds1 = new FusableDataSet<>();
		FusableDataSet<FusableCountry> ds2 = new FusableDataSet<>();
		FusableDataSet<FusableCountry> ds3 = new FusableDataSet<>();
		FusableDataSet<FusableCountry> ds4 = new FusableDataSet<>();
		FusableDataSet<FusableCountry> ds5 = new FusableDataSet<>();
		ds1.loadFromXML(
				new File("usecase/geocomplete/input/dbp_countries.xml"),
				new FusableCountryFactory(), 
				"/countries/country");
		ds2.loadFromXML(
				new File("usecase/geocomplete/input/worldbank_countries.xml"),
				new FusableCountryFactory(),
				"/countries/country");
		ds3.loadFromXML(
				new File("usecase/geocomplete/input/dbp_cities.xml"),
				new FusableCountryFactory(),
				"/countries/country");
		ds4.loadFromXML(
				new File("usecase/geocomplete/input/wcp_cities.xml"),
				new FusableCountryFactory(),
				"/countries/country");
		ds5.loadFromXML(
				new File("usecase/geocomplete/input/geonames_cities.xml"),
				new FusableCountryFactory(),
				"/countries/country");
		
		// set dataset metadata
		ds1.setScore(1.0);
		ds2.setScore(2.0);
		ds3.setScore(3.0);
		ds4.setScore(3.0);
		ds5.setScore(3.0);
		ds1.setDate(DateTime.parse("2012-01-01"));
		ds2.setDate(DateTime.parse("2010-01-01"));
		ds3.setDate(DateTime.parse("2008-01-01"));
		ds4.setDate(DateTime.parse("2006-01-01"));
		ds5.setDate(DateTime.parse("2004-01-01"));
		
		// print dataset density
		System.out.println("dbp_countries.xml");
		ds1.printDataSetDensityReport();
		System.out.println("worldbank_countries.xml");
		ds2.printDataSetDensityReport();
		System.out.println("dbp_cities.xml");
		ds3.printDataSetDensityReport();
		System.out.println("wcp_cities.xml");
		ds4.printDataSetDensityReport();
		System.out.println("geonames_cities.xml");
		ds5.printDataSetDensityReport();
		
		// load the correspondences
		CorrespondenceSet<FusableCountry> correspondences = new CorrespondenceSet<>();
		correspondences.loadCorrespondences(new File("usecase/geocomplete/correspondences/dbp_countries_wb_countries_correspondences.csv"), ds1, ds2);
		correspondences.loadCorrespondences(new File("usecase/geocomplete/correspondences/dbp_countries_dbp_cities_correspondences.csv"), ds1, ds3);
		//correspondences.loadCorrespondences(new File("usecase/geocomplete/correspondences/dbp_cities_geonames_cities_correspondences.csv"), ds3, ds5);
		correspondences.loadCorrespondences(new File("usecase/geocomplete/correspondences/wb_countries_gn_cities_correspondences.csv"), ds2, ds5);
		correspondences.loadCorrespondences(new File("usecase/geocomplete/correspondences/wb_countries_wcp_cities_correspondences.csv"), ds2, ds4);
		//correspondences.loadCorrespondences(new File("usecase/geocomplete/correspondences/wcp_cities_geonames_cities_correspondences.csv"), ds4, ds5);

		// write group size distribution
		correspondences.writeGroupSizeDistribution(new File("usecase/geocomplete/output/group_size_distribution.csv"));
		
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
		fusedDataSet.writeXML(new File("usecase/geocomplete/output/CountriesWithBasicCitiesList.xml"), new CountryXMLFormatter());
		System.out.println("Successful");
		/*
		// load the gold standard
		DataSet<FusableCountry> gs = new FusableDataSet<>();
		gs.loadFromXML(
				new File("usecase/geocomplete/goldstandard/fused.xml"),
				new FusableCountryFactory(), "/geocomplete/city");
		
		// evaluate
		DataFusionEvaluator<FusableCountry> evaluator = new DataFusionEvaluator<>(strategy);
		evaluator.setVerbose(true);
		double accuracy = evaluator.evaluate(fusedDataSet, gs);
		
		System.out.println(String.format("Accuracy: %.2f", accuracy));*/
		
	}
}
