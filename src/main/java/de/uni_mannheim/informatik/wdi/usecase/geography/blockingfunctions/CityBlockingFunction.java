package de.uni_mannheim.informatik.wdi.usecase.geography.blockingfunctions;

import de.uni_mannheim.informatik.wdi.identityresolution.blocking.BlockingFunction;
import de.uni_mannheim.informatik.wdi.usecase.geography.City;

public class CityBlockingFunction extends BlockingFunction<City>{

    @Override
    public String getBlockingKey(City instance) {
        return instance.getName().toLowerCase().substring(0, 2);
    }

}
