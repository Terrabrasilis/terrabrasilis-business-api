package br.inpe.dpi.terrabrasilis.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.inpe.dpi.terrabrasilis.domain.Tool;

/**
 * 
 * @author jether
 *
 */
@Repository
public interface ToolRepository extends ReactiveMongoRepository<Tool, String> {
	
}
