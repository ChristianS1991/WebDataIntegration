package de.uni_mannheim.informatik.wdi.identityresolution.similarity.wrapper;

import java.io.IOException;

import de.linguatools.disco.Compositionality;
import de.linguatools.disco.Compositionality.SimilarityMeasures;
import de.linguatools.disco.Compositionality.VectorCompositionMethod;
import de.linguatools.disco.DISCO;

public class DiscoWrapper {
    
    private static DiscoWrapper instance;
    
    private DISCO dis;
    private Compositionality comp;
    
    private float alpha = 0.5f;
    private float beta = 0.5f;
    private float gamma = 0.5f;
    private float delta = 0.5f;
    
    public float compareNGrams(String s1, String s2){
        try{   
            return comp.compositionalSemanticSimilarity(s1, s2, VectorCompositionMethod.COMBINED, SimilarityMeasures.KOLB, dis, alpha, beta, gamma, delta)/2.05f;        
        } catch(Exception e ){
            e.printStackTrace();
        }

        return 0f;
    }
    
    public float compare(String s1, String s2){
        try {
            return dis.firstOrderSimilarity(s1, s2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0f;
    }
    
    
    private DiscoWrapper () {
        try{
            dis = new DISCO("disco/enwiki-20130403-sim-lemma-mwl-lc", true);
            comp = new Compositionality();
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static DiscoWrapper getInstance () {
      if (DiscoWrapper.instance == null) {
        DiscoWrapper.instance = new DiscoWrapper ();
      }
      return DiscoWrapper.instance;
    }
  }