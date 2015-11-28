package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.countries;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class LatitudeFuser extends AttributeValueFuser<Double, FusableCountry> {

	public LatitudeFuser() {
		super(new Average<FusableCountry>());
	}
	
	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.LATITUDE);
	}
	
	@Override
	protected Double getValue(FusableCountry record) {
		return record.getLatitude();
	}

	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		FusedValue<Double, FusableCountry> fused = getFusedValue(group);
		fusedRecord.setLatitude(fused.getValue());
		fusedRecord.setAttributeProvenance(FusableCountry.LATITUDE, fused.getOriginalIds());
	}
	
}