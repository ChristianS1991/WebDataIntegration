package de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.FusableCountry;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;

public class LongitudeEvaluationRule extends EvaluationRule<FusableCountry>{
	
	PercentageSimilarity sim = new PercentageSimilarity(0.05d);

	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
		return sim.calculate(record1.getLongitude(), record2.getLongitude()) == 1.0;
	}
	

}
