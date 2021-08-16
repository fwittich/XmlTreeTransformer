package de.wittich.transformation.service.transformer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import de.wittich.transformation.persistence.ConfigurationEntity;
import de.wittich.transformation.tree.TreeElementAttribute;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Proxy implementation of {@link XMLValueTransformer} that delegates method
 * calls to the actual implementation.
 * <p>
 * When using {@link Autowired} this implementation will be injected due to the
 * {@link Primary} annotation.
 * 
 * @author Frank
 */
@Slf4j
@Primary
@Service
@Getter
public class ValueTransformerProxy implements XMLValueTransformer {

	private final Map<String, XMLValueTransformer> valueTransformerMap;

	@Autowired
	public ValueTransformerProxy(ApplicationContext appContext) {
		this.valueTransformerMap = appContext.getBeansOfType(XMLValueTransformer.class);
	}

	@Override
	public TreeElementAttribute getValue(Document doc, ConfigurationEntity configEntity) {
		TreeElementAttribute attribute = null;
		if (configEntity.getValueTransformerName() != null) {
			attribute = valueTransformerMap.get(configEntity.getValueTransformerName()).getValue(doc, configEntity);
		}

		if (attribute == null && configEntity.getDefaultValue() != null) {
			log.debug("Using default value for AttributeId {} in TreePath {}: {}", configEntity.getAttributeId(), configEntity.getTreePath(), configEntity.getDefaultValue());
			attribute = new TreeElementAttribute(configEntity.getAttributeId(), configEntity.getDefaultValue());
		}

		return attribute;
	}

}
