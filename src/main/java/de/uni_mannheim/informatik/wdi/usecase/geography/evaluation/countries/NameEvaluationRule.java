package de.uni_mannheim.informatik.wdi.usecase.geography.evaluation.countries;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.string.TokenizingJaccardSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class NameEvaluationRule extends EvaluationRule<FusableCountry> {
	
	SimilarityMeasure<String> sim = new TokenizingJaccardSimilarity();

	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
		// the title is correct if all tokens are there, but the order does not matter
		return sim.calculate(record1.getName(), record2.getName()) == 1.0;
	}

}
