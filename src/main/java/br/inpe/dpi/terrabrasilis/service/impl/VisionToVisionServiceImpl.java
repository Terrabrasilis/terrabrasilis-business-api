package br.inpe.dpi.terrabrasilis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.inpe.dpi.terrabrasilis.domain.Vision;
import br.inpe.dpi.terrabrasilis.domain.VisionDTO;
import br.inpe.dpi.terrabrasilis.repository.VisionToVisionRepository;
import br.inpe.dpi.terrabrasilis.service.VisionToVisionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author jether
 */
@Service
public class VisionToVisionServiceImpl implements VisionToVisionService {

    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(VisionToVisionServiceImpl.class);

    private VisionToVisionRepository visionToVisionRepository;

    public VisionToVisionServiceImpl(VisionToVisionRepository visionToVisionRepository) {
        this.visionToVisionRepository = visionToVisionRepository;
    }

    @Override
    public Mono<VisionDTO> save(VisionDTO vision) {
        logger.debug("Request to save VisionDTO : {}", vision);
        return this.visionToVisionRepository.save(vision);
    }

    @Override
    public Flux<VisionDTO> findAll() {
        logger.debug("Request to get all VisionsDTO");
        return this.visionToVisionRepository.findAll();
    }

    @Override
	public Mono<VisionDTO> findById(String id) {
        logger.debug("Request to get VisionDTO : {}", id);
		return this.visionToVisionRepository.findById(id);
    }

    @Override
    public Flux<VisionDTO> findByRoot(Vision vision) {
        logger.debug("Request to get VisionDTO by root: {}", vision.getId());
        return this.visionToVisionRepository.findByRoot(vision);
	}

	@Override
	public Flux<VisionDTO> saveAll(List<VisionDTO> visions) {
		logger.debug("Request to save VisionDTO in batch : {}", visions);
		return this.visionToVisionRepository.saveAll(visions);
	}
}