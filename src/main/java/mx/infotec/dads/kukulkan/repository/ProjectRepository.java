package mx.infotec.dads.kukulkan.repository;

import mx.infotec.dads.kukulkan.domain.Project;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Project entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectRepository extends MongoRepository<Project,String> {

}
