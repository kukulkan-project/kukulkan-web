package mx.infotec.dads.kukulkan.reports.repository;

import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.reports.domain.ReportIndicator;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ReportIndicator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportIndicatorRepository extends MongoRepository<ReportIndicator,String> {

}
