package de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.countries;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.ContinuousPercentageSimlarity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class GiniEvaluationRule extends EvaluationRule<FusableCountry>{
	
	ContinuousPercentageSimlarity sim = new ContinuousPercentageSimlarity();
	
	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
//		System.out.println("GINI: " + record1.getGini() + " & " + record2.getGini() + " = "+ sim.calculate(record1.getGini(), record2.getGini()));

		return sim.calculate(record1.getGini(), record2.getGini()) > 0.98;
	}
	

}