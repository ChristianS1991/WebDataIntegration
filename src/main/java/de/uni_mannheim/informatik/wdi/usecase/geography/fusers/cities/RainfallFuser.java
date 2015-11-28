package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCity;

public class RainfallFuser extends AttributeValueFuser<Double, FusableCity> {

	public RainfallFuser() {
		super(new Average<FusableCity>());
	}
	
	@Override
	public boolean hasValue(FusableCity record) {
		return record.hasValue(FusableCity.RAINFALL);
	}
	
	@Override
	protected Double getValue(FusableCity record) {
		return record.getRainfall();
	}

	@Override
	public void fuse(RecordGroup<FusableCity> group,
			FusableCity fusedRecord) {
		FusedValue<Double, FusableCity> fused = getFusedValue(group);
		fusedRecord.setRainfall(fused.getValue());
		fusedRecord.setAttributeProvenance(FusableCity.RAINFALL, fused.getOriginalIds());
	}
	
}
