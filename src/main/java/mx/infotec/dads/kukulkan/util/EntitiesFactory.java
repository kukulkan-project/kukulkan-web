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

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.assets.domain.Discipline;
import mx.infotec.dads.kukulkan.assets.domain.Granularity;
import mx.infotec.dads.kukulkan.assets.domain.LevelOfImplementation;
import mx.infotec.dads.kukulkan.assets.domain.Phase;
import mx.infotec.dads.kukulkan.assets.domain.ProblemDomain;
import mx.infotec.dads.kukulkan.assets.domain.State;
import mx.infotec.dads.kukulkan.domain.DataStore;
import mx.infotec.dads.kukulkan.domain.enumeration.TableTypes;
import mx.infotec.dads.kukulkan.engine.domain.core.DataStoreType;
import mx.infotec.dads.kukulkan.engine.domain.core.PrimaryKey;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.domain.core.Rule;
import mx.infotec.dads.kukulkan.engine.domain.core.RuleType;

/**
 * EntitiesFactory provide common entities with a properly initialization
 * 
 * @author Daniel Cortes Pichardo
 */
public class EntitiesFactory {

    private static final String STRING_TYPE = "String";
    private static final String STRING_QUALIFIED_NAME = "java.lang.String";
    private static final String ID_DEFAULT_NAME = "id";

    private EntitiesFactory() {

    }

    public static PrimaryKey createDefaultPrimaryKey() {
        PrimaryKey pk = PrimaryKey.createOrderedDataModel();
        pk.setType(STRING_TYPE);
        pk.setName(ID_DEFAULT_NAME);
        pk.setQualifiedLabel(STRING_QUALIFIED_NAME);
        pk.setComposed(Boolean.FALSE);
        return pk;
    }

    public static DataStore createDataStore() {
        DataStore ds = new DataStore();
        ds.setDataStoreType(new DataStoreType());
        ds.setDriverClass("");
        ds.setName("");
        ds.setSchema("");
        ds.setPassword("");
        ds.setTableTypes(TableTypes.TABLE_VIEW);
        ds.setUsername("");
        return ds;
    }

    public static KukulkanContext createDefaultKukulkanContext() {
        ProjectConfiguration pConf = new ProjectConfiguration();
        pConf.setId("conacyt");
        pConf.setGroupId("");
        pConf.setVersion("1.0.0");
        pConf.setPackaging("");
        pConf.setYear("2017");
        pConf.setAuthor("KUKULKAN");
        pConf.setWebLayerName("rest");
        pConf.setServiceLayerName("service");
        pConf.setDaoLayerName("repository");
        pConf.setDomainLayerName("model");
        pConf.setGroupId("mx.infotec.dads");
        pConf.setPackaging("mx.infotec.dads.conacyt");
        return new KukulkanContext(pConf, "");
    }

    public static DataStore createTestDataStore(DataStoreType dst) {
        DataStore testDataStore = new DataStore();
        testDataStore.setDataStoreType(dst);
        testDataStore.setDriverClass("org.h2.Driver");
        testDataStore.setName("h2-db-test");
        testDataStore.setPassword("");
        testDataStore.setTableTypes(TableTypes.TABLE_VIEW);
        testDataStore.setUrl("jdbc:h2:~");
        testDataStore.setSchema("test");
        testDataStore.setUsername("");
        return testDataStore;
    }

    public static DataStore createAtlasDataStore(DataStoreType dst) {
        DataStore atlasDataStore = new DataStore();
        atlasDataStore.setDataStoreType(dst);
        atlasDataStore.setDriverClass("com.mysql.jdbc.Driver");
        atlasDataStore.setName("atlas");
        atlasDataStore.setPassword("");
        atlasDataStore.setTableTypes(TableTypes.TABLE_VIEW);
        atlasDataStore.setUrl("jdbc:mysql://localhost");
        atlasDataStore.setSchema("atlas");
        atlasDataStore.setUsername("root");
        return atlasDataStore;
    }

    public static DataStore createMysqlTestDataStore(DataStoreType dst) {
        DataStore mysqlDataStore = new DataStore();
        mysqlDataStore.setDataStoreType(dst);
        mysqlDataStore.setDriverClass("org.h2.Driver");
        mysqlDataStore.setName("test");
        mysqlDataStore.setPassword("");
        mysqlDataStore.setTableTypes(TableTypes.TABLE_VIEW);
        mysqlDataStore.setUrl("jdbc:h2:~");
        mysqlDataStore.setSchema("test");
        mysqlDataStore.setUsername("");
        return mysqlDataStore;
    }

