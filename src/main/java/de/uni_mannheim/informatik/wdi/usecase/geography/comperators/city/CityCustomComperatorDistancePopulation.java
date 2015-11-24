package de.uni_mannheim.informatik.wdi.usecase.geography.comperators.city;

import java.util.ArrayList;

import de.uni_mannheim.informatik.wdi.identityresolution.matching.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.geo.GeodesicDistance;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.numeric.ContinuousPercentageSimlarity;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityCustomComperatorDistancePopulation extends Comparator<City>{

    GeodesicDistance dist = new GeodesicDistance();
    ContinuousPercentageSimlarity sim = new ContinuousPercentageSimlarity();
    
    @Override
    public double compare(City record1, City record2) {
        ArrayList<Double> lats = new ArrayList<Double>();
        ArrayList<Double> longs = new ArrayList<Double>();
        
        lats.add(record1.getLatitude());
        lats.add(record2.getLatitude());
        
        longs.add(record1.getLongtitude());
        longs.add(record2.getLongtitude());
        
        double distance = dist.calculate(lats, longs);
        double population1 = record1.getPopulation();
        double population2 = record2.getPopulation();
        
        double threshold = (111)*(Math.pow(Math.max(population1, population2), 0.3)/Math.pow(15000000,0.3));
        
        if(distance < threshold){
            return 1d;
        }else{
            return sim.calculate(distance, threshold);
        }
    }

}
