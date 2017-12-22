package mx.infotec.dads.kukulkan.util;

import java.util.Date;

/**
 * SuperGrammarPropertyType
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public enum SuperGrammarPropertyType {

    BOOLEAN_TYPE(Boolean.class),

    LITERAL_TYPE(String.class),

    NUMBER_TYPE(Number.class),

    TIME_TYPE(Date.class),

    BINARY_TYPE(byte[].class),

    OTHER_TYPE(Object.class);

    private Class<?> _javaEquivalentClass;

    private SuperGrammarPropertyType(Class<?> javaEquivalentClass) {
        _javaEquivalentClass = javaEquivalentClass;
    }

    /**
     * @return a java class that is appropriate for handling column values of
     *         this column super type
     */
    public Class<?> getJavaEquivalentClass() {
        return _javaEquivalentClass;
    }
}