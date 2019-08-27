package br.inpe.dpi.terrabrasilis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.inpe.dpi.terrabrasilis.domain.Datasource;
import br.inpe.dpi.terrabrasilis.enuns.DatasourceType;
import br.inpe.dpi.terrabrasilis.repository.DatasourceRepository;
import br.inpe.dpi.terrabrasilis.service.DatasourceService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether.rodrigues
 *
 */
@Service
public class DatasourceServiceImpl implements DatasourceService {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(DatasourceServiceImpl.class);

	private DatasourceRepository datasourceRepository;
	
	public DatasourceServiceImpl(DatasourceRepository datasourceRepository) {
		this.datasourceRepository = datasourceRepository;
	}
	
	@Override
	public Mono<Datasource> save(Datasource datasource) {
		logger.debug("Request to save Datasource : {}", datasource);
		return this.datasourceRepository.save(datasource);
	}

	@Override
	public Flux<Datasource> findAll() {
		logger.debug("Request to get all Datasource.");
		return this.datasourceRepository.findAll();
	}

	@Override
	public Mono<Datasource> findById(String id) {
		logger.debug("Request to get Datasource : {}", id);
		return this.datasourceRepository.findById(id);
	}

	@Override
	public Flux<Datasource> saveAll(List<Datasource> datasources) {
		logger.debug("Request to save a lsit of Datasource : {}", datasources);
		return this.datasourceRepository.saveAll(datasources);
	}

	@Override
	public Flux<Datasource> findByType(DatasourceType type) {
		logger.debug("Request to get Datasource by type : {}", type);
		return this.datasourceRepository.findByType(type);
	}
}
