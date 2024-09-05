package br.inpe.dpi.terrabrasilis.resource;

import static br.inpe.dpi.terrabrasilis.util.Constants.API;
import static br.inpe.dpi.terrabrasilis.util.Constants.SUBDOMAIN;
import static br.inpe.dpi.terrabrasilis.util.Constants.V1;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inpe.dpi.terrabrasilis.domain.Subdomain;
import br.inpe.dpi.terrabrasilis.service.SubdomainService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author jether
 *
 */
@RestController
@RequestMapping(API + V1 + SUBDOMAIN)
public class SubdomainResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(SubdomainResource.class);
	
	private SubdomainService subdomainService;
	
	public SubdomainResource(SubdomainService subdomainService) {
		this.subdomainService = subdomainService;
	}
	
	/**
	 * GET /all : get a list of subdomain.
	 *  
	 * @return 200 - OK and Flux of Subdomain
	 */
	@GetMapping("/all")
	public Flux<Subdomain> getAllSubdomain() {
		logger.debug("REST request to get all Subdomains");
		return subdomainService.findAll();
	}
	
	/**
	 * GET /{id} : get the subdomain by ID.
	 * 
	 * @param id - String id	 
	 * 
	 * @return 200 - OK and Mono of Subdomain
	 */
	@GetMapping("{id}")
	public Mono<Subdomain> getSubdomainById(@PathVariable String id) {
		logger.debug("REST request to get the Subdomain");
		return subdomainService.findById(id);
	}
	
	/**
	 * POST / : register the subdomain.
	 * 
	 * @param subdomain - Subdomain
	 * @throws SubdomainAlreadyExistsException - Already exist a subdomain if ID not null
	 * 
	 * @return 200 - OK and Mono of Subdomain
	 */
	/*@PostMapping
	public ResponseEntity<Mono<Subdomain>> createNewSubdomain(@Valid @RequestBody Subdomain subdomain) {
		logger.debug("REST request to save Subdomain : {}", subdomain);
		
		if (subdomain.getId() != null)
			throw new SubdomainAlreadyExistsException("A new Subdomain cannot already "
					+ "have an ID. This Subdomain alread existis: " + subdomain.toString());
		
		Mono<Subdomain> created = subdomainService.save(subdomain);	
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Subdomain", ""))
				.body(created);
	}*/

	/**
	 * POST  /batch : register the subdomain from list of Subdomains.
	 * 
	 * @param subdomains - list of Subdomain	 
	 * 
	 * @return 200 - OK
	 */
	/*@PostMapping("/batch")
	public ResponseEntity<?> createInBatch(@Valid @RequestBody List<Subdomain> subdomains) {
		logger.debug("REST request to save Subdomain from list: {}", subdomains);
		
		Flux<Subdomain> created = this.subdomainService.saveAll(subdomains);
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Subdomain", ""))
				.body(created
						.subscribe(System.out::println));
	}*/	
}
