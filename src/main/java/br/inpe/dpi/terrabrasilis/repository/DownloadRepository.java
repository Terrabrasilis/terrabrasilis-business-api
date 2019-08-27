package br.inpe.dpi.terrabrasilis.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.inpe.dpi.terrabrasilis.domain.Download;

/**
 * 
 * @author jether
 *
 */
@Repository
public interface DownloadRepository extends ReactiveMongoRepository<Download, String> {

}
