package br.inpe.dpi.terrabrasilis.service;

import java.io.Serializable;
import java.util.List;

import br.inpe.dpi.terrabrasilis.domain.Download;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether
 *
 */
public interface DownloadService extends Serializable {
	Flux<Download> saveAll(List<Download> downloads);
	Mono<Download> save(Download download);
	Flux<Download> findAll();
	Mono<Download> findById(String id);
}
