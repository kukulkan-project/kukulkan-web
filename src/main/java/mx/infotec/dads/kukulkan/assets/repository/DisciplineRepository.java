package mx.infotec.dads.kukulkan.assets.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.assets.domain.Discipline;


/**
 * Spring Data JPA repository for the Discipline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisciplineRepository extends MongoRepository<Discipline,String> {

}
