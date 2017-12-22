package mx.infotec.dads.kukulkan.config.dbmigrations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import mx.infotec.dads.kukulkan.assets.domain.State;
import mx.infotec.dads.kukulkan.domain.enumeration.ReportType;
import mx.infotec.dads.kukulkan.reports.domain.IndicatorStatus;
import mx.infotec.dads.kukulkan.reports.domain.Report;
import mx.infotec.dads.kukulkan.reports.domain.ReportElement;
import mx.infotec.dads.kukulkan.reports.domain.ReportIndicator;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "003")
public class ReportSetupMigration {

    @ChangeSet(order = "01", author = "dcp", id = "03-indicators")
    public void addIndicator(MongoTemplate mongoTemplate) {
        EntityFactory.createIndicators().forEach(indicator -> mongoTemplate.save(indicator));
    }

    @ChangeSet(order = "02", author = "dcp", id = "04-reports")
    public void addReports(MongoTemplate mongoTemplate) {
        report3(mongoTemplate);
        report2(mongoTemplate);
        report1(mongoTemplate);
    }

    private void report3(MongoTemplate mongoTemplate) {
        Report report = new Report();
        report.setNombre("Operación de Infraestructura de Desarrollo en la DADS");
        report.setBriefDescription("Reporte Semanal de Operación");
        report.setDescription("Reporte Semanal de Operación");
        report.setReportType(ReportType.OPERATIONAL);
        List<ReportIndicator> indicators = mongoTemplate.findAll(ReportIndicator.class);
        report.setIndicators(indicators);
        mongoTemplate.save(report);
        report.setElements(new ArrayList<>());
        List<State> states = mongoTemplate.findAll(State.class);

        ReportElement repo = new ReportElement();
        repo.setNombre("CONACYT - Repositorios");
        repo.setDescription("CONACYT - Repositorios");
        repo.setWeight(1d);
        repo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> repo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(repo);
        report.getElements().add(repo);

        // ext
        ReportElement admo = new ReportElement();
        admo.setNombre("INFOTEC - Procesos Admon");
        admo.setDescription("CONACYT - Repositorios");
        admo.setWeight(1d);
        admo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")) {
                admo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                admo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(admo);
        report.getElements().add(admo);

        ReportElement inne = new ReportElement();
        inne.setNombre("INNE - FSW");
        inne.setDescription("");
        inne.setWeight(1d);
        inne.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> inne.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(inne);
        report.getElements().add(inne);

        ReportElement innovation = new ReportElement();
        innovation.setNombre("INNOVACIÓN");
        innovation.setDescription("");
        innovation.setWeight(1d);
        innovation.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(
                indicator -> innovation.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(innovation);
        report.getElements().add(innovation);

        ReportElement bien = new ReportElement();
        bien.setNombre("CONACYT - BIEN");
        bien.setDescription("");
        bien.setWeight(1d);
        bien.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> bien.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(bien);
        report.getElements().add(bien);

        // ext
        ReportElement sectur = new ReportElement();
        sectur.setNombre("SECTUR - Conectarse al turismo");
        sectur.setDescription("");
        sectur.setWeight(1d);
        sectur.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                sectur.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                sectur.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(sectur);
        report.getElements().add(sectur);

        ReportElement profeco = new ReportElement();
        profeco.setNombre("PROFECO - Vuela");
        profeco.setDescription("");
        profeco.setWeight(1d);
        profeco.setIndicatorStatus(new ArrayList<>());
        indicators
                .forEach(indicator -> profeco.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(profeco);
        report.getElements().add(profeco);

        ReportElement sfp = new ReportElement();
        sfp.setNombre("SFP - Besop");
        sfp.setDescription("");
        sfp.setWeight(1d);
        sfp.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> sfp.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(sfp);
        report.getElements().add(sfp);

        ReportElement b2b = new ReportElement();
        b2b.setNombre("B2B");
        b2b.setDescription("");
        b2b.setWeight(1d);
        b2b.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                b2b.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                b2b.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(b2b);
        report.getElements().add(b2b);

        ReportElement sekc = new ReportElement();
        sekc.setNombre("DADS - SEKC");
        sekc.setDescription("");
        sekc.setWeight(1d);
        sekc.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> sekc.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(sekc);
        report.getElements().add(sekc);

        ReportElement desarrollo = new ReportElement();
        desarrollo.setNombre("STPS - Desarrollo");
        desarrollo.setDescription("");
        desarrollo.setWeight(1d);
        desarrollo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(
                indicator -> desarrollo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0))));
        mongoTemplate.save(desarrollo);
        report.getElements().add(desarrollo);

