package de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.fusers.cities;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.string.LongestString;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geography.FusableCity;

public class RegionFuser extends AttributeValueFuser<String, FusableCity>{
	
	public RegionFuser() {
		super(new LongestString<FusableCity>());
	}
	
	@Override
	public void fuse(RecordGroup<FusableCity> group,
			FusableCity fusedRecord) {
		
		// get the fused value
		FusedValue<String, FusableCity> fused = getFusedValue(group);
		
		// set the value for the fused record
		fusedRecord.setRegion(fused.getValue());
		
		// add provenance info
		fusedRecord.setAttributeProvenance(FusableCity.REGION, fused.getOriginalIds());
	}

	@Override
	public boolean hasValue(FusableCity record) {
		return record.hasValue(FusableCity.REGION);
	}
	
	@Override
	protected String getValue(FusableCity record) {
		return record.getRegion();
	}

}
