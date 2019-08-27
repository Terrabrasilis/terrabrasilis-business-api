package br.inpe.dpi.terrabrasilis.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.inpe.dpi.terrabrasilis.domain.Layer;

/**
 * 
 * @author jether.rodrigues
 *
 */
@Repository
public interface LayerRepository extends ReactiveMongoRepository<Layer, String> {

}
