package br.inpe.dpi.terrabrasilis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.inpe.dpi.terrabrasilis.domain.Subdomain;
import br.inpe.dpi.terrabrasilis.repository.SubdomainRepository;
import br.inpe.dpi.terrabrasilis.service.SubdomainService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
@Service
public class SubdomainServiceImpl implements SubdomainService {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(SubdomainServiceImpl.class);
	
	private final SubdomainRepository repository;
	
	public SubdomainServiceImpl(SubdomainRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Mono<Subdomain> save(Subdomain subdomain) {
		logger.debug("Request to save Subdomain : {}", subdomain);
		return repository.save(subdomain);
	}

	@Override
	public Flux<Subdomain> findAll() {
		logger.debug("Request to get all Subdomains");
		return repository.findAll();
	}

	@Override
	public Mono<Subdomain> findById(String id) {
		logger.debug("Request to get Subdomain : {}", id);
		return repository.findById(id);
	}

	@Override
	public Flux<Subdomain> saveAll(List<Subdomain> subdomains) {
		logger.debug("Request to save a lsit of Subdomain : {}", subdomains);
		return this.repository.saveAll(subdomains);
	}

}
