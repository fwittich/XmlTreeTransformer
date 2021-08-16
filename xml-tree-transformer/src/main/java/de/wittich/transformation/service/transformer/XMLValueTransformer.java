package de.wittich.transformation.service.transformer;

import org.w3c.dom.Document;

import de.wittich.transformation.persistence.ConfigurationEntity;
import de.wittich.transformation.tree.TreeElementAttribute;

/**
 * 
 * 
 * @author Frank
 */
public interface XMLValueTransformer {

	/**
	 * Extracts a value from the given {@link Document} and transforms it into a
	 * {@link TreeElementAttribute}. The value extraction and transformation are
	 * based on the contents of the given {@link ConfigurationEntity}.
	 * 
	 * @param doc {@link Document} representing a XML document
	 * @param configEntity used to extract and transform a value from the {@link Document}
	 * @return {@link TreeElementAttribute} representing an extracted and transformed value.
	 */
	public TreeElementAttribute getValue(Document doc, ConfigurationEntity configEntity);

}
