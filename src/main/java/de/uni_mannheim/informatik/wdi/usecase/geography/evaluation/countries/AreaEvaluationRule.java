package de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.countries;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.ContinuousPercentageSimlarity;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.PercentageSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class AreaEvaluationRule extends EvaluationRule<FusableCountry>{
	
	ContinuousPercentageSimlarity sim = new ContinuousPercentageSimlarity();

	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
//		System.out.println("AreaSim: " + record1.getArea() + " " + record2.getArea() + " " + sim.calculate(record1.getArea(), record2.getArea()));
		return sim.calculate(record1.getArea(), record2.getArea()) > 0.98;
	}
	

}
