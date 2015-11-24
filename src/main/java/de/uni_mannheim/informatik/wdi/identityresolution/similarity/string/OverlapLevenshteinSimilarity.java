package de.uni_mannheim.informatik.wdi.identityresolution.similarity.string;

import org.simmetrics.metrics.Levenshtein;

import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;

public class OverlapLevenshteinSimilarity extends SimilarityMeasure<String>{

    @Override
    public double calculate(String first, String second) {
        if(first==null || second==null) {
            return 0.0;
        } else {
            double conf=0d;
            Levenshtein l = new Levenshtein();
            
            String[] firstArray = first.split(" ");
            String[] secondArray = second.split(" ");
            
            int count = 0;
            
            for(int i = 0; i < firstArray.length; i++){
                boolean foundOne = false;
                for(int j = 0; j < secondArray.length; j++){
                    float distance = l.distance(firstArray[i], secondArray[j]);
                    if(distance < 3 && (firstArray[i].length() > 3 || secondArray[j].length() > 3)){
                        foundOne = true;
                    }   
                }
                if(foundOne){
                    count++;
                }
                
            }
            
            conf = (double)count/((firstArray.length+ secondArray.length)/2);
  
            return conf;
        }
        
    }
    
    
    public static void main(String[] args){
     OverlapLevenshteinSimilarity sim = new OverlapLevenshteinSimilarity();
     System.out.println(sim.calculate("Korea Soutch", "South Korea"));
        
    }

}
