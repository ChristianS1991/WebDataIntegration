package de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.AbsoluteDifferenceSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCity;

public class LatitudeEvaluationRule extends EvaluationRule<FusableCity>{
	
	AbsoluteDifferenceSimilarity sim = new AbsoluteDifferenceSimilarity(0.3d);

	@Override
	public boolean isEqual(FusableCity record1, FusableCity record2) {
		
//			System.out.println("LatSim: " + sim.calculate(record1.getLatitude(), record2.getLatitude()));
			return sim.calculate(record1.getLatitude(), record2.getLatitude()) > 0.9;
		
		
	}
	

}
