package mx.infotec.dads.kukulkan.config.dbmigrations;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.reports.domain.ReportIndicator;

/**
 * EntityFactory
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class EntityFactory {

    private EntityFactory() {

    }

    public static List<ReportIndicator> createIndicators() {
        List<ReportIndicator> indicatorList = new ArrayList<>();
        ReportIndicator svn = new ReportIndicator();
        svn.setNombre("svn");
        svn.setBriefDescription("svn");
        svn.setDescription("svn");
        svn.order(1);
        indicatorList.add(svn);
        ReportIndicator gitlab = new ReportIndicator();
        gitlab.setNombre("GitLab");
        gitlab.setBriefDescription("gitlab");
        gitlab.setDescription("gitlab");
        gitlab.order(2);
        indicatorList.add(gitlab);
        ReportIndicator sonarqube = new ReportIndicator();
        sonarqube.setNombre("Sonarqube");
        sonarqube.setBriefDescription("Sonarqube");
        sonarqube.setDescription("Sonarqube");
        sonarqube.order(3);
        indicatorList.add(sonarqube);
        ReportIndicator pdes = new ReportIndicator();
        pdes.setNombre("PDES");
        pdes.setBriefDescription("Process Dashboard EnterPrices");
        pdes.setDescription("Process Dashboard EnterPrices");
        pdes.order(4);
        indicatorList.add(pdes);
        ReportIndicator jenkins = new ReportIndicator();
        jenkins.setNombre("Jenkins");
        jenkins.setBriefDescription("Jenkins");
        jenkins.setDescription("Jenkins");
        jenkins.order(5);
        indicatorList.add(jenkins);
        ReportIndicator artifactory = new ReportIndicator();
        artifactory.setNombre("Artifactory");
        artifactory.setBriefDescription("Artifactory");
        artifactory.setDescription("Artifactory");
        artifactory.order(6);
        indicatorList.add(artifactory);
        return indicatorList;
    }
}
