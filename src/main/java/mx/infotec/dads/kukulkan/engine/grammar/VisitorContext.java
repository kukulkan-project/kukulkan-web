package mx.infotec.dads.kukulkan.engine.grammar;

import java.util.List;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;

/**
 * Visitor Context
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class VisitorContext {

    private List<DomainModelElement> elements;

    public VisitorContext(List<DomainModelElement> dmeList){
        this.elements= dmeList;
    }
    public List<DomainModelElement> getElements() {
        return elements;
    }

    public void setElements(List<DomainModelElement> elements) {
        this.elements = elements;
    }

}
