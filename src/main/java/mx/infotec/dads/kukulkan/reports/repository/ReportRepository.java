package mx.infotec.dads.kukulkan.reports.repository;

import org.springframework.stereotype.Repository;

import mx.infotec.dads.kukulkan.reports.domain.Report;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Report entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportRepository extends MongoRepository<Report,String> {

}
