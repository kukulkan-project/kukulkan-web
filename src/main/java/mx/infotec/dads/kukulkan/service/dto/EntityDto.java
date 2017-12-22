package mx.infotec.dads.kukulkan.service.dto;

import java.io.Serializable;
import java.util.List;

import mx.infotec.dads.kukulkan.engine.domain.core.GeneratedElement;

/**
 * GeneratedDto
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class EntityDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String entityName;

    private List<GeneratedElement> elements;

    public List<GeneratedElement> getElements() {
        return elements;
    }

    public void setElements(List<GeneratedElement> elements) {
        this.elements = elements;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

}
