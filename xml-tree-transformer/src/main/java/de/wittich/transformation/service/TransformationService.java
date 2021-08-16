package de.wittich.transformation.service;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import de.wittich.transformation.persistence.ConfigurationEntity;
import de.wittich.transformation.persistence.ConfigurationRepository;
import de.wittich.transformation.service.transformer.XMLValueTransformer;
import de.wittich.transformation.tree.TreeElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransformationService {

	@Autowired
	private XMLValueTransformer valueTransformer;

	@Autowired
	private ConfigurationRepository configRepo;

	/**
	 * Transforms the given xml string into a tree structure depending on the given tree element id.
	 * @param rootTreeElementId 
	 * @param xmlString
	 * @return {@link TreeElement} as root of the resulting tree structure
	 */
	public TreeElement transform(Integer rootTreeElementId, String xmlString) {
		TreeElement product = new TreeElement(rootTreeElementId);
		Document doc = convertXmlToDocument(xmlString);

		for (ConfigurationEntity configEntity : configRepo.findByRootTreeElementId(rootTreeElementId)) {
			product //
				.findSubElemet(configEntity.getTreePathList())  //
				.addAttribute(valueTransformer.getValue(doc, configEntity));
		}

		return product;
	}

	private Document convertXmlToDocument(String xmlString) {
		try {
			return DocumentBuilderFactory.newInstance() //
					.newDocumentBuilder() //
					.parse(new InputSource(new StringReader(xmlString)));
		} catch (Exception e) {
			log.error("Parsing of XML failed.", e);
		}
		return null;
	}
}
