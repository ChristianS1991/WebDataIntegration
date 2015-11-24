package de.uni_mannheim.informatik.wdi.identityresolution.similarity.geo;

import java.util.ArrayList;
import java.util.List;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;
import net.sf.geographiclib.GeodesicMask;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.SimilarityMeasure;

public class GeodesicDistance extends SimilarityMeasure<List<Double>> {

    @Override
    public double calculate(List<Double> lats, List<Double> longs) {
        if(lats == null || longs == null || lats.size() != 2 || longs.size() != 2){
            return 0.0d;
        }
        
        Double lat1 = lats.get(0);
        Double lat2 = lats.get(1);
        
        Double lon1 = longs.get(0);
        Double lon2 = longs.get(1);
        
        if(lat1 == null || lat2 == null || lon1 == null || lon2 == null){
            return 0.0d;
        }
        
        GeodesicData g = Geodesic.WGS84.Inverse(lat1, lon1, lat2, lon2,
                GeodesicMask.DISTANCE);
        
        return g.s12/1000d;
    }
    
    public static void main(String[] args){
        GeodesicDistance dist = new GeodesicDistance();
        ArrayList<Double> lats = new ArrayList<Double>();
        ArrayList<Double> longs = new ArrayList<Double>();
        
        lats.add(30.1);
        lats.add(30.7);
        
        longs.add(45.34);
        longs.add(46.0);
        
        System.out.println(dist.calculate(lats, longs));
        
    }

}
