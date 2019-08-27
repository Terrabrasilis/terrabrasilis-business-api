package br.inpe.dpi.terrabrasilis.resource;

import static br.inpe.dpi.terrabrasilis.util.Constants.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inpe.dpi.terrabrasilis.domain.Vision;
import br.inpe.dpi.terrabrasilis.domain.VisionDTO;
import br.inpe.dpi.terrabrasilis.exception.VisionNotFoundException;
import br.inpe.dpi.terrabrasilis.service.VisionService;
import br.inpe.dpi.terrabrasilis.service.VisionToVisionService;
import br.inpe.dpi.terrabrasilis.util.HeaderUtil;
import org.springframework.cache.annotation.Cacheable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author jether
 *
 */
@RestController
@RequestMapping(API + V1 + VISION_TO_VISION)
@EnableCaching
public class VisionToVisionResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(VisionToVisionResource.class);
	
	private VisionToVisionService visionToVisionService;
	private VisionService visionService;
	
	public VisionToVisionResource(VisionToVisionService visionToVisionService, VisionService visionService) {
		this.visionToVisionService = visionToVisionService;
		this.visionService = visionService;
	}
	
	@GetMapping("/all")
        @Cacheable(CACHE_DEFORESTATION)
	public Flux<VisionDTO> getAllVisionDTO() {
		logger.debug("REST request to get all VisionDTO");
		return visionToVisionService.findAll();
	}
	
	@GetMapping("{id}")
	public Mono<VisionDTO> getVisionDTOById(@PathVariable String id) {
		logger.debug("REST request to get the VisionDTO");
		return visionToVisionService.findById(id);
	}
	
	@PostMapping
	@CachePut(CACHE_DEFORESTATION)
	public ResponseEntity<Mono<VisionDTO>> createNewVisionDTO(@Valid @RequestBody VisionDTO vision) {
		logger.debug("REST request to save VisionDTO : {}", vision);
		
		Mono<VisionDTO> created = visionToVisionService.save(vision);	
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Vision", ""))
				.body(created);
	}
	
	/**
	 * POST  /batch : register the visionDTO from list of VisionDTOs.
	 * 
	 * @param visions - list of VisionDTO	 
	 * 
	 * @return 200 - OK
	 */
	@PostMapping("/batch")
	@CachePut(CACHE_DEFORESTATION)
	public ResponseEntity<?> createInBatch(@Valid @RequestBody List<VisionDTO> visions) {
		logger.debug("REST request to save VisionDTO from list: {}", visions);
		
		Flux<VisionDTO> created = this.visionToVisionService.saveAll(visions);
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("VisionDTO", ""))
				.body(created
						.subscribe(System.out::println));
	}
	
	/**
	 * PUT  / : update the visionDTO object.
	 * 
	 * @param dto - object to update	 
	 * 
	 * @return 200 - OK
	 */
	@PutMapping
	@CachePut(CACHE_DEFORESTATION)
	public Flux<ResponseEntity<VisionDTO>> updateVisionDTO(@Valid @RequestBody VisionDTO dto) {
		logger.debug("REST request to save Vision : {}", dto);
		
		Vision vision = this.visionService.findById(dto.getRootVisionId()).blockOptional().orElseThrow(VisionNotFoundException::new);
		
		return this.visionToVisionService.findByRoot(vision)
				.flatMap(toUpdate -> {
					List<Vision> visions = new ArrayList<>(toUpdate.getVisions());
					visions.addAll(dto.getVisions());
					
					toUpdate.setVisions(visions);
					return this.visionToVisionService.save(toUpdate);
				})
				.map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
