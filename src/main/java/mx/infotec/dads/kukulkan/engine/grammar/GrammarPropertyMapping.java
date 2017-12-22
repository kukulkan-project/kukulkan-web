package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.BINARY_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.BOOLEAN_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.LITERAL_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.NUMBER_TYPE;
import static mx.infotec.dads.kukulkan.engine.grammar.SuperColumnType.TIME_TYPE;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import mx.infotec.dads.kukulkan.grammar.kukulkanParser.DateTypesContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.FieldTypeContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.NumericTypesContext;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * GrammarPropertyMapping
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarPropertyMapping {

    private static final HashMap<String, GrammarPropertyType> map;
    static {
        map = new HashMap<>();
        /*
         * Literal
         */
        getMap().put("String", new GrammarPropertyTypeImpl("String", LITERAL_TYPE));
        getMap().put("TextBlob", new GrammarPropertyTypeImpl("TextBlob", LITERAL_TYPE, String.class, true));

        /*
         * Numbers
         */
        getMap().put("Integer", new GrammarPropertyTypeImpl("Integer", NUMBER_TYPE, Integer.class));
        getMap().put("Long", new GrammarPropertyTypeImpl("Long", NUMBER_TYPE, Long.class));
        getMap().put("BigDecimal", new GrammarPropertyTypeImpl("BigDecimal", NUMBER_TYPE, BigDecimal.class));
        getMap().put("Float", new GrammarPropertyTypeImpl("Float", NUMBER_TYPE, Float.class));
        getMap().put("Double", new GrammarPropertyTypeImpl("Double", NUMBER_TYPE, Double.class));

        /*
         * Time based
         */
        getMap().put("Date", new GrammarPropertyTypeImpl("Date", TIME_TYPE, Date.class));
        getMap().put("LocalDate", new GrammarPropertyTypeImpl("LocalDate", TIME_TYPE, LocalDate.class));
        getMap().put("ZonedDateTime", new GrammarPropertyTypeImpl("ZonedDateTime", TIME_TYPE, ZonedDateTime.class));
        getMap().put("Instant", new GrammarPropertyTypeImpl("Instant", TIME_TYPE, Instant.class));

        /*
         * Booleans
         */
        getMap().put("Boolean", new GrammarPropertyTypeImpl("Boolean", BOOLEAN_TYPE, boolean.class));

        /*
         * Blobs
         */
        getMap().put("Blob", new GrammarPropertyTypeImpl("Blob", BINARY_TYPE, byte[].class, true));
        getMap().put("AnyBlob", new GrammarPropertyTypeImpl("AnyBlob", BINARY_TYPE, byte[].class, true));
        getMap().put("ImageBlob", new GrammarPropertyTypeImpl("ImageBlob", BINARY_TYPE, byte[].class, true));

    }

    private GrammarPropertyMapping() {
    }

    public static GrammarPropertyType getPropertyType(String property) {
        return getMap().get(property);
    }

    public static GrammarPropertyType getPropertyType(FieldTypeContext type) {
        Optional<GrammarPropertyType> optional = Optional.of(getMap().get(extractPropertyType(type).getFieldTypeName()));
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ApplicationException("Property Not Found" + type.getText());
        }
    }

    public static GrammarProperty extractPropertyType(FieldTypeContext type) {
        if (type.stringFieldType() != null) {
            return new GrammarProperty(null, type.stringFieldType().name.getText());
        } else if (type.numericFieldType() != null) {
            return new GrammarProperty(null, getNumericType(type.numericFieldType().numericTypes()));
        } else if (type.booleanFieldType() != null) {
            return new GrammarProperty(null, type.booleanFieldType().name.getText());
        } else if (type.dateFieldType() != null) {
            return new GrammarProperty(null, getDateType(type.dateFieldType().dateTypes()));
        } else if (type.blobFieldType() != null) {
            return new GrammarProperty(null, type.blobFieldType().name.getText());
        } else {
            throw new ApplicationException("Property type not found for: " + type.getText());
        }
    }


    public static String getNumericType(NumericTypesContext numericTypes) {
        if (numericTypes.LONG() != null) {
            return numericTypes.LONG().getText();
        } else if (numericTypes.FLOAT() != null) {
            return numericTypes.FLOAT().getText();
        } else if (numericTypes.INTEGER() != null) {
            return numericTypes.INTEGER().getText();
        } else if (numericTypes.BIG_DECIMAL() != null) {
            return numericTypes.BIG_DECIMAL().getText();
        } else if (numericTypes.DOUBLE() != null) {
            return numericTypes.DOUBLE().getText();
        } else {
            throw new ApplicationException("Numeric Type Not Found for: " + numericTypes.getText());
        }
    }

    public static String getDateType(DateTypesContext dataTypes) {
        if (dataTypes.DATE() != null) {
            return dataTypes.DATE().getText();
        } else if (dataTypes.INSTANT() != null) {
            return dataTypes.INSTANT().getText();
        } else if (dataTypes.LOCAL_DATE() != null) {
            return dataTypes.LOCAL_DATE().getText();
        } else if (dataTypes.ZONED_DATETIME() != null) {
            return dataTypes.ZONED_DATETIME().getText();
        } else {
            throw new ApplicationException("Date Type Not Found for: " + dataTypes.getText());
        }
    }

    public static HashMap<String, GrammarPropertyType> getMap() {
        return map;
    }
}
