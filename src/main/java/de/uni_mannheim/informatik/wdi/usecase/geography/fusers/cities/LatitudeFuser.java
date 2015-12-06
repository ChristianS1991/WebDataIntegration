package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.FavourSources;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Median;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.MinProvAverage;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCity;

public class LatitudeFuser extends AttributeValueFuser<Double, FusableCity> {

	public LatitudeFuser() {
		super(new Average<FusableCity>());
	}
	
	@Override
	public boolean hasValue(FusableCity record) {
		return record.hasValue(FusableCity.LATITUDE);
	}
	
	@Override
	protected Double getValue(FusableCity record) {
		return record.getLatitude();
	}

	@Override
	public void fuse(RecordGroup<FusableCity> group,
			FusableCity fusedRecord) {
		FusedValue<Double, FusableCity> fused = getFusedValue(group);
		fusedRecord.setLatitude(fused.getValue());
		fusedRecord.setAttributeProvenance(FusableCity.LATITUDE, fused.getOriginalIds());
	}
	
}
