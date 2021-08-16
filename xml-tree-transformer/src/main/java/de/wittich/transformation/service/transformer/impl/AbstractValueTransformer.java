package de.wittich.transformation.service.transformer.impl;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import de.wittich.transformation.persistence.ConfigurationEntity;
import de.wittich.transformation.service.transformer.XMLValueTransformer;
import de.wittich.transformation.tree.TreeElementAttribute;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract implementation of {@link XMLValueTransformer} that handles the
 * evaluation of {@link ConfigurationEntity#getXpath()} for a given
 * {@link Document} and the construction of the required {@link TreeElementAttribute}.
 * The actual value transformation has to be performed by implementations of
 * this class.
 * 
 * @author Frank
 */
@Slf4j
public abstract class AbstractValueTransformer implements XMLValueTransformer {

	@Override
	public TreeElementAttribute getValue(Document doc, ConfigurationEntity configEntity) {

		if (configEntity.getXpath() != null) {
			try {
				String nodeValue = (String) XPathFactory.newInstance().newXPath() //
						.compile(configEntity.getXpath()) //
						.evaluate(doc, XPathConstants.STRING);
				Object transformedValue = transformValue(nodeValue, configEntity);
				log.debug("Transformed value for XPath {}: {} -> {}", configEntity.getXpath(), nodeValue, transformedValue);
				return new TreeElementAttribute(configEntity.getAttributeId(), transformedValue);

			} catch (XPathExpressionException e) {
				log.warn("Failed to evaluate XPath {}", configEntity.getXpath(), e);
			}
		}
		return null;
	}

	protected abstract Object transformValue(String value, ConfigurationEntity configEntity);

}
