package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.FavourSources;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.MostRecent;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Median;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.MinProvAverage;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCity;

public class PopulationFuser extends AttributeValueFuser<Double, FusableCity> {

	public PopulationFuser() {
		super(new MinProvAverage<FusableCity>());
	}
	
	@Override
	public boolean hasValue(FusableCity record) {
		return record.hasValue(FusableCity.POPULATION);
	}
	
	@Override
	protected Double getValue(FusableCity record) {
		return record.getPopulation();
	}

	@Override
	public void fuse(RecordGroup<FusableCity> group,
			FusableCity fusedRecord) {
		FusedValue<Double, FusableCity> fused = getFusedValue(group);
		fusedRecord.setPopulation(fused.getValue());
		fusedRecord.setAttributeProvenance(FusableCity.POPULATION, fused.getOriginalIds());
	}
	
}
