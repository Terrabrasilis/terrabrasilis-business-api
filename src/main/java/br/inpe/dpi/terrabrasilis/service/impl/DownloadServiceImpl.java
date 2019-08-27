package br.inpe.dpi.terrabrasilis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.inpe.dpi.terrabrasilis.domain.Download;
import br.inpe.dpi.terrabrasilis.repository.DownloadRepository;
import br.inpe.dpi.terrabrasilis.service.DownloadService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author jether.rodrigues
 *
 */
@Service
public class DownloadServiceImpl implements DownloadService {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(DownloadServiceImpl.class);

	private DownloadRepository downloadRepository;
	
	public DownloadServiceImpl(DownloadRepository downloadRepository) {
		this.downloadRepository = downloadRepository;
	}
	
	@Override
	public Mono<Download> save(Download download) {
		logger.debug("Request to save Download : {}", download);
		return this.downloadRepository.save(download);
	}

	@Override
	public Flux<Download> findAll() {
		logger.debug("Request to get all Download.");
		return this.downloadRepository.findAll();
	}

	@Override
	public Mono<Download> findById(String id) {
		logger.debug("Request to get Download : {}", id);
		return this.downloadRepository.findById(id);
	}

	@Override
	public Flux<Download> saveAll(List<Download> downloads) {
		logger.debug("Request to save Downloads : {}", downloads);
		return this.downloadRepository.saveAll(downloads);
	}
}
