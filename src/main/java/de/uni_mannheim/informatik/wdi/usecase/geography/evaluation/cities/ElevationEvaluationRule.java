package de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.cities;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.AbsoluteDifferenceSimilarity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.ContinuousPercentageSimlarity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCity;

public class ElevationEvaluationRule extends EvaluationRule<FusableCity>{
	
	 //AbsoluteDifferenceSimilarity sim1 = new AbsoluteDifferenceSimilarity();
	 ContinuousPercentageSimlarity sim2 = new ContinuousPercentageSimlarity();

	@Override
	public boolean isEqual(FusableCity record1, FusableCity record2) {
		
//			System.out.println("EleSim: " + Math.abs(record1.getElevation()-record2.getElevation()) + " " + sim2.calculate(record1.getElevation(), record2.getElevation()));
			return Math.abs(record1.getElevation()-record2.getElevation()) < 10 || sim2.calculate(record1.getElevation(), record2.getElevation()) > 0.98 ;
		
		
	}
	

}
