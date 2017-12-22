package mx.infotec.dads.kukulkan.engine.service.layers;

import static mx.infotec.dads.kukulkan.util.JavaFileNameParser.formatToImportStatement;
import static mx.infotec.dads.kukulkan.util.NameConventionFormatter.camelCaseToHyphens;

import java.util.HashMap;
import java.util.Map;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.util.NameConventions;

/**
 * LayerUtils
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class LayerUtils {

    public static final String URL_NAME_PROPERTY = "urlName";
    public static final String IMPORT_REPOSITORY_PROPERTY = "importRepository";
    public static final String IMPORT_SERVICE_PROPERTY = "importService";
    /**
     * Authoring Properties
     */
    public static final String YEAR_PROPERTY = "year";
    public static final String AUTHOR_PROPERTY = "author";
    public static final String IS_MONGO_PROPERTY = "isMongoDB";
    public static final String PROJECT_NAME_PROPERTY = "projectName";
    public static final String DATA_MODEL_GROUP_PROPERTY = "dataModelGroup";
    /**
     * Common Properties per Element
     */
    public static final String HAS_BLOB_PROPERTIES_PROPERTY = "hasBlobProperties";
    public static final String HAS_LOCAL_DATE_PROPERTY = "hasLocalDate";
    public static final String HAS_TIME_PROPERTIES_PROPERTY = "hasTimeProperties";
    public static final String HAS_BIG_DECIMAL_PROPERTY = "hasBigDecimal";
    public static final String HAS_ZONE_DATE_TIME_PROPERTY = "hasZoneDateTime";
    public static final String HAS_INSTANT_PROPERTY = "hasInstant";
    public static final String HAS_CONSTRAINTS_PROPERTY = "hasConstraints";
    public static final String HAS_NOT_NULL = "hasNotNull";
    public static final String TABLE_NAME_PROPERTY = "tableName";
    public static final String PACKAGE_PROPERTY = "package";
    public static final String PACKAGE_SIMPLE_FORMAT_PROPERTY = "packageSimpleFormat";
    public static final String PACKAGE_IMPL_PROPERTY = "packageImpl";
    public static final String IMPORTS_PROPERTY = "imports";
    public static final String IMPORT_PRIMARY_KEY_PROPERTY = "importPrimaryKey";
    public static final String IMPORT_MODEL_PROPERTY = "importModel";
    public static final String ENTITY_PROPERTY = "entity";
    public static final String ENTITY_CAMEL_CASE_PROPERTY = "entityCamelCase";
    public static final String ENTITY_CAMEL_CASE_PLURAL_PROPERTY = "entityCamelCasePlural";
    public static final String ENTITY_HYPHEN_NOTATION_PLURAL_PROPERTY = "entityHyphenNotationPlural";
    public static final String ENTITY_HYPHEN_NOTATION_PROPERTY = "entityHyphenNotation";
    public static final String ID_PROPERTY = "id";
    public static final String PRIMARY_KEY_PROPERTY = "primaryKey";
    public static final String PROPERTIES_PROPERTY = "properties";

    private LayerUtils() {

    }

    /**
     * createGeneralDescription for the template engine. It adds common
     * properties needed to identify meta info.
     * 
     * @param context
     * @return Map<String, Object>
     */
    public static Map<String, Object> addAuthoringData(GeneratorContext context) {
        Map<String, Object> model = new HashMap<>();
        model.put(YEAR_PROPERTY, context.getProjectConfiguration().getYear());
        model.put(AUTHOR_PROPERTY, context.getProjectConfiguration().getAuthor());
        model.put(IS_MONGO_PROPERTY, context.getProjectConfiguration().isMongoDb());
        model.put(PROJECT_NAME_PROPERTY, context.getProjectConfiguration().getId());
        model.put(DATA_MODEL_GROUP_PROPERTY, context.getDomainModel().getDomainModelGroup());
        return model;
    }

    /**
     * addCommonDataModelElements, add needed model properties for the template
     * engine
     * 
     * @param conf
     * @param map
     * @param bPackage
     * @param dme
     */
    public static void addCommonDataModelElements(ProjectConfiguration conf, Map<String, Object> map, String bPackage,
            DomainModelElement dme) {
        map.put(HAS_TIME_PROPERTIES_PROPERTY, dme.isHasTimeProperties());
        map.put(HAS_LOCAL_DATE_PROPERTY, dme.isHasLocalDate());
        map.put(HAS_BLOB_PROPERTIES_PROPERTY, dme.isHasBlobProperties());
        map.put(HAS_CONSTRAINTS_PROPERTY, dme.isHasConstraints());
        map.put(HAS_INSTANT_PROPERTY, dme.isHasInstant());
        map.put(HAS_ZONE_DATE_TIME_PROPERTY, dme.isHasZoneDateTime());
        map.put(HAS_BIG_DECIMAL_PROPERTY, dme.isHasBigDecimal());
        map.put(HAS_NOT_NULL, dme.isHasNotNullElements());
        map.put(URL_NAME_PROPERTY, dme.getCamelCaseFormat());
        map.put(TABLE_NAME_PROPERTY, dme.getTableName());
        map.put(PACKAGE_PROPERTY, null);// must be provided in individual files
        map.put(IMPORTS_PROPERTY, dme.getImports());
        map.put(IMPORT_SERVICE_PROPERTY,
                formatToImportStatement(bPackage, conf.getServiceLayerName(), dme.getName() + NameConventions.SERVICE));
        map.put(IMPORT_REPOSITORY_PROPERTY,
                formatToImportStatement(bPackage, conf.getDaoLayerName(), dme.getName() + NameConventions.DAO));
        map.put(IMPORT_MODEL_PROPERTY, formatToImportStatement(bPackage, conf.getDomainLayerName(), dme.getName()));
        importPrimaryKeyIfComposed(conf, map, bPackage, dme);
        map.put(ENTITY_CAMEL_CASE_PROPERTY, dme.getCamelCaseFormat());
        map.put(ENTITY_PROPERTY, dme.getName());
        map.put(ENTITY_HYPHEN_NOTATION_PROPERTY, camelCaseToHyphens(dme.getCamelCaseFormat()));
        map.put(ENTITY_HYPHEN_NOTATION_PLURAL_PROPERTY, camelCaseToHyphens(dme.getCamelCasePluralFormat()));
        map.put(ENTITY_CAMEL_CASE_PLURAL_PROPERTY, dme.getCamelCasePluralFormat());
        map.put(ID_PROPERTY, dme.getPrimaryKey().getType());
        dme.getPrimaryKey().setGenerationType(conf.getGlobalGenerationType());
        map.put(PRIMARY_KEY_PROPERTY, dme.getPrimaryKey());
        map.put(PROPERTIES_PROPERTY, dme.getProperties());
    }

    public static void importPrimaryKeyIfComposed(ProjectConfiguration conf, Map<String, Object> model, String bPackage,
            DomainModelElement dme) {
        if (dme.getPrimaryKey().isComposed()) {
            model.put(IMPORT_PRIMARY_KEY_PROPERTY,
                    formatToImportStatement(bPackage, conf.getDomainLayerName(), dme.getPrimaryKey().getType()));
        }
    }
}
