package br.inpe.dpi.terrabrasilis.service;

import java.io.Serializable;
import java.util.List;

import br.inpe.dpi.terrabrasilis.domain.Datasource;
import br.inpe.dpi.terrabrasilis.enuns.DatasourceType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
public interface DatasourceService extends Serializable {
	Flux<Datasource> saveAll(List<Datasource> datasources);
	Mono<Datasource> save(Datasource datasource);
	Flux<Datasource> findAll();
	Mono<Datasource> findById(String id);
	Flux<Datasource> findByType(DatasourceType type);
}
