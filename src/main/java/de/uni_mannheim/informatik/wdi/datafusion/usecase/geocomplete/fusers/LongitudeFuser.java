package de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.FusableCountry;

public class LongitudeFuser extends AttributeValueFuser<Double, FusableCountry> {

	public LongitudeFuser() {
		super(new Average<FusableCountry>());
	}
	
	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.LONGITUDE);
	}
	
	@Override
	protected Double getValue(FusableCountry record) {
		return record.getLongitude();
	}

	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		FusedValue<Double, FusableCountry> fused = getFusedValue(group);
			System.out.println(fused.getValue());
			if(fused.getValue()!=null){
				fusedRecord.setLongitude(fused.getValue());
				fusedRecord.setAttributeProvenance(FusableCountry.LONGITUDE, fused.getOriginalIds());
			}else{
				fusedRecord.setLongitude((Double) null);
			}
	}
	
}