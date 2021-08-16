package de.wittich.transformation.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Integer> {

	@Query
	public List<ConfigurationEntity> findByRootTreeElementId(Integer rootTreeElementId);
	
	@Query("Select Distinct ce.valueTransformerName From ConfigurationEntity ce Where ce.valueTransformerName IS NOT NULL")
	public List<String> findDistinctValueTransformerName();
	
}
