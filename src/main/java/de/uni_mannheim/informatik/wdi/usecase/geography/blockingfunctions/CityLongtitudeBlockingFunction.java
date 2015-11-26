package de.uni_mannheim.informatik.wdi.usecase.geography.blockingfunctions;

import java.text.DecimalFormat;

import de.uni_mannheim.informatik.wdi.identityresolution.blocking.BlockingFunction;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityLongtitudeBlockingFunction extends BlockingFunction<City>{

    @Override
    public String getBlockingKey(City instance) {
        DecimalFormat df = new DecimalFormat("000.0000");
        Double longtitude = instance.getLongitude();
        if(longtitude != null){
            return df.format(instance.getLongitude());
        }else{
            return "190.0000";
        }
        
    }
    
}