        ReportElement analitica = new ReportElement();
        analitica.setNombre("STPS - Plataforma Analítica");
        analitica.setDescription("");
        analitica.setWeight(1d);
        analitica.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube") || indicator.getNombre().equals("GitLab")) {
                analitica.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                analitica.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(analitica);
        report.getElements().add(analitica);

        ReportElement ola = new ReportElement();
        ola.setNombre("STPS - OLA");
        ola.setDescription("");
        ola.setWeight(1d);
        ola.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> ola.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(ola);
        report.getElements().add(ola);

        // ext
        ReportElement procesos = new ReportElement();
        procesos.setNombre("INFOTEC - Procesos");
        procesos.setDescription("");
        procesos.setWeight(1d);
        procesos.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                procesos.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                procesos.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(procesos);
        report.getElements().add(procesos);

        ReportElement valuapp = new ReportElement();
        valuapp.setNombre("AGDIGITAL - Valuapp");
        valuapp.setDescription("");
        valuapp.setWeight(1d);
        valuapp.setIndicatorStatus(new ArrayList<>());
        indicators
                .forEach(indicator -> valuapp.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(valuapp);
        report.getElements().add(valuapp);

        // ext
        ReportElement atlas = new ReportElement();
        atlas.setNombre("PROFECO - Atlas");
        atlas.setDescription("");
        atlas.setWeight(1d);
        atlas.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube") || indicator.getNombre().equals("svn")
                    || indicator.getNombre().equals("PDES")) {
                atlas.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                atlas.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(atlas);
        report.getElements().add(atlas);
        mongoTemplate.save(report);

    }

    private void report2(MongoTemplate mongoTemplate) {
        Report report = new Report();
        report.setNombre("Operación de Infraestructura de Desarrollo en la DADS");
        report.setBriefDescription("Reporte Semanal de Operación");
        report.setDescription("Reporte Semanal de Operación");
        report.setReportType(ReportType.OPERATIONAL);
        List<ReportIndicator> indicators = mongoTemplate.findAll(ReportIndicator.class);
        report.setIndicators(indicators);
        mongoTemplate.save(report);
        report.setElements(new ArrayList<>());
        List<State> states = mongoTemplate.findAll(State.class);

        ReportElement repo = new ReportElement();
        repo.setNombre("CONACYT - Repositorios");
        repo.setDescription("CONACYT - Repositorios");
        repo.setWeight(1d);
        repo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> repo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(repo);
        report.getElements().add(repo);

        // ext
        ReportElement admo = new ReportElement();
        admo.setNombre("INFOTEC - Procesos Admon");
        admo.setDescription("CONACYT - Repositorios");
        admo.setWeight(1d);
        admo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")) {
                admo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                admo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(admo);
        report.getElements().add(admo);

        ReportElement inne = new ReportElement();
        inne.setNombre("INNE - FSW");
        inne.setDescription("");
        inne.setWeight(1d);
        inne.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> inne.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(inne);
        report.getElements().add(inne);

        ReportElement innovation = new ReportElement();
        innovation.setNombre("INNOVACIÓN");
        innovation.setDescription("");
        innovation.setWeight(1d);
        innovation.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(
                indicator -> innovation.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(innovation);
        report.getElements().add(innovation);

        ReportElement bien = new ReportElement();
        bien.setNombre("CONACYT - BIEN");
        bien.setDescription("");
        bien.setWeight(1d);
        bien.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> bien.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(bien);
        report.getElements().add(bien);

        // ext
        ReportElement sectur = new ReportElement();
        sectur.setNombre("SECTUR - Conectarse al turismo");
        sectur.setDescription("");
        sectur.setWeight(1d);
        sectur.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                sectur.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                sectur.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(sectur);
        report.getElements().add(sectur);

        ReportElement profeco = new ReportElement();
        profeco.setNombre("PROFECO - Vuela");
        profeco.setDescription("");
        profeco.setWeight(1d);
        profeco.setIndicatorStatus(new ArrayList<>());
        indicators
                .forEach(indicator -> profeco.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(profeco);
        report.getElements().add(profeco);

        ReportElement sfp = new ReportElement();
        sfp.setNombre("SFP - Besop");
        sfp.setDescription("");
        sfp.setWeight(1d);
        sfp.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> sfp.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(sfp);
        report.getElements().add(sfp);

        ReportElement b2b = new ReportElement();
        b2b.setNombre("B2B");
        b2b.setDescription("");
        b2b.setWeight(1d);
        b2b.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                b2b.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                b2b.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(b2b);
        report.getElements().add(b2b);

        ReportElement sekc = new ReportElement();
        sekc.setNombre("DADS - SEKC");
        sekc.setDescription("");
        sekc.setWeight(1d);
        sekc.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> sekc.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(sekc);
        report.getElements().add(sekc);

        ReportElement desarrollo = new ReportElement();
        desarrollo.setNombre("STPS - Desarrollo");
        desarrollo.setDescription("");
        desarrollo.setWeight(1d);
        desarrollo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(
                indicator -> desarrollo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0))));
        mongoTemplate.save(desarrollo);
        report.getElements().add(desarrollo);