    public static DataStore createGrammarDataStore(DataStoreType dst) {
        DataStore atlasDataStore = new DataStore();
        atlasDataStore.setDataStoreType(dst);
        atlasDataStore.setDriverClass("NO APLICA");
        atlasDataStore.setName(DataStoreConstants.DATA_STORE_TYPE_GRAMMAR);
        atlasDataStore.setPassword("NO APLICA");
        atlasDataStore.setTableTypes(TableTypes.TABLE_VIEW);
        atlasDataStore.setUrl("NO APLICA");
        atlasDataStore.setSchema("NO APLICA");
        atlasDataStore.setUsername("NO APLICA");
        return atlasDataStore;
    }

    public static DataStoreType createDefaultDataStoreType() {
        DataStoreType dst = new DataStoreType();
        dst.setDescription("Data Store for JDBC connector");
        dst.setName("jdbc");
        return dst;
    }

    public static DataStoreType createGrammarDataStoreType() {
        DataStoreType dst = new DataStoreType();
        dst.setDescription("Kukulan DataStore Type");
        dst.setName(DataStoreConstants.DATA_STORE_TYPE_GRAMMAR);
        return dst;
    }

    public static RuleType createDefaultSingularRuleType() {
        RuleType singularRuleType = new RuleType();
        singularRuleType.setDescription("regla que aplica para palabras convertir palabras de plural a singular");
        singularRuleType.setName("singular");
        return singularRuleType;
    }

    public static RuleType createDefaultPluralRuleType() {
        RuleType plurarlRuleType = new RuleType();
        plurarlRuleType.setDescription("regla que aplica para palabras convertir palabras de singular a plural");
        plurarlRuleType.setName("plural");
        return plurarlRuleType;
    }

    public static Rule createOsRule(RuleType ruleType) {
        Rule osRule = new Rule();
        osRule.setExpression("os$");
        osRule.setReplacement("o");
        osRule.setRuleType(ruleType);
        return osRule;
    }

    public static Rule createEsRule(RuleType ruleType) {
        Rule esRule = new Rule();
        esRule.setExpression("es$");
        esRule.setReplacement("");
        esRule.setRuleType(ruleType);
        return esRule;
    }

    public static List<LevelOfImplementation> createLevelOfImplementation() {
        List<LevelOfImplementation> levels = new ArrayList<>();
        LevelOfImplementation ninguno = new LevelOfImplementation();
        ninguno.setName("Especificación");
        ninguno.setBriefDescription("Especificación");
        ninguno.setDescription("Especificación");
        ninguno.setOrder(1);
        levels.add(ninguno);
        LevelOfImplementation parcial = new LevelOfImplementation();
        parcial.setName("Parcial");
        parcial.setBriefDescription("Parcial");
        parcial.setDescription("Parcial");
        parcial.setOrder(2);
        levels.add(parcial);
        LevelOfImplementation completo = new LevelOfImplementation();
        completo.setName("Completo");
        completo.setBriefDescription("Completo");
        completo.setDescription("Completo");
        completo.setOrder(3);
        levels.add(completo);
        return levels;
    }

    public static List<State> createStates() {
        List<State> states = new ArrayList<>();
        State resp = new State();
        resp.setName("Responsabilidades Asignadas");
        resp.setBriefDescription("Responsabilidades Asignadas");
        resp.setDescription("Responsabilidades Asignadas");
        resp.setOrder(1);
        states.add(resp);
        State especificado = new State();
        especificado.setName("Especificado");
        especificado.setBriefDescription("Especificado");
        especificado.setDescription("Especificado");
        especificado.setOrder(2);
        states.add(especificado);
        State implementado = new State();
        implementado.setName("Implementado");
        implementado.setBriefDescription("Implementado");
        implementado.setDescription("Implementado");
        implementado.setOrder(3);
        states.add(implementado);
        State integrado = new State();
        integrado.setName("Integrado");
        integrado.setBriefDescription("Integrado");
        integrado.setDescription("Integrado");
        integrado.setOrder(4);
        states.add(integrado);
        State verificado = new State();
        verificado.setName("Verificado");
        verificado.setBriefDescription("Verificado");
        verificado.setDescription("Verificado");
        verificado.setOrder(5);
        states.add(verificado);
        State liberado = new State();
        liberado.setName("Liberado");
        liberado.setBriefDescription("Liberado");
        liberado.setDescription("Liberado");
        liberado.setOrder(6);
        states.add(liberado);
        return states;
    }

