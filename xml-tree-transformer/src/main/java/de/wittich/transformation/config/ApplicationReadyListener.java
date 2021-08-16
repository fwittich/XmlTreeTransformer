package de.wittich.transformation.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import de.wittich.transformation.persistence.ConfigurationEntity;
import de.wittich.transformation.persistence.ConfigurationRepository;
import de.wittich.transformation.service.transformer.ValueTransformerProxy;
import de.wittich.transformation.service.transformer.XMLValueTransformer;

@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private ValueTransformerProxy transformerProxy;

	@Autowired
	private ConfigurationRepository configRepo;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		checkTransformerDatabaseEntries();
	}

	/**
	 * Ensures that for each {@link ConfigurationEntity#getValueTransformerName()}
	 * an accordingly named {@link XMLValueTransformer} bean exists.
	 * <p>
	 * In case there are any bean names defined by {@link ConfigurationEntity} but
	 * not present in the application context, a {@link MissingTransformerException}
	 * is thrown.
	 */
	private void checkTransformerDatabaseEntries() {
		List<String> configuredResolvers = configRepo.findDistinctValueTransformerName();
		configuredResolvers.removeAll(transformerProxy.getValueTransformerMap().keySet());

		if (!configuredResolvers.isEmpty()) {
			throw new MissingTransformerException(configuredResolvers);
		}
	}

}
