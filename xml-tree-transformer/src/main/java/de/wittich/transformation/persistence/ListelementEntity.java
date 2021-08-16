package de.wittich.transformation.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "T_Listelement")
public class ListelementEntity {
	
	@Id
	private int listelementId;

	private String value;
	
	private Integer attributeId;
	
}
