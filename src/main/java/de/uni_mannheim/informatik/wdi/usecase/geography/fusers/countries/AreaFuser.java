package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.countries;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class AreaFuser extends AttributeValueFuser<Double, FusableCountry> {

	public AreaFuser() {
		super(new Average<FusableCountry>());
	}
	
	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.AREA);
	}
	
	@Override
	protected Double getValue(FusableCountry record) {
		return record.getArea();
	}

	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		
		// get the fused value
		FusedValue<Double, FusableCountry> fused = getFusedValue(group);
		// set the value for the fused record
		fusedRecord.setArea(fused.getValue());
		// add provenance info
		fusedRecord.setAttributeProvenance(FusableCountry.AREA, fused.getOriginalIds());
	}
	
}
