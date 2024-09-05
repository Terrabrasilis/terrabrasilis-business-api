package br.inpe.dpi.terrabrasilis.resource;

import static br.inpe.dpi.terrabrasilis.util.Constants.API;
import static br.inpe.dpi.terrabrasilis.util.Constants.DOWNLOAD;
import static br.inpe.dpi.terrabrasilis.util.Constants.V1;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inpe.dpi.terrabrasilis.domain.Download;
import br.inpe.dpi.terrabrasilis.service.DownloadService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author jether
 *
 */
@RestController
@RequestMapping(API + V1 + DOWNLOAD)
public class DownloadResource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(DownloadResource.class);
	
	private DownloadService downloadService;
	
	public DownloadResource(DownloadService downloadService) {
		this.downloadService = downloadService;
	}
	
	@CrossOrigin(allowCredentials="true", value="*")
	@GetMapping("/all")
	public Flux<Download> getAllDownload() {
		logger.debug("REST request to get all Download");
		return downloadService.findAll();
	}
	
	@GetMapping("{id}")
	public Mono<Download> getDownloadById(@PathVariable String id) {
		logger.debug("REST request to get the Download");
		return downloadService.findById(id);
	}
	
	/*@PostMapping
	public ResponseEntity<Mono<Download>> createNewDownload(@Valid @RequestBody Download download) {
		logger.debug("REST request to save Download : {}", download);
		
		if (download.getId() != null)
			throw new DownloadAlreadyExistsException("A new Download cannot already "
					+ "have an ID. This Download alread existis: " + download.toString());
			
		Mono<Download> created = downloadService.save(download);	
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Download", ""))
				.body(created);
	}*/
	
	/**
	 * POST  /batch : register the downloas in batch.
	 * 
	 * @param downloads - list of Download	 
	 * 
	 * @return 200 - OK
	 */
	/*@PostMapping("/batch")
	public ResponseEntity<?> createInBatch(@Valid @RequestBody List<Download> downloads) {
		logger.debug("REST request to save Downloads from list: {}", downloads);
		
		Flux<Download> created = this.downloadService.saveAll(downloads);
		
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert("Downloads", ""))
				.body(created
						.subscribe(System.out::println));
		
	}*/
}
