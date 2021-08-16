package de.wittich.transformation.persistence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "T_Configuration")
public class ConfigurationEntity {
	
	@Id
	private int configurationId;
	
	private Integer rootTreeElementId;

	private String treePath;
	
	private Integer attributeId;
	
	private String defaultValue;
	
	private String xpath;
	
	private String valueTransformerName;
	
	/**
	 * Splits the {@link #treePath} into single integers and returns them as list
	 * <p>
	 * E.g.: "1/4/15/19" is returned as [1, 4, 15, 19]
	 * @return Integer List representation of the {@link #treePath}
	 */
	@Transient
	public List<Integer> getTreePathList(){
		return Stream.of(treePath.split("/")) //
			.filter(s -> !s.isEmpty()) //
			.map(s -> Integer.parseInt(s)) //
			.collect(Collectors.toList());
	}

}
