package mx.infotec.dads.kukulkan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.domain.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
	
	@Query("{ 'name' : { $regex: ?0, $options: 'i'} }")
	public Category findOneByName(String name);

}
