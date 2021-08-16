package de.wittich.transformation.service.transformer.impl;

import org.springframework.stereotype.Service;

import de.wittich.transformation.persistence.ConfigurationEntity;

/**
 * Implementation of {@link AbstractValueTransformer} that returns the given
 * value as is.
 * 
 * @author Frank
 */
@Service("Direct")
public class DirectValueTransformer extends AbstractValueTransformer {

	@Override
	public Object transformValue(String value, ConfigurationEntity configEntity) {
		return value;
	}

}
