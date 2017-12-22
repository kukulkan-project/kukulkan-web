package mx.infotec.dads.kukulkan.engine.grammar;

import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDefaultPrimaryKey;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.JavaProperty;
import mx.infotec.dads.kukulkan.grammar.kukulkanLexer;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityContext;
import mx.infotec.dads.kukulkan.grammar.kukulkanParser.EntityFieldContext;
import mx.infotec.dads.kukulkan.util.InflectorProcessor;
import mx.infotec.dads.kukulkan.util.SchemaPropertiesParser;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * Grammar Util, It is used to performe common operation in the grammar.
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GrammarUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrammarUtil.class);

    private GrammarUtil() {

    }

    public static kukulkanParser.DomainModelContext getDomainModelContext(String file) {
        try {
            LOGGER.debug("Interpreting file {}", file);
            kukulkanLexer lexer;
            lexer = new kukulkanLexer(new ANTLRFileStream(file));
            return getDomainModelContext(lexer);
        } catch (IOException e) {
            throw new ApplicationException("getDomainModelContext Error: ", e);
        }
    }

    public static kukulkanParser.DomainModelContext getDomainModelContext(String file, boolean isText) {
        if (isText) {
            try {
                LOGGER.debug("Interpreting file {}", file);
                kukulkanLexer lexer;
                lexer = new kukulkanLexer(new ANTLRInputStream(file));
                return getDomainModelContext(lexer);
            } catch (Exception e) {
                throw new ApplicationException("getDomainModelContext Error: ", e);
            }
        } else {
            return getDomainModelContext(file);
        }
    }

    public static kukulkanParser.DomainModelContext getDomainModelContext(kukulkanLexer lexer) {
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        kukulkanParser parser = new kukulkanParser(tokens);
        return parser.domainModel();
    }

    public static kukulkanLexer getKukulkanLexer(String file) throws IOException {
        return new kukulkanLexer(new ANTLRFileStream(file));
    }

    public static kukulkanLexer getKukulkanLexer(InputStream is) throws IOException {
        return new kukulkanLexer(new ANTLRInputStream(is));
    }

    public static void addMetaData(EntityContext entity, DomainModelElement dme) {
        String singularName = InflectorProcessor.getInstance().singularize(entity.name.getText());
        dme.setTableName(entity.name.getText().toUpperCase());
        dme.setName(entity.name.getText());
        dme.setCamelCaseFormat(SchemaPropertiesParser.parseToPropertyName(singularName));
        dme.setCamelCasePluralFormat(InflectorProcessor.getInstance().pluralize(dme.getCamelCaseFormat()));
        dme.setPrimaryKey(createDefaultPrimaryKey());
    }

    public static void addContentType(DomainModelElement dme, String propertyName, GrammarPropertyType propertyType) {
        if (propertyType.isBinary()) {
            dme.addProperty(createContentTypeProperty(propertyName));
        }
    }

    public static JavaProperty createJavaProperty(EntityFieldContext field, String propertyName,
            GrammarPropertyType propertyType) {
        return JavaProperty.builder()
                .withName(propertyName)
                .withType(propertyType.getJavaName())
                .withColumnName(propertyName)
                .withColumnType(propertyType.getGrammarName())
                .withQualifiedName(propertyType.getJavaQualifiedName())
                .isNullable(true)
                .isPrimaryKey(false)
                .isIndexed(false)
                .isLocalDate(propertyType.getJavaEquivalentClass().equals(LocalDate.class))
                .isZoneDateTime(propertyType.getJavaEquivalentClass().equals(ZonedDateTime.class))
                .isInstance(propertyType.getJavaEquivalentClass().equals(Instant.class))
                .isLargeObject(propertyType.isLargeObject())
                .addType(field.type)
                .withJavaEquivalentClass(propertyType.getJavaEquivalentClass()).build();
    }

    public static JavaProperty createContentTypeProperty(String propertyName) {
        return JavaProperty.builder().withName(propertyName + "ContentType").withType("String")
                .withColumnName(propertyName + "ContentType").withColumnType("TextBlob")
                .withQualifiedName("java.lang.String").isNullable(true).isPrimaryKey(false).isIndexed(false)
                .isLargeObject(false).isLiteral(true).withJavaEquivalentClass(String.class).build();
    }
}
