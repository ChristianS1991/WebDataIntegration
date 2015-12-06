package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.countries;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.FavourSources;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.MostRecent;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCountry;

public class GdpFuser extends AttributeValueFuser<Double, FusableCountry> {

	public GdpFuser() {
		super(new MostRecent<Double, FusableCountry>());
	}
	
	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.GDP);
	}
	
	@Override
	protected Double getValue(FusableCountry record) {
		return record.getGdp();
	}

	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		FusedValue<Double, FusableCountry> fused = getFusedValue(group);
		fusedRecord.setGdp(fused.getValue());
		fusedRecord.setAttributeProvenance(FusableCountry.GDP, fused.getOriginalIds());
	}
	
}