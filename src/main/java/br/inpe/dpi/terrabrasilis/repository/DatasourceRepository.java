package br.inpe.dpi.terrabrasilis.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.inpe.dpi.terrabrasilis.domain.Datasource;
import br.inpe.dpi.terrabrasilis.enuns.DatasourceType;
import reactor.core.publisher.Flux;

/**
 * 
 * @author jether
 *
 */
@Repository
public interface DatasourceRepository extends ReactiveMongoRepository<Datasource, String> {
	Flux<Datasource> findByType(DatasourceType type);
}
