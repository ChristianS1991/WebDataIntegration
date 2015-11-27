package de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.fusers;

import java.util.List;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.list.Union;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.City;
import de.uni_mannheim.informatik.wdi.datafusion.usecase.geocomplete.FusableCountry;

public class CitiesFuser extends AttributeValueFuser<List<City>, FusableCountry> {

	public CitiesFuser() {
		super(new Union<City, FusableCountry>());
	}
	
	@Override
	public boolean hasValue(FusableCountry record) {
		return record.hasValue(FusableCountry.CITIES);
	}
	
	@Override
	protected List<City> getValue(FusableCountry record) {
		return record.getCities();
	}

	@Override
	public void fuse(RecordGroup<FusableCountry> group,
			FusableCountry fusedRecord) {
		FusedValue<List<City>, FusableCountry> fused = getFusedValue(group);
		fusedRecord.setCities(fused.getValue());
		fusedRecord.setAttributeProvenance(FusableCountry.CITIES, fused.getOriginalIds());
	}
}
