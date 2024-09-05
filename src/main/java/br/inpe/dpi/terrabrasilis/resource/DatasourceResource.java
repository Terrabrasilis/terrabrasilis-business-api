package br.inpe.dpi.terrabrasilis.resource;

import static br.inpe.dpi.terrabrasilis.util.Constants.API;
import static br.inpe.dpi.terrabrasilis.util.Constants.DATASOURCE;
import static br.inpe.dpi.terrabrasilis.util.Constants.V1;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inpe.dpi.terrabrasilis.domain.Datasource;
import br.inpe.dpi.terrabrasilis.enuns.DatasourceType;
import br.inpe.dpi.terrabrasilis.service.DatasourceService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author jether
 *
 */
@RestController
@RequestMapping(API + V1 + DATASOURCE)
//@CrossOrigin(origins = "http://example.com", maxAge = 3600)
@CrossOrigin(maxAge = 3600)
public class DatasourceResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(DatasourceResource.class);
	
	private DatasourceService datasourceService;
	
	public DatasourceResource(DatasourceService datasourceService) {
		this.datasourceService = datasourceService;
	}
	
	@CrossOrigin()
	@GetMapping("/all")
	public Flux<Datasource> getAllDatasource() {
		logger.debug("REST request to get all Datasource");
		return datasourceService.findAll();
	}
	
	@CrossOrigin()
	@GetMapping("{id}")
	public Mono<Datasource> getDatasourceById(@PathVariable String id) {
		logger.debug("REST request to get the Datasource");
		return datasourceService.findById(id);
	}
	
	@CrossOrigin()
	@GetMapping("{type}/type")
	public Flux<Datasource> getDatasourceByType(@PathVariable String type) {
		logger.debug("REST request to get the Datasource by DatasourceType");
		DatasourceType typeToSearch = DatasourceType
										.stream()
										.filter(t -> t.getType().equals(type.toUpperCase()))
										.map(d -> d)
										.findAny()
										.get();
		
		return datasourceService.findByType(typeToSearch);
	}
	
	/*@PostMapping
	public ResponseEntity<Mono<Datasource>> createNewDatasource(@Valid @RequestBody Datasource datasource) {
		logger.debug("REST request to save Datasource : {}", datasource);
		
		if (datasource.getId() != null)
			throw new DatasourceAlreadyExistsException("A new Datasource cannot already "
					+ "have an ID. This Datasource alread existis: " + datasource.toString());
		
		Mono<Datasource> created = datasourceService.save(datasource);	
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Datasource", ""))
				.body(created);
	}
	*/
	/**
	 * POST  /batch : register the datasource from list of Datasource.
	 * 
	 * @param datasources - list of Datasource	 
	 * 
	 * @return 200 - OK
	 */
	/*@PostMapping("/batch")
	public ResponseEntity<?> createInBatch(@Valid @RequestBody List<Datasource> datasources) {
		logger.debug("REST request to save Datasource from list: {}", datasources);
		
		Flux<Datasource> created = this.datasourceService.saveAll(datasources);
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Datasource", ""))
				.body(created
						.subscribe(System.out::println));
	}*/
	
	/**
	 * PUT  / : update the Datasource object.
	 * 
	 * @param datasource - object to update	 
	 * 
	 * @return 200 - OK
	 */
/*	@PutMapping
	public Mono<ResponseEntity<Datasource>> updateDatasource(@Valid @RequestBody Datasource datasource) {
		logger.debug("REST request to save DatasourDatasourcece : {}", datasource);
		
		return this.datasourceService.findById(datasource.getId())
				.flatMap(toUpdate -> {
					if(datasource.getType() != null) {
						toUpdate.setType(datasource.getType());
					}
					
					List<Download> downloads = new ArrayList<>(toUpdate.getDownloads());
					List<Tool> tools = new ArrayList<>(toUpdate.getTools());
					
					downloads.addAll(datasource.getDownloads());
					tools.addAll(datasource.getTools());
					
					toUpdate.setDownloads(downloads);
					toUpdate.setTools(tools);

					return this.datasourceService.save(toUpdate);
				})
				.map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	*/
}
