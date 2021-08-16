package de.wittich.transformation.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;
import org.springframework.stereotype.Component;

import de.wittich.transformation.persistence.ConfigurationEntity;
import de.wittich.transformation.service.transformer.XMLValueTransformer;

/**
 * Custom {@link FailureAnalyzer} for {@link MissingTransformerException}.
 * 
 * @author Frank
 */
@Component
public class MissingTransformerFailureAnalyzer extends AbstractFailureAnalyzer<MissingTransformerException> {

	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, MissingTransformerException cause) {
		return new FailureAnalysis( //
				buildErrorMessage(cause.getMissingTransformers()), //
				"Ensure there is a bean for all names listed by " + ConfigurationEntity.class.getSimpleName(), //
				cause);
	}

	private String buildErrorMessage(List<String> strings) {
		return String.format( //
				"No Bean of type %s found with name: [%s]", //
				XMLValueTransformer.class.getSimpleName(), //
				strings.stream().collect(Collectors.joining(", ")) //
		);
	}

}
