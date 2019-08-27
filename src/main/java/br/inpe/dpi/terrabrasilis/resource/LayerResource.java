package br.inpe.dpi.terrabrasilis.resource;

import static br.inpe.dpi.terrabrasilis.util.Constants.API;
import static br.inpe.dpi.terrabrasilis.util.Constants.LAYER;
import static br.inpe.dpi.terrabrasilis.util.Constants.V1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inpe.dpi.terrabrasilis.domain.Datasource;
import br.inpe.dpi.terrabrasilis.domain.Download;
import br.inpe.dpi.terrabrasilis.domain.Layer;
import br.inpe.dpi.terrabrasilis.domain.Subdomain;
import br.inpe.dpi.terrabrasilis.domain.Tool;
import br.inpe.dpi.terrabrasilis.exception.LayerAlreadyExistsException;
import br.inpe.dpi.terrabrasilis.service.DatasourceService;
import br.inpe.dpi.terrabrasilis.service.LayerService;
import br.inpe.dpi.terrabrasilis.service.SubdomainService;
import br.inpe.dpi.terrabrasilis.util.HeaderUtil;
import one.util.streamex.StreamEx;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author jether
 *
 */
@RestController
@RequestMapping(API + V1 + LAYER)
public class LayerResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(LayerResource.class);
	
	private final LayerService layerService;
	private final SubdomainService subdomainService;
	private final DatasourceService datasourceService;

	public LayerResource(LayerService layerService, SubdomainService subdomainService, DatasourceService datasourceService) {
		this.layerService = layerService;
		this.subdomainService = subdomainService;
		this.datasourceService = datasourceService;
	}

	@GetMapping("/all")
	public Flux<Layer> getAllLayer() {
		logger.debug("REST request to get all Layer");
		return layerService.findAll();
	}
	
	@GetMapping("{id}")
	public Mono<Layer> getLayerById(@PathVariable String id) {
		logger.debug("REST request to get the Layer");
		return layerService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Mono<Layer>> createNewLayer(@Valid @RequestBody Layer layer) {
		logger.debug("REST request to save Layer : {}", layer);
		
		if (layer.getId() != null)
			throw new LayerAlreadyExistsException("A new Layer cannot already "
					+ "have an ID. This Layer alread existis: " + layer.toString());
		
		if (layer.getDatasource() != null) {
			Optional<Datasource> datasource = datasourceService.findById(layer.getDatasource().getId()).blockOptional();
			if (datasource.isPresent())
				layer.setDatasource(datasource.get());
		}
		
		if (!layer.getSubdomains().isEmpty()) {
			List<Subdomain> subdomains = new ArrayList<Subdomain>();
			layer.getSubdomains().forEach(s -> {
				Optional<Subdomain> subdomain = subdomainService.findById(s.getId()).blockOptional();
				if (subdomain.isPresent())
					subdomains.add(subdomain.get());
			});
			layer.setSubdomains(subdomains);
		}
			
		Mono<Layer> created = layerService.save(layer);	
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Layer", ""))
				.body(created);
	}
	
	/**
	 * PUT  / : update the Layer object.
	 * 
	 * @param layer - object to update	 
	 * 
	 * @return 200 - OK
	 */
	@PutMapping
	public Mono<ResponseEntity<Layer>> updateLayer(@Valid @RequestBody Layer layer) {
		logger.debug("REST request to update Layer : {}", layer);
		
		return this.layerService.findById(layer.getId())
				.flatMap(toUpdate -> {
					if(layer.getMetadata() != null) {
						toUpdate.setMetadata(layer.getMetadata());
					}
                                        
                                        if(layer.getDashboard() != null) {
						toUpdate.setDashboard(layer.getDashboard());
					}
					
					List<Subdomain> subdomains = new ArrayList<>(toUpdate.getSubdomains());
					Set<Tool> tools = new HashSet<>(toUpdate.getTools());    
                    List<Download> downloads = new ArrayList<>(toUpdate.getDownloads());
					
					subdomains.addAll(layer.getSubdomains());
					tools.addAll(layer.getTools());
                    downloads.addAll(layer.getDownloads());
                        
					toUpdate.setSubdomains(StreamEx.of(subdomains).distinct(Subdomain::getId).collect(Collectors.toList()));
					toUpdate.setTools(StreamEx.of(tools).distinct(Tool::getId).collect(Collectors.toList()));
                    toUpdate.setDownloads(StreamEx.of(downloads).distinct(Download::getId).collect(Collectors.toList()));
                                        
					return this.layerService.save(toUpdate);
				})
				.map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
