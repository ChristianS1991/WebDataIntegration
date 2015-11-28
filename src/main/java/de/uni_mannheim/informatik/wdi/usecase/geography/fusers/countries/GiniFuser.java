package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.countries;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class GiniFuser extends AttributeValueFuser<Double, FusableCountry> {

	public GiniFuser() {
		super(new Average<FusableCountry>());
	}
	
	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.GINI);
	}
	
	@Override
	protected Double getValue(FusableCountry record) {
		return record.getGini();
	}

	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		FusedValue<Double, FusableCountry> fused = getFusedValue(group);
			System.out.println(fused.getValue());
			if(fused.getValue()!=null){
				fusedRecord.setGini(fused.getValue());
				fusedRecord.setAttributeProvenance(FusableCountry.GINI, fused.getOriginalIds());
			}else{
				fusedRecord.setGini((Double) null);
			}
	}
}
