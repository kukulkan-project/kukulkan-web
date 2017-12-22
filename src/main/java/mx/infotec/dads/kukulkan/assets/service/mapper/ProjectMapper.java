package mx.infotec.dads.kukulkan.assets.service.mapper;

import mx.infotec.dads.kukulkan.domain.Project;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.util.PKGenerationStrategy;

/**
 * Mapper for the entity Asset and its DTO AssetDTO.
 */
public class ProjectMapper {

    private ProjectMapper(){
        
    }
    
    public static ProjectConfiguration toProjectConfiguration(Project project) {
        ProjectConfiguration pConf = new ProjectConfiguration();
        pConf.setId(project.getProjectId());
        pConf.setGroupId(project.getProjectId());
        pConf.setVersion(project.getVersion());
        pConf.setPackaging(project.getPackaging());
        pConf.setYear(project.getYear());
        pConf.setAuthor(project.getAuthor());
        pConf.setWebLayerName(project.getWebLayerName());
        pConf.setServiceLayerName(project.getServiceLayerName());
        pConf.setDaoLayerName(project.getDaoLayerName());
        pConf.setDomainLayerName(project.getDomainLayerName());
        pConf.setGlobalGenerationType(PKGenerationStrategy.SEQUENCE);
        return pConf;
    }
    
}
