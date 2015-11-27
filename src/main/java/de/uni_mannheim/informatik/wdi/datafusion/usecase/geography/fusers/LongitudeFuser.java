package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.FusableCity;

public class LongitudeFuser extends AttributeValueFuser<Double, FusableCity> {

	public LongitudeFuser() {
		super(new Average<FusableCity>());
	}
	
	@Override
	public boolean hasValue(FusableCity record) {
		return record.hasValue(FusableCity.LONGITUDE);
	}
	
	@Override
	protected Double getValue(FusableCity record) {
		return record.getLongitude();
	}

	@Override
	public void fuse(RecordGroup<FusableCity> group,
			FusableCity fusedRecord) {
		FusedValue<Double, FusableCity> fused = getFusedValue(group);
		fusedRecord.setLongitude(fused.getValue());
		fusedRecord.setAttributeProvenance(FusableCity.LONGITUDE, fused.getOriginalIds());
	}
	
}
