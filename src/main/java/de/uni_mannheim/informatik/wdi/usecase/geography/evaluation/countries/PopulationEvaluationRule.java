package de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.countries;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.ContinuousPercentageSimlarity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class PopulationEvaluationRule extends EvaluationRule<FusableCountry>{
	
	ContinuousPercentageSimlarity sim = new ContinuousPercentageSimlarity();

	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
//		System.out.println("Population: " + record1.getPopulation() + " & " + record2.getPopulation() + " = "+ sim.calculate(record1.getPopulation(), record2.getPopulation()));

		return sim.calculate(record1.getPopulation(), record2.getPopulation()) > 0.95;
	}
	

}