package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.FavourSources;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.Average;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.numeric.MinProvAverage;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCity;

public class ElevationFuser extends AttributeValueFuser<Double, FusableCity>{
	
	public ElevationFuser() {
		super(new Average<FusableCity>());
	}
	
	@Override
	public boolean hasValue(FusableCity record) {
		return record.hasValue(FusableCity.ELEVATION);
	}
	
	@Override
	protected Double getValue(FusableCity record) {
		return record.getElevation();
	}

	@Override
	public void fuse(RecordGroup<FusableCity> group,
			FusableCity fusedRecord) {
		FusedValue<Double, FusableCity> fused = getFusedValue(group);
		if(fused.getValue()!=null){
			fusedRecord.setElevation(fused.getValue());
			fusedRecord.setAttributeProvenance(FusableCity.ELEVATION, fused.getOriginalIds());
		}else{
			fusedRecord.setElevation((Double) null);
		}
		
	}
	
}
