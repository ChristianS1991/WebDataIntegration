package de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.Voting;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.FusableCountry;

public class CodeFuser extends AttributeValueFuser<String, FusableCountry> {
	
	public CodeFuser() {
		super(new Voting<String, FusableCountry>());
	}
	
	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		
		// get the fused value
		FusedValue<String, FusableCountry> fused = getFusedValue(group);
		
		// set the value for the fused record
		fusedRecord.setCode(fused.getValue());
		
		// add provenance info
		System.out.println("Provenance Code: " + fused.getOriginalIds());
		fusedRecord.setAttributeProvenance(FusableCountry.CODE, fused.getOriginalIds());
	}

	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.CODE);
	}
	
	@Override
	protected String getValue(FusableCountry record) {
		return record.getCode();
	}

}
