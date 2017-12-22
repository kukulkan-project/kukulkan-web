package mx.infotec.dads.kukulkan.engine.grammar;

import mx.infotec.dads.kukulkan.engine.domain.core.Constraint;

/**
 * GrammarProperty is a Wrapper.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarProperty {

    private String fieldTypeName;

    private GrammarPropertyType propertyType;
    private Constraint constraint;

    public GrammarProperty() {
    }

    public GrammarProperty(Constraint constraint, String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
        this.constraint = constraint;
    }

    public GrammarPropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(GrammarPropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }

}
