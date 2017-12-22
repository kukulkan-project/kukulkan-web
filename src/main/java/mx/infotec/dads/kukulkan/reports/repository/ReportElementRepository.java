package mx.infotec.dads.kukulkan.reports.repository;

import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.reports.domain.ReportElement;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ReportElement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportElementRepository extends MongoRepository<ReportElement,String> {

}
