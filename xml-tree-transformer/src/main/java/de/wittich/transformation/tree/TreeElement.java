package de.wittich.transformation.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Class to represent a tree structure.
 * Each element consisting of an id, list of child elements and a list of attributes.
 * 
 * @author Frank
 *
 */
@Data
public class TreeElement {

	private final Integer treeElementId;
	
	@JsonInclude(value = Include.NON_EMPTY)
	private List<TreeElement> subElements = new ArrayList<TreeElement>();
	

	@JsonInclude(value = Include.NON_EMPTY)
	private List<TreeElementAttribute> attributes = new ArrayList<>();	
	
	/**
	 * Returns the Sub-{@link TreeElement} corresponding to the list of provided ids.
	 * Any intermediate elements that do not exists are created in the process. 
	 * @param list Ordered list of suelement ids.
	 * @return 
	 */
	public TreeElement findSubElemet(List<Integer> list) {
		TreeElement subProduct = getDirectSubElement(list.remove(0));
		return list.isEmpty() ? subProduct : subProduct.findSubElemet(list);
	}

	/**
	 * Searches for the subelement of this {@link TreeElement} with the given id.
	 * If no element with the given id exists, it is created and added to the list of subelements.
	 * @param subElementId Id of the Sub {@link TreeElement}
	 * @return {@link TreeElement}
	 */
	private TreeElement getDirectSubElement(Integer subElementId) {	
		if(treeElementId.equals(subElementId))
			return this;
		
		Optional<TreeElement> optElement = subElements.stream() //
				.filter(sub -> subElementId.equals(sub.getTreeElementId())) //
				.findFirst();
		
		if(optElement.isPresent()) {
			return optElement.get();
		}
		else {
			TreeElement subElement = new TreeElement(subElementId);
			this.subElements.add(subElement);
			return subElement;
		}
	}

	public void addAttribute(TreeElementAttribute attribute) {
		attributes.add(attribute);
	}
}
