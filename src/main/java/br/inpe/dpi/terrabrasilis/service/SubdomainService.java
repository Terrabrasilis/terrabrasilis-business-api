package br.inpe.dpi.terrabrasilis.service;

import java.io.Serializable;
import java.util.List;

import br.inpe.dpi.terrabrasilis.domain.Subdomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
public interface SubdomainService extends Serializable {
	Flux<Subdomain> saveAll(List<Subdomain> subdomains);
	Mono<Subdomain> save(Subdomain subdomain);
	Flux<Subdomain> findAll();
	Mono<Subdomain> findById(String id);	
}
