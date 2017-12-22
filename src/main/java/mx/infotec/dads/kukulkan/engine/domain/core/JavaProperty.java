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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.apache.metamodel.schema.ColumnType;

import mx.infotec.dads.kukulkan.engine.grammar.GrammarMapping;
import mx.infotec.dads.kukulkan.engine.grammar.GrammarPropertyType;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.FieldTypeContext;
import mx.infotec.dads.kukulkan.util.DataBaseMapping;

/**
 * 
 * PropertyHolder Class that is used for hold the properties of a table
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class JavaProperty implements Property<JavaProperty> {

    private static final long serialVersionUID = 1L;

    private String name;
    private String type;
    private String qualifiedName;
    private String columnName;
    private String columnType;
    private boolean blob;
    private boolean time;
    private boolean clob;
    private boolean bigDecimal;
    private boolean localDate;
    private boolean instant;
    private boolean zoneDateTime;
    private boolean booleanType;
    private boolean number;
    private boolean literal;
    private boolean largeObject;
    private boolean sizeValidation;
    private boolean hasConstraints;

    private Class<?> javaEquivalentClass;

    private Constraint constraint;

    @Override
    public boolean isBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(boolean bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    @Override
    public boolean isLocalDate() {
        return localDate;
    }

    @Override
    public boolean isInstant() {
        return instant;
    }

    public void setInstant(boolean instant) {
        this.instant = instant;

    }

    public void setLocalDate(boolean localDate) {
        this.localDate = localDate;
    }

    @Override
    public boolean isZoneDateTime() {
        return zoneDateTime;
    }

    public void setZoneDateTime(boolean zoneDateTime) {
        this.zoneDateTime = zoneDateTime;
    }

    private JavaProperty() {

    }

    public static JavaPropertyBuilder builder() {
        return new JavaPropertyBuilder();
    }

    @Override
    public boolean isBlob() {
        return blob;
    }

    public void setBlob(boolean blob) {
        this.blob = blob;
    }

    public boolean isBoolean() {
        return booleanType;
    }

    public void setBoolean(boolean booleanType) {
        this.booleanType = booleanType;
    }

    @Override
    public boolean isTime() {
        return isLocalDate() || isZoneDateTime() || isInstant();
    }

    public void setTime(boolean time) {
        this.time = time;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getQualifiedName() {
        return this.qualifiedName;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Collection<Property> getAssociations() {
        return new ArrayList<>();
    }

    @Override
    public int compareTo(JavaProperty o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String getColumnName() {
        return this.columnName;
    }

    @Override
    public String getColumnType() {
        return this.columnType;
    }

    @Override
    public boolean isClob() {
        return this.clob;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public Constraint getConstraint() {
        return constraint;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JavaProperty other = (JavaProperty) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void setQualifiedName(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    protected void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    protected void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setClob(boolean clob) {
        this.clob = clob;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public static class JavaPropertyBuilder {

        private JavaProperty javaProperty;

        public JavaPropertyBuilder() {
            this.javaProperty = new JavaProperty();
            this.javaProperty.setConstraint(new Constraint());
        }

        public JavaPropertyBuilder withJavaEquivalentClass(Class<?> javaEquivalentClass) {
            this.javaProperty.setJavaEquivalentClass(javaEquivalentClass);
            return this;
        }

        public JavaPropertyBuilder withName(String propertyName) {
            this.javaProperty.setName(propertyName);
            return this;
        }

        public JavaPropertyBuilder withType(String propertyType) {
            this.javaProperty.setType(propertyType);
            return this;
        }

        public JavaPropertyBuilder withType(Optional<GrammarPropertyType> optional) {
            if (optional.isPresent()) {
                this.javaProperty.setType(optional.get().getJavaName());
            }
            return null;
        }

        public JavaPropertyBuilder withQualifiedName(String qualifiedName) {
            this.javaProperty.setQualifiedName(qualifiedName);
            return this;
        }

        public JavaPropertyBuilder withColumnName(String columnName) {
            this.javaProperty.setColumnName(columnName);
            return this;
        }

        public JavaPropertyBuilder withColumnType(String columnType) {
            this.javaProperty.setColumnType(columnType);
            return this;
        }

        public JavaPropertyBuilder isNullable(boolean nullable) {
            this.javaProperty.getConstraint().setNullable(nullable);
            return this;
        }

        public JavaPropertyBuilder isPrimaryKey(boolean isPrimaryKey) {
            this.javaProperty.getConstraint().setPrimaryKey(isPrimaryKey);
            return this;
        }

        public JavaPropertyBuilder isIndexed(boolean indexed) {
            this.javaProperty.getConstraint().setIndexed(indexed);
            return this;
        }

        public JavaPropertyBuilder addType(ColumnType type) {
            DataBaseMapping.addType(javaProperty, type);
            return this;
        }

        public JavaPropertyBuilder addType(FieldTypeContext type) {
            GrammarMapping.addType(javaProperty, type);
            return this;
        }

        public JavaPropertyBuilder isLiteral(boolean literal) {
            javaProperty.setLiteral(literal);
            return this;
        }

        public JavaPropertyBuilder isBigDecimal(boolean bigDecimal) {
            this.javaProperty.setBigDecimal(bigDecimal);
            return this;
        }

        public JavaPropertyBuilder isLocalDate(boolean localDate) {
            this.javaProperty.setLocalDate(localDate);
            return this;
        }

        public JavaPropertyBuilder isZoneDateTime(boolean zoneDateTime) {
            this.javaProperty.setZoneDateTime(zoneDateTime);
            return this;
        }

        public JavaPropertyBuilder isInstance(boolean instant) {
            this.javaProperty.setInstant(instant);
            return this;
        }

        public JavaPropertyBuilder isLargeObject(boolean largeObject) {
            this.javaProperty.setLargeObject(largeObject);
            return this;
        }

        public JavaProperty build() {
            return this.javaProperty;
        }

    }

    @Override
    public boolean isNumber() {
        return number;
    }

    public void setNumber(boolean number) {
        this.number = number;
    }

    public boolean isLiteral() {
        return literal;
    }

    public void setLiteral(boolean literal) {
        this.literal = literal;
    }

    @Override
    public boolean isLargeObject() {
        return largeObject;
    }

    public void setLargeObject(boolean largeObject) {
        this.largeObject = largeObject;
    }

    public void setSizeValidation(boolean sizeValidation) {
        this.sizeValidation = sizeValidation;
    }

    @Override
    public boolean isSizeValidation() {
        return sizeValidation;
    }

    @Override
    public boolean isLong() {
        return getJavaEquivalentClass().equals(Long.class);
    }

    @Override
    public boolean isInteger() {
        return getJavaEquivalentClass().equals(Integer.class);
    }

    @Override
    public boolean isDouble() {
        return getJavaEquivalentClass().equals(Double.class);
    }

    @Override
    public boolean isFloat() {
        return getJavaEquivalentClass().equals(Float.class);
    }

    public Class<?> getJavaEquivalentClass() {
        return javaEquivalentClass;
    }

    public void setJavaEquivalentClass(Class<?> javaEquivalentClass) {
        this.javaEquivalentClass = javaEquivalentClass;
    }

    public void setHasConstraints(boolean hasConstraints) {
        this.hasConstraints = hasConstraints;
    }

    @Override
    public boolean isHasConstraints() {
        return hasConstraints;
    }
}