        ReportElement analitica = new ReportElement();
        analitica.setNombre("STPS - Plataforma Analítica");
        analitica.setDescription("");
        analitica.setWeight(1d);
        analitica.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube") || indicator.getNombre().equals("GitLab")) {
                analitica.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                analitica.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(analitica);
        report.getElements().add(analitica);

        ReportElement ola = new ReportElement();
        ola.setNombre("STPS - OLA");
        ola.setDescription("");
        ola.setWeight(1d);
        ola.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> ola.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(ola);
        report.getElements().add(ola);

        // ext
        ReportElement procesos = new ReportElement();
        procesos.setNombre("INFOTEC - Procesos");
        procesos.setDescription("");
        procesos.setWeight(1d);
        procesos.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                procesos.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                procesos.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(procesos);
        report.getElements().add(procesos);

        ReportElement valuapp = new ReportElement();
        valuapp.setNombre("AGDIGITAL - Valuapp");
        valuapp.setDescription("");
        valuapp.setWeight(1d);
        valuapp.setIndicatorStatus(new ArrayList<>());
        indicators
                .forEach(indicator -> valuapp.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(valuapp);
        report.getElements().add(valuapp);

        // ext
        ReportElement atlas = new ReportElement();
        atlas.setNombre("PROFECO - Atlas");
        atlas.setDescription("");
        atlas.setWeight(1d);
        atlas.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube") || indicator.getNombre().equals("svn")
                    || indicator.getNombre().equals("PDES")) {
                atlas.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                atlas.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(atlas);
        report.getElements().add(atlas);
        mongoTemplate.save(report);

    }

