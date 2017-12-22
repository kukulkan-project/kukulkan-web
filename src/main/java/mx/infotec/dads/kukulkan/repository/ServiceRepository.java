package mx.infotec.dads.kukulkan.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import mx.infotec.dads.kukulkan.domain.Service;

public interface ServiceRepository extends MongoRepository<Service, String> {

    public List<Service> findAllByCategory(String categoryId);

    @Query("{ 'name' : { $regex: ?0, options: 'i'} }")
    public Service findOneByName(String name);

}
