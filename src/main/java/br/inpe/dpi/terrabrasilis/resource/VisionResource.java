package br.inpe.dpi.terrabrasilis.resource;

import br.inpe.dpi.terrabrasilis.domain.Download;
import static br.inpe.dpi.terrabrasilis.util.Constants.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inpe.dpi.terrabrasilis.domain.Layer;
import br.inpe.dpi.terrabrasilis.domain.Tool;
import br.inpe.dpi.terrabrasilis.domain.Vision;
import br.inpe.dpi.terrabrasilis.domain.VisionDTO;
import br.inpe.dpi.terrabrasilis.exception.VisionAlreadyExistsException;
import br.inpe.dpi.terrabrasilis.exception.VisionBadRequestException;
import br.inpe.dpi.terrabrasilis.exception.VisionNotFoundException;
import br.inpe.dpi.terrabrasilis.service.VisionService;
import br.inpe.dpi.terrabrasilis.service.VisionToVisionService;
import br.inpe.dpi.terrabrasilis.util.HeaderUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author jether
 *
 */
@RestController
@RequestMapping(API + V1 + VISION)
@CrossOrigin(maxAge = 3600)
@EnableCaching
public class VisionResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(VisionResource.class);
	
	private VisionService visionService;
	private VisionToVisionService visionToVisionService;
	
	public VisionResource(VisionService visionService, VisionToVisionService visionToVisionService) {
		this.visionService = visionService;
		this.visionToVisionService = visionToVisionService;
	}
	
	@GetMapping("/all")
	@CrossOrigin()         
	public Flux<Vision> getAllVision() {
		logger.debug("REST request to get all Vision");
		return visionService.findAll();
	}
	
	@GetMapping("{id}")
	public Mono<Vision> getVisionById(@PathVariable String id) {
		logger.debug("REST request to get the Vision by id");
		return visionService.findById(id);
	}

	@GetMapping("/name/{name}")
	public Mono<Vision> getVisionByName(@PathVariable String name) {
		logger.debug("REST request to get the Vision name");

		Vision vision = visionService.findByName(name).blockOptional().orElseThrow(VisionNotFoundException::new);

		return Mono.just(vision);
	}

	@GetMapping("/name/{name}/all")
	@CrossOrigin()
	@Cacheable(CACHE_DEFORESTATION)
	public Flux<VisionDTO> getAllVisionByName(@PathVariable String name) {
		logger.debug("REST request to get the all Vision name");

		Vision vision = visionService.findByName(name).blockOptional().orElseThrow(VisionNotFoundException::new);								
		
		return visionToVisionService.findByRoot(vision);
	}
	
	@PostMapping
        @CachePut(CACHE_DEFORESTATION)
	public ResponseEntity<Mono<Vision>> createVision(@Valid @RequestBody Vision vision) {
		logger.debug("REST request to save Vision : {}", vision);
		
		if (vision.getId() != null)
			throw new VisionAlreadyExistsException("A new Vision cannot already "
					+ "have an ID. This Vision alread existis: " + vision.toString());
		
		Mono<Vision> created = visionService.save(vision);	
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Vision", ""))
				.body(created);
	}

	/**
	 * PUT  / : update the Vision object.
	 * 
	 * @param vision - object to update	 
	 * 
	 * @return 200 - OK
	 */
	@PutMapping
	@CachePut(CACHE_DEFORESTATION)
	public Mono<ResponseEntity<Vision>> updateVision(@Valid @RequestBody Vision vision) {
		logger.debug("REST request to save Vision : {}", vision);
		
		if (vision.getId() == null)
			throw new VisionBadRequestException();

		return this.visionService.findById(vision.getId())
				.flatMap(toUpdate -> {
					if(vision.getName() != null) {
						toUpdate.setName(vision.getName());
					}
					if(vision.getDescription() != null) {
						toUpdate.setDescription(vision.getDescription());
					}
					if(vision.getStackOrder() != toUpdate.getStackOrder()) {
						toUpdate.setStackOrder(vision.getStackOrder());
					}
                                        
//					if(vision.isEnabled() != toUpdate.isEnabled()) {
//						toUpdate.setEnabled(vision.isEnabled());
//					}
					
					List<Layer> layers = new ArrayList<>(toUpdate.getLayers());
					List<Tool> tools = new ArrayList<>(toUpdate.getTools());
                                        List<Download> downloads = new ArrayList<>(toUpdate.getDownloads());
					
					layers.addAll(vision.getLayers());
					tools.addAll(vision.getTools());
                                        downloads.addAll(vision.getDownloads());
					
					toUpdate.setLayers(layers);
					toUpdate.setTools(tools);
                                        toUpdate.setDownloads(downloads);

					return this.visionService.save(toUpdate);
				})
				.map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
