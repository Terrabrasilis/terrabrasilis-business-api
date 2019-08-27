package br.inpe.dpi.terrabrasilis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.inpe.dpi.terrabrasilis.domain.Tool;
import br.inpe.dpi.terrabrasilis.repository.ToolRepository;
import br.inpe.dpi.terrabrasilis.service.ToolService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
@Service
public class ToolServiceImpl implements ToolService {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(ToolServiceImpl.class);
	
	private final ToolRepository repository;
	
	public ToolServiceImpl(ToolRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Mono<Tool> save(Tool tool) {
		logger.debug("Request to save Tool : {}", tool);
		return repository.save(tool);
	}

	@Override
	public Flux<Tool> findAll() {
		logger.debug("Request to get all Tools");
		return repository.findAll();
	}

	@Override
	public Mono<Tool> findById(String id) {
		logger.debug("Request to get Tool : {}", id);
		return repository.findById(id);
	}

	@Override
	public Flux<Tool> saveAll(List<Tool> tools) {
		logger.debug("Request to save in batch Tools : {}", tools);
		return repository.saveAll(tools);
	}

}
