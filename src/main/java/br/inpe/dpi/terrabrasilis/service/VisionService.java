package br.inpe.dpi.terrabrasilis.service;

import java.io.Serializable;

import br.inpe.dpi.terrabrasilis.domain.Vision;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
public interface VisionService extends Serializable {
	Mono<Vision> save(Vision vision);
	Flux<Vision> findAll();
	Mono<Vision> findById(String id);
	Mono<Vision> findByName(String name);
}
