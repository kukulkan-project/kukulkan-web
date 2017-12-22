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
package mx.infotec.dads.kukulkan.engine.service.layers.frontend;

import static mx.infotec.dads.kukulkan.engine.domain.editor.EditorFactory.createDefaultAceEditor;
import static mx.infotec.dads.kukulkan.engine.domain.editor.LanguageType.HTML;
import static mx.infotec.dads.kukulkan.engine.domain.editor.LanguageType.JAVASCRIPT;
import static mx.infotec.dads.kukulkan.engine.domain.editor.LanguageType.JSON;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_CONTROLLER_JS;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_DELETE_DIALOG_CONTROLLER_JS;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_DELETE_DIALOG_HTML;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_DETAIL_CONTROLLER_JS;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_DETAIL_HTML;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_DIALOG_CONTROLLER_JS;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_DIALOG_HTML;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_HTML;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_SEARCH_SERVICE_JS;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_SERVICE_JS;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.ENTITY_STATE_JS;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.FRONT_END_ENTITIES_LOCATION;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.FRONT_END_I18N_LOCATION_EN;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.FRONT_END_I18N_LOCATION_ES;
import static mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants.IDIOMA_JS;
import static mx.infotec.dads.kukulkan.util.BasePathEnum.WEB_APP_ENTITIES;
import static mx.infotec.dads.kukulkan.util.BasePathEnum.WEB_APP_I18N;
import static mx.infotec.dads.kukulkan.util.BasePathEnum.WEB_APP_NAV_BAR;
import static mx.infotec.dads.kukulkan.util.BasePathEnum.WEB_INDEX;
import static mx.infotec.dads.kukulkan.util.NameConventionFormatter.camelCaseToHyphens;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.domain.editor.LanguageType;
import mx.infotec.dads.kukulkan.engine.service.layers.Layer;
import mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants;
import mx.infotec.dads.kukulkan.engine.service.layers.util.TemplateFormatter;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;

