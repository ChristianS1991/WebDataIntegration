package de.uni_mannheim.informatik.wdi.identityresolution.similarity.string;

import org.simmetrics.metrics.Jaro;

import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;

public class JaroSimilarity extends SimilarityMeasure<String> {

    @Override
    public double calculate(String first, String second) {
        if(first==null || second==null) {
            return 0.0;
        } else {
            Jaro j = new Jaro();
            
            return j.compare(first, second);
        }
    }
    
    public static void main(String[] args){
        JaroSimilarity sim = new JaroSimilarity();
        
        System.out.println(sim.calculate("South Korea", "Korea Republic"));
    }

}
