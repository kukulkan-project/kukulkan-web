package mx.infotec.dads.kukulkan.config.dbmigrations;

import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDefaultDataStoreType;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createGrammarDataStoreType;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDefaultPluralRuleType;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDefaultSingularRuleType;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDisciplines;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createDomains;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createEsRule;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createGranularities;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createLevelOfImplementation;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createOsRule;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createPhases;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createStates;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createTestDataStore;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createAtlasDataStore;
import static mx.infotec.dads.kukulkan.util.EntitiesFactory.createGrammarDataStore;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "002")
public class CatalogSetupMigration {

    @ChangeSet(order = "01", author = "dcp", id = "02-kukulkan")
    public void addDataStoreType(MongoTemplate mongoTemplate) {
        DataStoreType dst = createDefaultDataStoreType();
        mongoTemplate.save(dst);
        DataStoreType dstGrammar = createGrammarDataStoreType();
        mongoTemplate.save(dstGrammar);
        DataStore testDs = createTestDataStore(dst);
        mongoTemplate.save(testDs);
        DataStore atlasDs = createAtlasDataStore(dst);
        mongoTemplate.save(atlasDs);
        DataStore grammar = createGrammarDataStore(dstGrammar);
        mongoTemplate.save(grammar);
        RuleType singularRuleType = createDefaultSingularRuleType();
        mongoTemplate.save(singularRuleType);
        mongoTemplate.save(createDefaultPluralRuleType());
        mongoTemplate.save(createOsRule(singularRuleType));
        mongoTemplate.save(createEsRule(singularRuleType));
    }

    @ChangeSet(order = "02", author = "dcp", id = "02-assets")
    public void addAssets(MongoTemplate mongoTemplate) {
        createLevelOfImplementation().forEach(entity -> mongoTemplate.save(entity));
        createStates().forEach(entity -> mongoTemplate.save(entity));
        createDisciplines().forEach(entity -> mongoTemplate.save(entity));
        createPhases().forEach(entity -> mongoTemplate.save(entity));
        createDomains().forEach(entity -> mongoTemplate.save(entity));
        createGranularities().forEach(entity -> mongoTemplate.save(entity));
    }
}
