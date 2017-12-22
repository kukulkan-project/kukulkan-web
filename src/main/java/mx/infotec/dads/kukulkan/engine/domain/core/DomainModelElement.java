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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * The DataModelElement represent a Table mapped into a specific technology
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DomainModelElement implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * id of the dataModelElement, it is the primary key
     */
    protected PrimaryKey primaryKey;

    /**
     * urlName statements of the elements
     */
    protected String urlName;

    /**
     * imports statements of the elements
     */
    private Collection<String> imports;

    /**
     * Name of the dataModel, usually it is the name of the Schema formatted for
     * specific technology. For instance, in java it is the java Class name
     */
    protected String name;

    /**
     * The name of the database Table
     */
    protected String tableName;

    /**
     * The name of the table formated to java world.
     */
    protected String camelCaseFormat;

    /**
     * The name of the table formated to java world.
     */
    protected String camelCasePluralFormat;

    /**
     * The columns mapped into the PropertyHolder class
     */
    protected String qualifiedName;

    /**
     * The columns mapped into the PropertyHolder class
     */
    @SuppressWarnings("rawtypes")
    private Collection<Property> properties;

    /**
     * The generated elements
     */
    private List<GeneratedElement> generatedElements = new ArrayList<>();

    protected boolean hasNotNullElements;

    protected boolean hasTimeProperties;

    protected boolean hasBlobProperties;

    protected boolean hasClobProperties;

    protected boolean hasConstraints;

    protected boolean hasBigDecimal;

    protected boolean hasInstant;

    protected boolean hasLocalDate;

    protected boolean hasZoneDateTime;

    public boolean isHasConstraints() {
        return hasConstraints;
    }

    public void setHasConstraints(boolean hasConstraints) {
        this.hasConstraints = hasConstraints;
    }

    public boolean isHasBigDecimal() {
        return hasBigDecimal;
    }

    public void setHasBigDecimal(boolean hasBigDecimal) {
        this.hasBigDecimal = hasBigDecimal;
    }

    public boolean isHasInstant() {
        return hasInstant;
    }

    public void setHasInstant(boolean hasInstant) {
        this.hasInstant = hasInstant;
    }

    public boolean isHasLocalDate() {
        return hasLocalDate;
    }

    public void setHasLocalDate(boolean hasLocalDate) {
        this.hasLocalDate = hasLocalDate;
    }

    public boolean isHasZoneDateTime() {
        return hasZoneDateTime;
    }

    public void setHasZoneDateTime(boolean hasZoneDateTime) {
        this.hasZoneDateTime = hasZoneDateTime;
    }

    private DomainModelElement() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("rawtypes")
    public Collection<Property> getProperties() {
        return properties;
    }

    @SuppressWarnings("rawtypes")
    public void setProperties(Collection<Property> properties) {
        this.properties = properties;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCamelCaseFormat() {
        return camelCaseFormat;
    }

    public void setCamelCaseFormat(String camelCaseFormat) {
        this.camelCaseFormat = camelCaseFormat;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public Collection<String> getImports() {
        return imports;
    }

    public void setImports(Collection<String> imports) {
        this.imports = imports;
    }

    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void addProperty(@SuppressWarnings("rawtypes") Property propertyHolder) {
        properties.add(propertyHolder);
    }

    public static DomainModelElement createOrderedDataModel() {
        DomainModelElement dme = new DomainModelElement();
        dme.setImports(new TreeSet<>());
        dme.setProperties(new TreeSet<>());
        return dme;
    }
    
    public static DomainModelElement createDomainModelElement() {
        DomainModelElement dme = new DomainModelElement();
        dme.setImports(new TreeSet<>());
        dme.setProperties(new ArrayList<>());
        return dme;
    }

    public String getQualifiedName() {
        return qualifiedName;
    }

    public void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    public boolean isHasNotNullElements() {
        return hasNotNullElements;
    }

    public void setHasNotNullElements(boolean hasNotNullElements) {
        this.hasNotNullElements = hasNotNullElements;
    }

    public boolean isHasTimeProperties() {
        return hasTimeProperties;
    }

    public void setHasTimeProperties(boolean hasTimeProperties) {
        this.hasTimeProperties = hasTimeProperties;
    }

    public boolean isHasBlobProperties() {
        return hasBlobProperties;
    }

    public void setHasBlobProperties(boolean hasBlobProperties) {
        this.hasBlobProperties = hasBlobProperties;
    }

    public void setHasClobProperties(boolean hasClobProperties) {
        this.hasClobProperties = hasClobProperties;
    }

    public boolean isHasClobProperties() {
        return hasClobProperties;
    }

    public String getCamelCasePluralFormat() {
        return camelCasePluralFormat;
    }

    public void setCamelCasePluralFormat(String camelCasePluralFormat) {
        this.camelCasePluralFormat = camelCasePluralFormat;
    }

    public List<GeneratedElement> getGeneratedElements() {
        return generatedElements;
    }

    public void setGeneratedElements(List<GeneratedElement> generatedElements) {
        this.generatedElements = generatedElements;
    }

    public void addGeneratedElement(GeneratedElement element) {
        this.generatedElements.add(element);
    }
}