    private static void report1(MongoTemplate mongoTemplate) {
        Report report = new Report();
        report.setNombre("Operación de Infraestructura de Desarrollo en la DADS");
        report.setBriefDescription("Reporte Semanal de Operación");
        report.setDescription("Reporte Semanal de Operación");
        report.setReportType(ReportType.OPERATIONAL);
        List<ReportIndicator> indicators = mongoTemplate.findAll(ReportIndicator.class);
        report.setIndicators(indicators);
        mongoTemplate.save(report);
        report.setElements(new ArrayList<>());
        List<State> states = mongoTemplate.findAll(State.class);

        ReportElement repo = new ReportElement();
        repo.setNombre("CONACYT - Repositorios");
        repo.setDescription("CONACYT - Repositorios");
        repo.setWeight(1d);
        repo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> repo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(repo);
        report.getElements().add(repo);

        // ext
        ReportElement admo = new ReportElement();
        admo.setNombre("INFOTEC - Procesos Admon");
        admo.setDescription("CONACYT - Repositorios");
        admo.setWeight(1d);
        admo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")) {
                admo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                admo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(admo);
        report.getElements().add(admo);

        ReportElement inne = new ReportElement();
        inne.setNombre("INNE - FSW");
        inne.setDescription("");
        inne.setWeight(1d);
        inne.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> inne.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(inne);
        report.getElements().add(inne);

        ReportElement innovation = new ReportElement();
        innovation.setNombre("INNOVACIÓN");
        innovation.setDescription("");
        innovation.setWeight(1d);
        innovation.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(
                indicator -> innovation.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(innovation);
        report.getElements().add(innovation);

        ReportElement bien = new ReportElement();
        bien.setNombre("CONACYT - BIEN");
        bien.setDescription("");
        bien.setWeight(1d);
        bien.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> bien.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(bien);
        report.getElements().add(bien);

        // ext
        ReportElement sectur = new ReportElement();
        sectur.setNombre("SECTUR - Conectarse al turismo");
        sectur.setDescription("");
        sectur.setWeight(1d);
        sectur.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                sectur.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                sectur.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(sectur);
        report.getElements().add(sectur);

        ReportElement profeco = new ReportElement();
        profeco.setNombre("PROFECO - Vuela");
        profeco.setDescription("");
        profeco.setWeight(1d);
        profeco.setIndicatorStatus(new ArrayList<>());
        indicators
                .forEach(indicator -> profeco.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(profeco);
        report.getElements().add(profeco);

        ReportElement sfp = new ReportElement();
        sfp.setNombre("SFP - Besop");
        sfp.setDescription("");
        sfp.setWeight(1d);
        sfp.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> sfp.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(sfp);
        report.getElements().add(sfp);

        ReportElement b2b = new ReportElement();
        b2b.setNombre("B2B");
        b2b.setDescription("");
        b2b.setWeight(1d);
        b2b.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                b2b.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                b2b.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(b2b);
        report.getElements().add(b2b);

        ReportElement sekc = new ReportElement();
        sekc.setNombre("DADS - SEKC");
        sekc.setDescription("");
        sekc.setWeight(1d);
        sekc.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> sekc.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(sekc);
        report.getElements().add(sekc);

        ReportElement desarrollo = new ReportElement();
        desarrollo.setNombre("STPS - Desarrollo");
        desarrollo.setDescription("");
        desarrollo.setWeight(1d);
        desarrollo.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(
                indicator -> desarrollo.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0))));
        mongoTemplate.save(desarrollo);
        report.getElements().add(desarrollo);

        ReportElement analitica = new ReportElement();
        analitica.setNombre("STPS - Plataforma Analítica");
        analitica.setDescription("");
        analitica.setWeight(1d);
        analitica.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube") || indicator.getNombre().equals("GitLab")) {
                analitica.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                analitica.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(analitica);
        report.getElements().add(analitica);

        ReportElement ola = new ReportElement();
        ola.setNombre("STPS - OLA");
        ola.setDescription("");
        ola.setWeight(1d);
        ola.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> ola.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(ola);
        report.getElements().add(ola);

        // ext
        ReportElement procesos = new ReportElement();
        procesos.setNombre("INFOTEC - Procesos");
        procesos.setDescription("");
        procesos.setWeight(1d);
        procesos.setIndicatorStatus(new ArrayList<>());
        indicators.forEach(indicator -> {
            if (indicator.getNombre().equals("Jenkins") || indicator.getNombre().equals("Artifactory")
                    || indicator.getNombre().equals("Sonarqube")) {
                procesos.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(0)));
            } else {
                procesos.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5)));
            }
        });
        mongoTemplate.save(procesos);
        report.getElements().add(procesos);

        ReportElement valuapp = new ReportElement();
        valuapp.setNombre("AGDIGITAL - Valuapp");
        valuapp.setDescription("");
        valuapp.setWeight(1d);
        valuapp.setIndicatorStatus(new ArrayList<>());
        indicators
                .forEach(indicator -> valuapp.getIndicatorStatus().add(new IndicatorStatus(indicator, states.get(5))));
        mongoTemplate.save(valuapp);
        report.getElements().add(valuapp);
        mongoTemplate.save(report);

    }
}
