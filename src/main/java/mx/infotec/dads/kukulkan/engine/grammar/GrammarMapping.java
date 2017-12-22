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
package mx.infotec.dads.kukulkan.engine.grammar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.BlobTypesContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.DateTypesContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.DomainModelContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.FieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.NumericTypesContext;

/**
 * DataMapping utility class
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarMapping {

    private GrammarMapping() {

    }

    /**
     * Create a DataModelGroup Class
     * 
     * @param dataContext
     * @return DataModelGroup
     */
    public static DomainModelGroup createDefaultDataModelGroup(DomainModelContext dmc, KukulkanVisitor visitor) {
        DomainModelGroup dmg = new DomainModelGroup();
        dmg.setName("");
        dmg.setDescription("Default package");
        dmg.setBriefDescription("Default package");
        dmg.setDomainModelElements(new ArrayList<>());
        List<DomainModelElement> dmeList = new ArrayList<>();
        createDataModelElement(dmc, visitor, dmeList);
        dmg.setDomainModelElements(dmeList);
        return dmg;
    }

    /**
     * createDataModelElement is used for map the KukulkanGrammar to
     * DataModelElement.
     * 
     * @param dmc
     * @param visitor
     * @param dmeList
     */
    private static void createDataModelElement(DomainModelContext dmc, KukulkanVisitor visitor,
            List<DomainModelElement> dmeList) {
        visitor.visit(dmc);
        dmeList.addAll(visitor.getVctx().getElements());
    }

    public static boolean addImports(Collection<String> imports, JavaProperty property) {
        if (property.isBlob() || property.isLiteral() || property.isBoolean() || property.isClob()
                || (property.isNumber() && !property.isBigDecimal())) {
            return false;
        } else {
            imports.add(property.getQualifiedName());
            return true;
        }
    }

    public static void addType(JavaProperty javaProperty, FieldTypeContext type) {
        if (type.booleanFieldType() != null) {
            javaProperty.setBoolean(true);
        } else if (type.dateFieldType() != null) {
            setKindOfDateType(javaProperty, type.dateFieldType().type);
        } else if (type.blobFieldType() != null) {
            setKindOfBlobType(javaProperty, type.blobFieldType().blobTypes());
            javaProperty.setBlob(true);
        } else if (type.numericFieldType() != null) {
            javaProperty.setNumber(true);
            setKindOfNumeric(javaProperty, type.numericFieldType().numericTypes());
        } else if (type.stringFieldType() != null) {
            javaProperty.setLiteral(true);
        }
    }

    private static void setKindOfNumeric(JavaProperty javaProperty, NumericTypesContext type) {
        if (type.BIG_DECIMAL() != null) {
            javaProperty.setBigDecimal(true);
        }
    }

    private static void setKindOfBlobType(JavaProperty property, BlobTypesContext ctx) {
        if (ctx.BLOB() != null || ctx.ANY_BLOB() != null || ctx.IMAGE_BLOB() != null) {
            property.setBlob(true);
        } else if (ctx.TEXT_BLOB() != null) {
            property.setClob(true);
        }
    }

    private static void setKindOfDateType(JavaProperty property, DateTypesContext type) {
        property.setTime(true);
        if (type.ZONED_DATETIME() != null) {
            property.setZoneDateTime(true);
        } else if (type.DATE() != null || type.LOCAL_DATE() != null) {
            property.setLocalDate(true);
        } else if (type.INSTANT() != null) {
            property.setInstant(true);
        }
    }

    /**
     * createSingleDataModelGroupList
     * 
     * @param visitor
     * @param tablesToProcess
     * @return
     * @throws IOException
     */
    public static List<DomainModelGroup> createSingleDataModelGroupList(KukulkanVisitor visitor) {
        String program = "src/test/resources/grammar/test." + "3k";
        DomainModelContext tree = GrammarUtil.getDomainModelContext(program);
        List<DomainModelGroup> dataModelGroupList = new ArrayList<>();
        dataModelGroupList.add(createDefaultDataModelGroup(tree, visitor));
        return dataModelGroupList;
    }

    /**
     * createSingleDataModelGroupList
     * 
     * @param visitor
     * @param tablesToProcess
     * @return
     * @throws IOException
     */
    public static List<DomainModelGroup> createSingleDataModelGroupList(KukulkanVisitor visitor, String textFile) {
        DomainModelContext tree = GrammarUtil.getDomainModelContext(textFile, true);
        List<DomainModelGroup> dataModelGroupList = new ArrayList<>();
        dataModelGroupList.add(createDefaultDataModelGroup(tree, visitor));
        return dataModelGroupList;
    }

    public static Optional<GrammarPropertyType> getPropertyType(String type) {
        return Optional.of(GrammarPropertyMapping.getPropertyType(type));
    }
}
