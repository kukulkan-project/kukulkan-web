/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.engine.domain.core;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * PropertyHolder Class that is used for hold the properties of a table and
 * properties representations in the Object domain.
 * 
 * @author Daniel Cortes Pichardo
 * @param <T>
 *
 */
public interface Property<T> extends Comparable<T>, Serializable {

    /**
     * Return the name of the property
     * 
     * @return propertyName
     */
    String getName();

    /**
     * Return the type of the property
     * 
     * @return propertyType
     */
    String getType();

    /**
     * Return the name of the property
     * 
     * @return propertyName
     */
    String getColumnName();

    /**
     * Return the columnType of the table
     * 
     * @return columnType
     */
    String getColumnType();

    /**
     * Return the qualified name of the type of the Property, in order to create
     * a import statement in the generation fase;
     * 
     * @return qualifiedName
     */
    String getQualifiedName();

    /**
     * Return true if it blob o clob element
     * 
     * @return boolean
     */
    boolean isBlob();

    /**
     * Return true if it is Time element
     * 
     * @return boolean
     */
    boolean isTime();

    /**
     * Return true if it is Clob element
     * 
     * @return boolean
     */
    boolean isClob();

    /**
     * Return true if it is Clob element
     * 
     * @return boolean
     */
    boolean isBigDecimal();

    /**
     * Return true if it is Long element
     * 
     * @return boolean
     */
    boolean isLong();

    /**
     * Return true if it is Integer element
     * 
     * @return boolean
     */
    boolean isInteger();

    /**
     * Return true if it is Double element
     * 
     * @return boolean
     */
    boolean isDouble();

    /**
     * Return true if it is Float element
     * 
     * @return boolean
     */
    boolean isFloat();

    /**
     * Return true if it is Clob element
     * 
     * @return boolean
     */
    boolean isNumber();

    /**
     * Return true if it is Clob element
     * 
     * @return boolean
     */
    boolean isLocalDate();

    /**
     * Return true if it is Clob element
     * 
     * @return boolean
     */
    boolean isInstant();

    /**
     * Return true if it is zoneDataTime element
     * 
     * @return boolean
     */
    boolean isZoneDateTime();

    /**
     * Return true if it is literal element
     * 
     * @return boolean
     */
    boolean isLiteral();
    
    /**
     * Return true if has constraints
     * 
     * @return boolean
     */
    boolean isHasConstraints();

    /**
     * Return associations if it exists
     * 
     * @return associations
     */
    @SuppressWarnings("rawtypes")
    Collection<Property> getAssociations();

    /**
     * Return the constraint applied
     */
    Constraint getConstraint();

    /**
     * Return true if Large Object is present
     * 
     * @return true if Large Object
     */
    boolean isLargeObject();

    boolean isSizeValidation();
}
