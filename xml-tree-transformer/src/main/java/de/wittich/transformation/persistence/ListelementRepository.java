package de.wittich.transformation.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListelementRepository extends JpaRepository<ListelementEntity, Integer> {

	@Query
	public Optional<ListelementEntity> findByValueAndAttributeId(String value, Integer attributeId);
	
}
