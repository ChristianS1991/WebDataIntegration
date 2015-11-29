package de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.countries;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.ContinuousPercentageSimlarity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class GdpEvaluationRule extends EvaluationRule<FusableCountry>{
	
ContinuousPercentageSimlarity sim = new ContinuousPercentageSimlarity();
	
	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
		System.out.println("Gdp: " + record1.getGdp() + " & " + record2.getGdp() + " = "+ sim.calculate(record1.getGdp(), record2.getGdp()));

		return sim.calculate(record1.getGdp(), record2.getGdp()) > 0.98;
	}
	

}