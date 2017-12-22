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
package mx.infotec.dads.kukulkan.engine.service.layers.web;

import static mx.infotec.dads.kukulkan.engine.domain.editor.EditorFactory.createDefaultAceEditor;
import static mx.infotec.dads.kukulkan.engine.domain.editor.LanguageType.JAVA;
import static mx.infotec.dads.kukulkan.engine.service.layers.LayerUtils.PACKAGE_PROPERTY;
import static mx.infotec.dads.kukulkan.engine.service.layers.LayerUtils.PACKAGE_SIMPLE_FORMAT_PROPERTY;
import static mx.infotec.dads.kukulkan.util.JavaFileNameParser.formatToPackageStatement;
import static mx.infotec.dads.kukulkan.util.JavaFileNameParser.replaceDotBySlash;
import static mx.infotec.dads.kukulkan.util.JavaFileNameParser.replaceSlashByDot;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.service.layers.Layer;
import mx.infotec.dads.kukulkan.engine.service.layers.util.LayerConstants;
import mx.infotec.dads.kukulkan.templating.service.TemplateService;
import mx.infotec.dads.kukulkan.util.BasePathEnum;
import mx.infotec.dads.kukulkan.util.NameConventions;

/**
 * Service Layer Task
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service(Layer.Web.SpringRest.SERVICE_NAME)
public class RestResourceLayerTask implements WebLayerService {

    @Autowired
    private TemplateService templateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResourceLayerTask.class);

    @Override
    public void visitDomainModelElement(ProjectConfiguration pConf, Collection<DomainModelElement> dmElementCollection,
            Map<String, Object> propertiesMap, String dmgName, DomainModelElement dmElement, String basePackage) {
        LOGGER.debug("visitDomainModelElement {} ", basePackage);
        String webLayerDotFormat = replaceSlashByDot(pConf.getWebLayerName());
        String webLayerSlashFormat = replaceDotBySlash(pConf.getWebLayerName());
        propertiesMap.put(PACKAGE_PROPERTY, formatToPackageStatement(basePackage, webLayerDotFormat));
        propertiesMap.put(PACKAGE_SIMPLE_FORMAT_PROPERTY,
                formatToPackageStatement(true, basePackage, webLayerDotFormat));
        templateService.fillModel(dmElement, pConf.getId(),
                LayerConstants.REST_SPRING_JPA_BACK_END_URL + "/restResource.ftl", propertiesMap,
                BasePathEnum.SRC_MAIN_JAVA, basePackage.replace('.', '/') + "/" + dmgName + "/" + webLayerSlashFormat
                        + "/" + dmElement.getName() + NameConventions.REST_CONTROLLER + ".java",
                createDefaultAceEditor(JAVA));
    }
}
