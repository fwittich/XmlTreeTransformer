package de.wittich.transformation.service.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.wittich.transformation.persistence.ConfigurationEntity;
import de.wittich.transformation.persistence.ListelementEntity;
import de.wittich.transformation.persistence.ListelementRepository;

/**
 * Implementation of {@link AbstractValueTransformer} that transforms the given
 * value into an Integer of a correspoding {@link ListelementEntity} based on a
 * matching {@link ListelementEntity#getValue()}.
 */
@Service("Listelement")
public class ListelementValueTransformer extends AbstractValueTransformer {

	@Autowired
	private ListelementRepository listelementRepo;

	@Override
	protected Object transformValue(String value, ConfigurationEntity configEntity) {
		return listelementRepo.findByValueAndAttributeId(value, configEntity.getAttributeId()) //
				.orElse(new ListelementEntity()) //
				.getListelementId();
	}

}
