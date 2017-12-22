package mx.infotec.dads.kukulkan.engine.domain.core;

import java.io.Serializable;

/**
 * AbstractElement represent a general element used into the domain model, it
 * represent an abstraction of the common properties used for most elements into
 * the model.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class AbstractElement implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The name of the element.
     */
    private String name;
    /**
     * The description of the element, it could be rich text
     */
    private String description;
    /**
     * The Brief Description of the element, it is not rich text.
     */
    private String briefDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }
}