/**
 * Service Layer Task
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service(Layer.FrontEnd.Angular.SERVICE_NAME)
public class AngularLayerServiceImpl implements FrontEndLayerService {

    @Autowired
    private TemplateService templateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AngularLayerServiceImpl.class);

    @Override
    public void doBeforeProcessDataModelGroup(GeneratorContext context, Map<String, Object> model) {
        fillNavBar(context.getProjectConfiguration(), model, context.getDomainModel());
        fillIdiomaGlobalEsJs(context.getProjectConfiguration(), model, context.getDomainModel());
        fillIdiomaGlobalEnJs(context.getProjectConfiguration(), model, context.getDomainModel());
        fillIndex(context.getProjectConfiguration(), model, context.getDomainModel());
    }

    @Override
    public void visitDomainModelElement(ProjectConfiguration pConf, Collection<DomainModelElement> dmElementCollection,
            Map<String, Object> propertiesMap, String dmgName, DomainModelElement dmElement, String basePackage) {
        fillEntityControllerJs(pConf, propertiesMap, dmElement);
        fillEntityDeleteDialogControllerJs(pConf, propertiesMap, dmElement);
        fillEntityDeleteDialogHtml(pConf, propertiesMap, dmElement);
        fillEntityDetailControllerJs(pConf, propertiesMap, dmElement);
        fillEntityDetailHtml(pConf, propertiesMap, dmElement);
        fillEntityDialogControllerJs(pConf, propertiesMap, dmElement);
        fillEntityDialogHtml(pConf, propertiesMap, dmElement);
        fillEntityHtml(pConf, propertiesMap, dmElement);
        fillEntitySearchServiceJs(pConf, propertiesMap, dmElement);
        fillEntityServiceJs(pConf, propertiesMap, dmElement);
        fillEntityStateJs(pConf, propertiesMap, dmElement);
        fillIdiomaEsJs(pConf, propertiesMap, dmElement);
        fillIdiomaEnJs(pConf, propertiesMap, dmElement);
    }

    private void fillNavBar(ProjectConfiguration pConf, Map<String, Object> model, DomainModel domainModel) {
        templateService.fillModel(domainModel, pConf.getId(), "rest-spring-jpa/frontEnd/navbar.html.ftl", model,
                WEB_APP_NAV_BAR, "/navbar.html", createDefaultAceEditor(HTML));
    }

    private void fillIdiomaGlobalEnJs(ProjectConfiguration pConf, Map<String, Object> model, DomainModel domainModel) {
        templateService.fillModel(domainModel, pConf.getId(), "rest-spring-jpa/frontEnd/i18n/en/global.json.ftl", model,
                WEB_APP_I18N, "/en/global.json", createDefaultAceEditor(JSON));
    }

    private void fillIdiomaGlobalEsJs(ProjectConfiguration pConf, Map<String, Object> model, DomainModel domainModel) {
        templateService.fillModel(domainModel, pConf.getId(), "rest-spring-jpa/frontEnd/i18n/es/global.json.ftl", model,
                WEB_APP_I18N, "/es/global.json", createDefaultAceEditor(JSON));
    }

    private void fillIndex(ProjectConfiguration pConf, Map<String, Object> model, DomainModel domainModel) {
        templateService.fillModel(domainModel, pConf.getId(), "common/index.html.ftl", model, WEB_INDEX, "/index.html",
                createDefaultAceEditor(HTML));
    }

    private void fillEntityControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityControllerJs {}", ENTITY_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_CONTROLLER_JS, false);

    }

    private void fillIdiomaEsJs(ProjectConfiguration pConf, Map<String, Object> model, DomainModelElement dmElement) {
        LOGGER.info("fillIdiomaEsJs {}", IDIOMA_JS);
        saveInternationalizationTemplate(pConf, model, dmElement, FRONT_END_I18N_LOCATION_ES, IDIOMA_JS, "es");
    }

    private void fillIdiomaEnJs(ProjectConfiguration pConf, Map<String, Object> model, DomainModelElement dmElement) {
        LOGGER.info("fillIdiomaEnJs {}", IDIOMA_JS);
        saveInternationalizationTemplate(pConf, model, dmElement, FRONT_END_I18N_LOCATION_EN, IDIOMA_JS, "en");
    }

    private void fillEntityStateJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityStateJs {}", ENTITY_STATE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_STATE_JS, false);
    }

    private void fillEntityServiceJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityServiceJs {}", ENTITY_SERVICE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_SERVICE_JS, false);
    }

    private void fillEntitySearchServiceJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntitySearchServiceJs {}", ENTITY_SEARCH_SERVICE_JS);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_SEARCH_SERVICE_JS, false);
    }

    private void fillEntityHtml(ProjectConfiguration pConf, Map<String, Object> model, DomainModelElement dmElement) {
        LOGGER.info("fillEntityHtml {}", ENTITY_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_HTML, true, HTML);
    }

    private void fillEntityDialogHtml(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDialogHtml {}", ENTITY_DETAIL_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_DIALOG_HTML, false, HTML);
    }

    private void fillEntityDialogControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDialogControllerJs {}", ENTITY_DIALOG_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_DIALOG_CONTROLLER_JS, false);
    }

    private void fillEntityDetailControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDetailControllerJs {}", ENTITY_DETAIL_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_DETAIL_CONTROLLER_JS, false);
    }

    private void fillEntityDetailHtml(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDetailHtml {}", LayerConstants.ENTITY_DETAIL_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_DETAIL_HTML, false, HTML);
    }

    private void fillEntityDeleteDialogHtml(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDeleteDialogHtml {}", ENTITY_DELETE_DIALOG_HTML);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_DELETE_DIALOG_HTML, false,
                HTML);
    }

    private void fillEntityDeleteDialogControllerJs(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement) {
        LOGGER.info("fillEntityDeleteDialogControllerJs {}", ENTITY_DELETE_DIALOG_CONTROLLER_JS);
        saveFrontEndTemplate(pConf, model, dmElement, FRONT_END_ENTITIES_LOCATION, ENTITY_DELETE_DIALOG_CONTROLLER_JS,
                false);
    }

    private void saveFrontEndTemplate(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement, String templateLocation, String templateName, boolean isPlural) {
        saveFrontEndTemplate(pConf, model, dmElement, templateLocation, templateName, isPlural, JAVASCRIPT);
    }

    private void saveFrontEndTemplate(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement, String templateLocation, String templateName, boolean isPlural,
            LanguageType languageType) {
        String fileNamingConvention = camelCaseToHyphens(dmElement.getCamelCaseFormat());
        String entityName = fileNamingConvention;
        if (isPlural) {
            entityName = camelCaseToHyphens(dmElement.getCamelCasePluralFormat());
        }
        templateService.fillModel(dmElement, pConf.getId(), templateLocation + templateName, model, WEB_APP_ENTITIES,
                fileNamingConvention + "/" + entityName + TemplateFormatter.formatNameTemplate(templateName),
                createDefaultAceEditor(languageType));
    }

    private void saveInternationalizationTemplate(ProjectConfiguration pConf, Map<String, Object> model,
            DomainModelElement dmElement, String templateLocation, String templateName, String idiomaKey) {
        String fileNamingConvention = camelCaseToHyphens(dmElement.getCamelCaseFormat());
        templateService.fillModel(dmElement, pConf.getId(), templateLocation + templateName, model, WEB_APP_I18N,
                idiomaKey + "/" + fileNamingConvention + TemplateFormatter.formatNameTemplate(templateName),
                createDefaultAceEditor(JSON));
    }
}
