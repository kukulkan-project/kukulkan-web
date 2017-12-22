package mx.infotec.dads.kukulkan.assets.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.assets.domain.LevelOfImplementation;

/**
 * Spring Data JPA repository for the LevelOfImplementation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelOfImplementationRepository extends MongoRepository<LevelOfImplementation, String> {

}
