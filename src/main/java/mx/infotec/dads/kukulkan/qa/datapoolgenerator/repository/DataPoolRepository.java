package mx.infotec.dads.kukulkan.qa.datapoolgenerator.repository;

import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.qa.datapoolgenerator.domain.DataPool;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the DataPool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataPoolRepository extends MongoRepository<DataPool,String> {
    
	public DataPool findById(String id);
	
}
