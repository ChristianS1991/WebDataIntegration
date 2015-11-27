package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.evaluation.countries;

import java.util.HashSet;
import java.util.Set;

import de.uni_mannheim.informatik.wdi.datafusion.evaluation.EvaluationRule;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.FusableCountry;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CitiesEvaluationRule extends EvaluationRule<FusableCountry> {
	@Override
	public boolean isEqual(FusableCountry record1, FusableCountry record2) {
		Set<String> cities1 = new HashSet<>();
		
		for(City c : record1.getCities()) {
			// note: evaluating using the actor's name only suffices for simple lists
			// in your project, you should have actor ids which you use here (and in the identity resolution)
			cities1.add(c.getName());
		}
		
		Set<String> cities2 = new HashSet<>();
		for(City c : record2.getCities()) {
			cities2.add(c.getName());
		}
		
		return cities1.containsAll(cities2) && cities2.containsAll(cities1);
	}
}
