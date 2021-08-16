package de.wittich.transformation.controller;

import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.wittich.transformation.service.TransformationService;
import de.wittich.transformation.tree.TreeElement;

@RestController
public class XMLTransformationController {
	
	@Autowired 
	private TransformationService importService;
	
	@PostMapping(value = "/transform/{rootTreeElementId}")
	public TreeElement importApplication(@PathVariable("rootTreeElementId") Integer rootTreeElementId, @RequestParam String applicationXml) throws XPathExpressionException {
		return importService.transform(rootTreeElementId, applicationXml);
	}
	


}
