package mx.infotec.dads.kukulkan.assets.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.assets.domain.Phase;

/**
 * Spring Data JPA repository for the Phase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhaseRepository extends MongoRepository<Phase, String> {

}
