package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.countries;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class PopulationDensityFuser extends AttributeValueFuser<Double, FusableCountry> {

	public PopulationDensityFuser() {
		super(new Average<FusableCountry>());
	}
	
	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.POPULATION_DENSITY);
	}
	
	@Override
	protected Double getValue(FusableCountry record) {
		return record.getPopulationDensity();
	}

	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		FusedValue<Double, FusableCountry> fused = getFusedValue(group);
		fusedRecord.setPopulationDensity(fused.getValue());
		fusedRecord.setAttributeProvenance(FusableCountry.POPULATION_DENSITY, fused.getOriginalIds());
	}
	
}