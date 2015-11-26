package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.cities;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.FusableCity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;

public class RainfallEvaluationRule extends EvaluationRule<FusableCity>{
	
	PercentageSimilarity sim = new PercentageSimilarity(0.05d);

	@Override
	public boolean isEqual(FusableCity record1, FusableCity record2) {
		return sim.calculate(record1.getRainfall(), record2.getRainfall()) == 1.0;
	}
	

}
