package mx.infotec.dads.kukulkan.engine.service.layers.frontend;

import java.util.Collection;
import java.util.Map;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;

public interface FrontEndLayerService {

    void doBeforeProcessDataModelGroup(GeneratorContext context, Map<String, Object> model);

    void visitDomainModelElement(ProjectConfiguration pConf, Collection<DomainModelElement> dmElementCollection,
            Map<String, Object> propertiesMap, String dmgName, DomainModelElement dmElement, String basePackage);

}
