package de.uni_mannheim.informatik.wdi.identityresolution.similarity.string;

import org.simmetrics.metrics.JaroWinkler;

import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;

public class WinklerSimilarity extends SimilarityMeasure<String> {

    @Override
    public double calculate(String first, String second) {
        if(first==null || second==null) {
            return 0.0;
        } else {
            JaroWinkler j = new JaroWinkler();
            
            return j.compare(first, second);
        }
    }
    
    public static void main(String[] args){
        WinklerSimilarity sim = new WinklerSimilarity();
        
        System.out.println(sim.calculate("South Korea", "Korea Republic"));
    }

}
