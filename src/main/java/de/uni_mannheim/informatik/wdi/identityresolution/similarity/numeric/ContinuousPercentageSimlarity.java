package de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric;

import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;

public class ContinuousPercentageSimlarity extends SimilarityMeasure<Double>{

    @Override
    public double calculate(Double first, Double second) {
        if(first == null || second == null){
            return 0d;
        }
        return 1 - Math.abs(first-second)/Math.max(first, second);
    }

}
