package mx.infotec.dads.kukulkan.assets.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.assets.domain.Granularity;

/**
 * Spring Data JPA repository for the Granularity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GranularityRepository extends MongoRepository<Granularity, String> {

}
