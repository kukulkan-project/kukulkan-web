package mx.infotec.dads.kukulkan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.infotec.dads.kukulkan.domain.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

}
