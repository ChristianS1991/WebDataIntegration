package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.FusableCountry;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;

public class GdpEvaluationRule extends EvaluationRule<FusableCountry>{
	
	PercentageSimilarity sim = new PercentageSimilarity(0.05d);

	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
		return sim.calculate(record1.getGdp(), record2.getGdp()) == 1.0;
	}
	

}