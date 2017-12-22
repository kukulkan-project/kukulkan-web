package mx.infotec.dads.kukulkan.engine.grammar;

import java.util.Date;

/**
 * Represents an abstract, generalized type of column
 */
public enum SuperColumnType {

    BOOLEAN_TYPE(Boolean.class),

    LITERAL_TYPE(String.class),

    NUMBER_TYPE(Number.class),

    TIME_TYPE(Date.class),

    BINARY_TYPE(byte[].class),

    OTHER_TYPE(Object.class);

    private Class<?> javaEquivalentClass;

    private SuperColumnType(Class<?> javaEquivalentClass) {
        this.javaEquivalentClass = javaEquivalentClass;
    }

    /**
     * @return a java class that is appropriate for handling column values of
     *         this column super type
     */
    public Class<?> getJavaEquivalentClass() {
        return javaEquivalentClass;
    }
}