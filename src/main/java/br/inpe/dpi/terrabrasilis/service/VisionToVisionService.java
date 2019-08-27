package br.inpe.dpi.terrabrasilis.service;

import java.io.Serializable;
import java.util.List;

import br.inpe.dpi.terrabrasilis.domain.Vision;
import br.inpe.dpi.terrabrasilis.domain.VisionDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
public interface VisionToVisionService extends Serializable {
	Mono<VisionDTO> save(VisionDTO vision);
	Flux<VisionDTO> findAll();
	Mono<VisionDTO> findById(String id);
	Flux<VisionDTO> findByRoot(Vision vision);
	Flux<VisionDTO> saveAll(List<VisionDTO> visions);
}
