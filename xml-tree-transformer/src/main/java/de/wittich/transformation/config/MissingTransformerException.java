package de.wittich.transformation.config;

import java.util.List;

import lombok.Getter;

@Getter
public class MissingTransformerException extends RuntimeException {

	private static final long serialVersionUID = -2454805151224909234L;
	
	private List<String> missingTransformers;

	public MissingTransformerException(List<String> missingTransformers) {
		super();
		this.missingTransformers = missingTransformers;
	}

}
