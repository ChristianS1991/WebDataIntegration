package de.uni_mannheim.informatik.wdi.usecase.geography.blockingfunctions;

import de.uni_mannheim.informatik.wdi.identityresolution.blocking.BlockingFunction;
import de.uni_mannheim.informatik.wdi.usecase.geography.Country;

public class CountryPopulationBlockingFunction extends BlockingFunction<Country>{

    @Override
    public String getBlockingKey(Country instance) {
        return instance.getPopulation() + "";
    }

}
