package br.inpe.dpi.terrabrasilis.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.inpe.dpi.terrabrasilis.domain.Subdomain;

/**
 * 
 * @author jether
 *
 */
@Repository
public interface SubdomainRepository extends ReactiveMongoRepository<Subdomain, String> {
	
}
