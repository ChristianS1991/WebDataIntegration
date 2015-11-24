package de.uni_mannheim.informatik.wdi.identityresolution.similarity.string;

import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.wrapper.DiscoWrapper;

public class DistributionalSemanticsSimilarity extends SimilarityMeasure<String> {

    @Override
    public double calculate(String first, String second) {
        if(first==null || second==null) {
            return 0.0;
        } else if (first.contains(" ") || second.contains(" ")){
            return DiscoWrapper.getInstance().compareNGrams(first, second);
        } else {
            return DiscoWrapper.getInstance().compare(first, second);
        }
    }
    
    public static void main(String[] args){
        DistributionalSemanticsSimilarity sim = new DistributionalSemanticsSimilarity();
        
        System.out.println(sim.calculate("South Korea", "Korea Republic"));
    }

}
