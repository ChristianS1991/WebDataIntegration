package de.uni_mannheim.informatik.wdi.usecase.geography.fusers.cities;

import org.apache.commons.lang3.text.WordUtils;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.FusedValue;
import de.uni_mannheim.informatik.wdi.datafusion.RecordGroup;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.Voting;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.FavourSources;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.string.LongestString;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.string.ShortestString;
import de.uni_mannheim.informatik.wdi.usecase.geography.FusableCity;


public class NameFuser extends AttributeValueFuser<String, FusableCity> {
	
	public NameFuser() {
		super(new Voting<String, FusableCity>());
	}
	
	@Override
	public void fuse(RecordGroup<FusableCity> group,
			FusableCity fusedRecord) {
		
		// get the fused value
		FusedValue<String, FusableCity> fused = getFusedValue(group);
		
		// add provenance info
		fusedRecord.setAttributeProvenance(FusableCity.NAME, fused.getOriginalIds());
//		System.out.println("CityName Prov: " + fused.getOriginalIds());
//		System.out.println("CityName Valu: " + WordUtils.capitalize(fused.getValue()) + " " + fused.getValue());
		// set the value for the fused record
		fusedRecord.setName(WordUtils.capitalize(fused.getValue()));
	}

	@Override
	public boolean hasValue(FusableCity record) {
		return record.hasValue(FusableCity.NAME);
	}
	
	@Override
	protected String getValue(FusableCity record) {
		return record.getName();
	}

}
