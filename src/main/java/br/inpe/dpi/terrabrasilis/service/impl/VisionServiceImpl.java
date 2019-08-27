package br.inpe.dpi.terrabrasilis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.inpe.dpi.terrabrasilis.domain.Vision;
import br.inpe.dpi.terrabrasilis.repository.VisionRepository;
import br.inpe.dpi.terrabrasilis.service.VisionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
@Service
public class VisionServiceImpl implements VisionService {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(VisionServiceImpl.class);
	
	private final VisionRepository repository;
	
	public VisionServiceImpl(VisionRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Mono<Vision> save(Vision vision) {
		logger.debug("Request to save Vision : {}", vision);
		return repository.save(vision);
	}

	@Override
	public Flux<Vision> findAll() {
		logger.debug("Request to get all Visions");
		return repository.findAll();
	}

	@Override
	public Mono<Vision> findById(String id) {
		logger.debug("Request to get Vision : {}", id);
		return repository.findById(id);
	}

	@Override
	public Mono<Vision> findByName(String name) {
		logger.debug("Request to get Vision by name : {}", name);
		return repository.findByName(name);
	}
}