    public static List<Discipline> createDisciplines() {
        List<Discipline> disciplines = new ArrayList<>();
        Discipline mod = new Discipline();
        mod.setName("Modelado Organizacional");
        mod.setBriefDescription("Modelado Organizacional");
        mod.setDescription("Modelado Organizacional");
        mod.setOrder(1);
        disciplines.add(mod);
        Discipline requerimientos = new Discipline();
        requerimientos.setName("Requerimientos");
        requerimientos.setBriefDescription("Requerimientos");
        requerimientos.setDescription("Requerimientos");
        requerimientos.setOrder(2);
        disciplines.add(requerimientos);
        Discipline analisis = new Discipline();
        analisis.setName("Análisis y Diseño");
        analisis.setBriefDescription("Análisis y Diseño");
        analisis.setDescription("Análisis y Diseño");
        analisis.setOrder(3);
        disciplines.add(analisis);
        Discipline construccion = new Discipline();
        construccion.setName("Construcción");
        construccion.setBriefDescription("Construcción");
        construccion.setDescription("Construcción");
        construccion.setOrder(4);
        disciplines.add(construccion);
        Discipline pruebas = new Discipline();
        pruebas.setName("Pruebas");
        pruebas.setBriefDescription("Pruebas");
        pruebas.setDescription("Pruebas");
        pruebas.setOrder(5);
        disciplines.add(pruebas);
        Discipline despliegue = new Discipline();
        despliegue.setName("Despliegue");
        despliegue.setBriefDescription("Despliegue");
        despliegue.setDescription("Despliegue");
        despliegue.setOrder(6);
        disciplines.add(despliegue);
        return disciplines;
    }

    public static List<Phase> createPhases() {
        List<Phase> phases = new ArrayList<>();
        Phase inicio = new Phase();
        inicio.setName("Inicio");
        inicio.setBriefDescription("Inicio");
        inicio.setDescription("Inicio");
        inicio.setOrder(1);
        phases.add(inicio);
        Phase elaboracion = new Phase();
        elaboracion.setName("Elaboración");
        elaboracion.setBriefDescription("Elaboración");
        elaboracion.setDescription("Elaboración");
        elaboracion.setOrder(2);
        phases.add(elaboracion);
        Phase construccion = new Phase();
        construccion.setName("Construcción");
        construccion.setBriefDescription("Construcción");
        construccion.setDescription("Construcción");
        construccion.setOrder(3);
        phases.add(construccion);
        Phase transicion = new Phase();
        transicion.setName("Transición");
        transicion.setBriefDescription("Transición");
        transicion.setDescription("Transición");
        transicion.setOrder(4);
        phases.add(transicion);
        return phases;
    }

    public static List<ProblemDomain> createDomains() {
        List<ProblemDomain> domains = new ArrayList<>();
        ProblemDomain general = new ProblemDomain();
        general.setName("General");
        general.setBriefDescription("General");
        general.setDescription("General");
        general.setOrder(1);
        domains.add(general);
        ProblemDomain seguridad = new ProblemDomain();
        seguridad.setName("Seguridad");
        seguridad.setBriefDescription("Seguridad");
        seguridad.setDescription("Seguridad");
        seguridad.setOrder(2);
        domains.add(seguridad);
        return domains;
    }

    public static List<Granularity> createGranularities() {
        List<Granularity> domains = new ArrayList<>();
        Granularity general = new Granularity();
        general.setName("General");
        general.setBriefDescription("Grano Grueso");
        general.setDescription("Grano Grueso");
        general.setOrder(1);
        domains.add(general);
        Granularity especifico = new Granularity();
        especifico.setName("Específico");
        especifico.setBriefDescription("Grano fino");
        especifico.setDescription("Grano fino");
        especifico.setOrder(2);
        domains.add(especifico);
        return domains;
    }
}
