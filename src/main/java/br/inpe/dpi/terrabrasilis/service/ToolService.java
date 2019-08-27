package br.inpe.dpi.terrabrasilis.service;

import java.io.Serializable;
import java.util.List;

import br.inpe.dpi.terrabrasilis.domain.Tool;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
public interface ToolService extends Serializable {
	Mono<Tool> save(Tool tool);
	Flux<Tool> findAll();
	Mono<Tool> findById(String id);
	Flux<Tool> saveAll(List<Tool> tools);
}
