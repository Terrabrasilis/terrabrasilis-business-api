package br.inpe.dpi.terrabrasilis.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.inpe.dpi.terrabrasilis.domain.Vision;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
@Repository
public interface VisionRepository extends ReactiveMongoRepository<Vision, String> {
	Mono<Vision> findByName(String name);
}
