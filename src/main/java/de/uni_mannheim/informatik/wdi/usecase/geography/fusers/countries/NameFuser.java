package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.countries;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.string.LongestString;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class NameFuser extends AttributeValueFuser<String, FusableCountry> {
	
	public NameFuser() {
		super(new LongestString<FusableCountry>());
	}
	
	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		
		// get the fused value
		FusedValue<String, FusableCountry> fused = getFusedValue(group);
		
		// set the value for the fused record
		fusedRecord.setName(fused.getValue());
		
		// add provenance info
		fusedRecord.setAttributeProvenance(FusableCountry.NAME, fused.getOriginalIds());
	}

	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.NAME);
	}
	
	@Override
	protected String getValue(FusableCountry record) {
		return record.getName();
	}

}

