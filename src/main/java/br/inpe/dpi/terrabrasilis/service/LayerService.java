package br.inpe.dpi.terrabrasilis.service;

import java.io.Serializable;

import br.inpe.dpi.terrabrasilis.domain.Layer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
public interface LayerService extends Serializable {
	Mono<Layer> save(Layer layer);
	Flux<Layer> findAll();
	Mono<Layer> findById(String id);
}
