package mx.infotec.dads.kukulkan.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.engine.domain.core.GeneratedElement;

/**
 * GeneratedDto
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GeneratedDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<EntityDto> entities = new ArrayList<>();
    
    private List<GeneratedElement> mainElements;
    
    private String url;
    
    private String fileName;

    public List<GeneratedElement> getMainElements() {
        return mainElements;
    }

    public void setMainElements(List<GeneratedElement> mainElements) {
        this.mainElements = mainElements;
    }

    public List<EntityDto> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityDto> entities) {
        this.entities = entities;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
