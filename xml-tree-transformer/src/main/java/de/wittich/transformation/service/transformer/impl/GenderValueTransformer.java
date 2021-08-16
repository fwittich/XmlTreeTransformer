package de.wittich.transformation.service.transformer.impl;

import org.springframework.stereotype.Service;

import de.wittich.transformation.persistence.ConfigurationEntity;

/**
 * Implementation of {@link AbstractValueTransformer} that transforms the given
 * value as gender name and than uses the {@link ListelementValueTransformer} to
 * perform the final value transformation.
 * 
 * @author Frank
 */
@Service("Gender")
public class GenderValueTransformer extends ListelementValueTransformer {

	@Override
	public Object transformValue(String value, ConfigurationEntity configEntity) {
		String genderValue;
		switch (value) {
		case "m":
			genderValue = "male";
			break;
		case "f":
			genderValue = "female";
			break;
		default:
			return null;
		}
		return super.transformValue(genderValue, configEntity);
	}

}
