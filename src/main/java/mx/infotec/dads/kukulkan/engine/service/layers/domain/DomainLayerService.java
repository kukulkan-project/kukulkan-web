package mx.infotec.dads.kukulkan.engine.service.layers.domain;

import java.util.Collection;
import java.util.Map;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;

public interface DomainLayerService {

    void visitDomainModelElement(ProjectConfiguration confg, Collection<DomainModelElement> dmElementCollection,
            Map<String, Object> propertiesMap, String dmgName, DomainModelElement dmElement, String basePackage);

}
