package br.inpe.dpi.terrabrasilis.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.inpe.dpi.terrabrasilis.domain.Vision;
import br.inpe.dpi.terrabrasilis.domain.VisionDTO;
import reactor.core.publisher.Flux;

/**
 * 
 * @author jether
 *
 */
@Repository
public interface VisionToVisionRepository extends ReactiveMongoRepository<VisionDTO, String> {
    Flux<VisionDTO> findByRoot(Vision root);
}
