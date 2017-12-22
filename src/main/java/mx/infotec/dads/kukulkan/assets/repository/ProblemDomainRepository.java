package mx.infotec.dads.kukulkan.assets.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.assets.domain.ProblemDomain;

/**
 * Spring Data JPA repository for the ProblemDomain entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProblemDomainRepository extends MongoRepository<ProblemDomain, String> {

}
