package br.inpe.dpi.terrabrasilis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.inpe.dpi.terrabrasilis.domain.Layer;
import br.inpe.dpi.terrabrasilis.repository.LayerRepository;
import br.inpe.dpi.terrabrasilis.service.LayerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether.rodrigues
 *
 */
@Service
public class LayerServiceImpl implements LayerService {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(LayerServiceImpl.class);
	
	private LayerRepository layerRepository;
	
	public LayerServiceImpl(LayerRepository layerRepository) {
		this.layerRepository = layerRepository;
	}
	
	@Override
	public Mono<Layer> save(Layer layer) {
		logger.debug("Request to save Layer : {}", layer);
		return this.layerRepository.save(layer);
	}

	@Override
	public Flux<Layer> findAll() {
		logger.debug("Request to get all Layers.");
		return this.layerRepository.findAll();
	}

	@Override
	public Mono<Layer> findById(String id) {
		logger.debug("Request to get Layer : {}", id);
		return this.layerRepository.findById(id);
	}

}
