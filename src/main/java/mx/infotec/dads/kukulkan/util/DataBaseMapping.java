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
package mx.infotec.dads.kukulkan.util;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.metamodel.schema.Column;
import org.apache.metamodel.schema.ColumnType;
import org.apache.metamodel.schema.Table;

import mx.infotec.dads.kukulkan.domain.enumeration.ArchetypeType;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.engine.domain.core.PrimaryKey;
import mx.infotec.dads.kukulkan.engine.service.layers.LayerTask;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * DataMapping utility class
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class DataBaseMapping {

    private DataBaseMapping() {

    }

    /**
     * Create a DataModelGroup Class
     * 
     * @param dataContext
     * @return DataModelGroup
     */
    public static DomainModelGroup createDefaultDataModelGroup(List<Table> tables, List<String> excludedTables) {
        DomainModelGroup dmg = new DomainModelGroup();
        dmg.setName("");
        dmg.setDescription("Default package");
        dmg.setBriefDescription("Default package");
        dmg.setDomainModelElements(new ArrayList<>());
        List<DomainModelElement> dmeList = new ArrayList<>();
        createDataModelElement(excludedTables, tables, dmeList);
        dmg.setDomainModelElements(dmeList);
        return dmg;
    }

    private static void createDataModelElement(List<String> tablesToProcess, List<Table> tables,
            List<DomainModelElement> dmeList) {
        tables.forEach(table -> {
            if ((tablesToProcess.contains(table.getName()) || tablesToProcess.isEmpty())
                    && hasPrimaryKey(table.getPrimaryKeys())) {
                DomainModelElement dme = DomainModelElement.createOrderedDataModel();
                String singularName = InflectorProcessor.getInstance().singularize(table.getName());
                dme.setTableName(table.getName());
                dme.setName(SchemaPropertiesParser.parseToClassName(singularName));
                dme.setCamelCaseFormat(SchemaPropertiesParser.parseToPropertyName(singularName));
                dme.setCamelCasePluralFormat(InflectorProcessor.getInstance().pluralize(dme.getCamelCaseFormat()));
                extractPrimaryKey(dme, singularName, table.getPrimaryKeys());
                extractProperties(dme, table);
                dmeList.add(dme);
            }
        });
    }

    public static void extractPrimaryKey(DomainModelElement dme, String singularName, List<Column> columns) {
        dme.setPrimaryKey(mapPrimaryKeyElements(singularName, columns));
        if (!dme.getPrimaryKey().isComposed()) {
            dme.getImports().add(dme.getPrimaryKey().getQualifiedLabel());
        }
    }

    public static void extractProperties(DomainModelElement dme, Table table) {
        table.getColumns().stream().filter(column -> !column.isPrimaryKey())
                .forEach(column -> processNotPrimaryProperties(dme, column));
    }

    private static void processNotPrimaryProperties(DomainModelElement dme, Column column) {
        String propertyName = SchemaPropertiesParser.parseToPropertyName(column.getName());
        String propertyType = extractPropertyType(column);
        JavaProperty javaProperty = JavaProperty.builder()
                .withName(propertyName)
                .withType(propertyType)
                .withColumnName(column.getName())
                .withColumnType(column.getNativeType())
                .withQualifiedName(extractQualifiedType(column))
                .isNullable(column.isNullable())
                .isPrimaryKey(false)
                .isIndexed(column.isIndexed()).addType(column.getType()).build();
        dme.addProperty(javaProperty);
        addImports(dme.getImports(), column.getType());
        fillModelMetaData(dme, javaProperty);
    }

    public static void fillModelMetaData(DomainModelElement dme, JavaProperty javaProperty) {
        if (!javaProperty.getConstraint().isNullable()) {
            dme.setHasNotNullElements(true);
            dme.setHasConstraints(true);
        }
        if (javaProperty.isTime()) {
            checkIfTime(dme, javaProperty);
            return;
        } else if (javaProperty.isBlob()) {
            checkIfBlob(dme, javaProperty);
            return;
        } else if (javaProperty.isClob()) {
            dme.setHasClobProperties(true);
            javaProperty.setClob(true);
            return;
        }
    }

    public static void checkIfBlob(DomainModelElement dme, JavaProperty javaProperty) {
        dme.setHasBlobProperties(true);
        javaProperty.setBlob(true);
        return;
    }

    public static void checkIfTime(DomainModelElement dme, JavaProperty javaProperty) {
        dme.setHasTimeProperties(true);
        if (javaProperty.isZoneDateTime()) {
            dme.setHasZoneDateTime(true);
        } else if (javaProperty.isLocalDate()) {
            dme.setHasLocalDate(true);
        } else if (javaProperty.isInstant()) {
            dme.setHasInstant(true);
        } else {
            throw new ApplicationException("Not java Time Equivalent: " + javaProperty.getColumnName());
        }
        return;
    }

    private static boolean addImports(Collection<String> imports, ColumnType columnType) {
        String javaType = columnType.getJavaEquivalentClass().getCanonicalName();
        if (columnType == ColumnType.BLOB || columnType == ColumnType.CLOB || columnType == ColumnType.NCLOB
                || isWrapperClass(columnType) || columnType == ColumnType.DATE || columnType == ColumnType.TIMESTAMP) {
            return false;
        }
        imports.add(javaType);
        return true;
    }

    private static boolean isWrapperClass(ColumnType columnType) {
        Class<?> testClass = columnType.getJavaEquivalentClass();
        if (testClass.equals(Boolean.class) || testClass.equals(Double.class) || testClass.equals(Integer.class)
                || testClass.equals(Long.class) || testClass.equals(Float.class) || testClass.equals(String.class)) {
            return true;
        } else {
            return false;
        }
    }

    private static String extractPropertyType(Column column) {
        String propertyType = column.getType().getJavaEquivalentClass().getSimpleName();
        if (column.isIndexed() && column.getType().isNumber()) {
            return "Long";
        } else if (column.getType() == ColumnType.BLOB) {
            return "byte[]";
        } else if (column.getType() == ColumnType.CLOB) {
            return "String";
        } else if (column.getType() == ColumnType.DATE) {
            return "LocalDate";
        } else if (column.getType() == ColumnType.TIMESTAMP) {
            return "ZonedDateTime";
        } else {
            return propertyType;
        }
    }

    private static String extractQualifiedType(Column column) {
        if (column.isIndexed() && column.getType().isNumber()) {
            return "java.lang.Long";
        } else {
            return column.getType().getJavaEquivalentClass().getCanonicalName();
        }
    }

    public static boolean hasPrimaryKey(List<Column> columns) {
        return columns.size() == 0 ? false : true;
    }

    public static PrimaryKey mapPrimaryKeyElements(String singularName, List<Column> columns) {
        PrimaryKey pk = PrimaryKey.createOrderedDataModel();
        // Not found primary key
        if (columns.size() == 0) {
            return null;
        }
        // Simple Primary key
        if (columns.size() == 1) {
            pk.setType("Long");
            pk.setName(SchemaPropertiesParser.parseToPropertyName(columns.get(0).getName()));
            pk.setQualifiedLabel("java.lang.Long");
            pk.setComposed(false);
        } else {
            // Composed Primary key
            pk.setType(SchemaPropertiesParser.parseToClassName(singularName) + "PK");
            pk.setName(SchemaPropertiesParser.parseToPropertyName(singularName) + "PK");
            pk.setComposed(true);
            for (Column columnElement : columns) {
                String propertyName = SchemaPropertiesParser.parseToPropertyName(columnElement.getName());
                String propertyType = columnElement.getType().getJavaEquivalentClass().getSimpleName();
                String qualifiedLabel = columnElement.getType().getJavaEquivalentClass().toString();
                pk.addProperty(JavaProperty.builder().withName(propertyName).withType(propertyType)
                        .withColumnName(columnElement.getName()).withColumnType(columnElement.getNativeType())
                        .withQualifiedName(qualifiedLabel).isNullable(columnElement.isNullable()).isPrimaryKey(true)
                        .isIndexed(columnElement.isIndexed()).build());
            }
        }
        return pk;
    }

    public static void addType(JavaProperty javaProperty, ColumnType type) {
        if (type.isBoolean()) {
            javaProperty.setBoolean(true);
        } else if (type.isTimeBased()) {
            setKindOfDateType(javaProperty, type);
        } else if (type.isBinary()) {
            javaProperty.setBlob(true);
        } else if (type.isNumber()) {
            javaProperty.setNumber(true);
        } else if (type.isLiteral()) {
            setKindOfLiteral(javaProperty, type);
            javaProperty.setLiteral(true);
        }
    }

    public static void setKindOfDateType(JavaProperty property, ColumnType type) {
        property.setTime(true);
        if (type == ColumnType.TIMESTAMP) {
            property.setZoneDateTime(true);
        } else if (type == ColumnType.DATE) {
            property.setLocalDate(true);
        }
    }

    public static void setKindOfLiteral(JavaProperty property, ColumnType type) {
        property.setLiteral(true);
        if (type == ColumnType.CLOB || type == ColumnType.NCLOB) {
            property.setClob(true);
        }
    }

    /**
     * Create a List of DataModelGroup into a single group from a DataContext
     * Element
     * 
     * @param dataContext
     * @return
     */
    public static List<DomainModelGroup> createSingleDataModelGroupList(List<Table> tables,
            List<String> excludedTables) {
        List<DomainModelGroup> dataModelGroupList = new ArrayList<>();
        dataModelGroupList.add(createDefaultDataModelGroup(tables, excludedTables));
        return dataModelGroupList;
    }

    public static List<LayerTask> createLaterTaskList(Map<String, LayerTask> map, ArchetypeType archetype) {
        List<LayerTask> layerTaskList = new ArrayList<>();
        map.entrySet().forEach(layerTaskEntry -> {
            if (layerTaskEntry.getValue().getArchetype().equals(archetype)) {
                layerTaskList.add(layerTaskEntry.getValue());
            }
        });
        return layerTaskList;
    }
}
