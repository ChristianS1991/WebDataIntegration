package de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.evaluation;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.FusableCountry;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.string.TokenizingJaccardSimilarity;

public class CodeEvaluationRule extends EvaluationRule<FusableCountry>{
	
	SimilarityMeasure<String> sim = new TokenizingJaccardSimilarity();

	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
		// the title is correct if all tokens are there, but the order does not matter
		return sim.calculate(record1.getCode(), record2.getCode()) == 1.0;
	}

}