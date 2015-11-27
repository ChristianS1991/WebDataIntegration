package de.uni_mannheim.informatik.wdi.identityresolution.similarity.geo;

import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.wrapper.CountryCodeWrapper;

public class CountryCodeSimilarity extends SimilarityMeasure<String> {

    CountryCodeWrapper wrapper = CountryCodeWrapper.getInstance();
    
    @Override
    public double calculate(String first, String second) {
        if(first == null || second == null){
            return 0d;
        }
        if(first.length() == 2 && second.length() == 3){
            if(wrapper.getTwoLetterCode(second.toUpperCase()).equalsIgnoreCase(first.toUpperCase())){
                return 1d;
            }
        }
        else if(first.length() == 3 && second.length() == 2){
            if(wrapper.getTwoLetterCode(first.toUpperCase()).equalsIgnoreCase(second.toUpperCase())){
                return 1d;
            }
        }
        return 0d;
    }

}
