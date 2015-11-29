package de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.AbsoluteDifferenceSimilarity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCity;

public class LongitudeEvaluationRule extends EvaluationRule<FusableCity>{
	
	AbsoluteDifferenceSimilarity sim = new AbsoluteDifferenceSimilarity(0.3d);

	@Override
	public boolean isEqual(FusableCity record1, FusableCity record2) {
		if (record1.getName() != null){
			return sim.calculate(record1.getLongitude(), record2.getLongitude()) > 0.9;
		}else{
			return true;
		}
		
	}
	

}
