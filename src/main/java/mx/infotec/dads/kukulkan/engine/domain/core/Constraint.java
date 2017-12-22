package mx.infotec.dads.kukulkan.engine.domain.core;

import java.io.Serializable;

/**
 * Constraint Class, This class represent a Constraint applied to a column in a
 * table
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Constraint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Is column nullable
     */
    private boolean nullable = true;

    /**
     * Is the property primaryKey
     */
    private boolean primaryKey;

    /**
     * the name of the primaryKey
     */
    private String primaryKeyName;

    /**
     * Is unique
     */
    private boolean unique;

    /**
     * The uniqueConstraint
     */
    private boolean uniqueConstraintName;

    /**
     * referenced in other tables
     */
    private String references;

    /**
     * the foreign key name
     */
    private String foreignKeyName;

    /**
     * is cascade delete
     */
    private boolean deleteCascade;

    /**
     * Are constraints deferrable
     */
    private boolean deferrable;

    /**
     * Are constraints initially deferred *
     */
    private boolean initiallyDeferred;

    /**
     * is indexed
     */
    private boolean indexed;

    /**
     * Min value of the property
     */
    private String minLength;

    /**
     * Max value of the property
     */
    private String maxLength;

    /**
     * Min value of the property
     */
    private String minNumber;

    /**
     * Max value of the property
     */
    private String maxNumber;
    
    /**
     * Min value of the property
     */
    private String minByte;

    /**
     * Max value of the property
     */
    private String maxByte;

    /**
     * Pattern
     */
    private String pattern;

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isUniqueConstraintName() {
        return uniqueConstraintName;
    }

    public void setUniqueConstraintName(boolean uniqueConstraintName) {
        this.uniqueConstraintName = uniqueConstraintName;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public void setForeignKeyName(String foreignKeyName) {
        this.foreignKeyName = foreignKeyName;
    }

    public boolean isDeleteCascade() {
        return deleteCascade;
    }

    public void setDeleteCascade(boolean deleteCascade) {
        this.deleteCascade = deleteCascade;
    }

    public boolean isDeferrable() {
        return deferrable;
    }

    public void setDeferrable(boolean deferrable) {
        this.deferrable = deferrable;
    }

    public boolean isInitiallyDeferred() {
        return initiallyDeferred;
    }

    public void setInitiallyDeferred(boolean initiallyDeferred) {
        this.initiallyDeferred = initiallyDeferred;
    }

    public boolean isIndexed() {
        return indexed;
    }

    public void setIndexed(boolean indexed) {
        this.indexed = indexed;
    }
    
    public String getMinLength() {
        return minLength;
    }

    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public String getMinByte() {
        return minByte;
    }

    public void setMinByte(String minByte) {
        this.minByte = minByte;
    }

    public String getMaxByte() {
        return maxByte;
    }

    public void setMaxByte(String maxByte) {
        this.maxByte = maxByte;
    }
    
    public String getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(String minNumber) {
        this.minNumber = minNumber;
    }

    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
