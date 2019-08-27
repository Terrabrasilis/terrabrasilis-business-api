package br.inpe.dpi.terrabrasilis.resource;

import static br.inpe.dpi.terrabrasilis.util.Constants.API;
import static br.inpe.dpi.terrabrasilis.util.Constants.TOOL;
import static br.inpe.dpi.terrabrasilis.util.Constants.V1;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inpe.dpi.terrabrasilis.domain.Tool;
import br.inpe.dpi.terrabrasilis.exception.ToolAlreadyExistsException;
import br.inpe.dpi.terrabrasilis.service.ToolService;
import br.inpe.dpi.terrabrasilis.util.HeaderUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author jether
 *
 */
@RestController
@RequestMapping(API + V1 + TOOL)
public class ToolResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(ToolResource.class);
	
	private ToolService toolService;
	
	public ToolResource(ToolService toolService) {
		this.toolService = toolService;
	}
	
	@GetMapping("/all")
	public Flux<Tool> getAllTool() {
		logger.debug("REST request to get all Tools");
		return toolService.findAll();
	}
	
	@GetMapping("{id}")
	public Mono<Tool> getToolById(@PathVariable String id) {
		logger.debug("REST request to get the Tool");
		return toolService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Mono<Tool>> createNewTool(@Valid @RequestBody Tool tool) {
		logger.debug("REST request to save Tool : {}", tool);
		
		if (tool.getId() != null)
			throw new ToolAlreadyExistsException("A new Tool cannot already "
					+ "have an ID. This Tool alread existis: " + tool.toString());
		
		Mono<Tool> created = toolService.save(tool);	
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Tool", ""))
				.body(created);
	}
	
	/**
	 * POST  /batch : register the tool from list of Tool.
	 * 
	 * @param tools - list of Tool	 
	 * 
	 * @return 200 - OK
	 */
	@PostMapping("/batch")
	public ResponseEntity<?> createInBatch(@Valid @RequestBody List<Tool> tools) {
		logger.debug("REST request to save Tool from list: {}", tools);
		
		Flux<Tool> created = this.toolService.saveAll(tools);
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Datasource", ""))
				.body(created
						.subscribe(System.out::println));
	}
}
