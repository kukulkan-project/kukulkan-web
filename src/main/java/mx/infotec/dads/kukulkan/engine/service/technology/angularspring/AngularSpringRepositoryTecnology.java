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
package mx.infotec.dads.kukulkan.engine.service.technology.angularspring;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;
import mx.infotec.dads.kukulkan.engine.service.layers.Layer;
import mx.infotec.dads.kukulkan.engine.service.layers.domain.DomainLayerService;
import mx.infotec.dads.kukulkan.engine.service.layers.frontend.FrontEndLayerService;
import mx.infotec.dads.kukulkan.engine.service.layers.repository.DataAccessLayerService;
import mx.infotec.dads.kukulkan.engine.service.layers.service.BusinessLayerService;
import mx.infotec.dads.kukulkan.engine.service.layers.web.WebLayerService;

/**
 * Service Layer Task
 * 
 * @author Daniel Cortes Pichardo
 *
 */
@Service("angularLayerTask")
public class AngularSpringRepositoryTecnology extends AbstractAngularSpringLayerTask {

    @Autowired
    @Qualifier(Layer.FrontEnd.Angular.SERVICE_NAME)
    private FrontEndLayerService frontEndservice;

    @Autowired
    @Qualifier(Layer.Domain.Core.SERVICE_NAME)
    private DomainLayerService domainService;

    @Autowired
    @Qualifier(Layer.Web.SpringRest.SERVICE_NAME)
    private WebLayerService webLayerService;

    @Autowired
    @Qualifier(Layer.Business.SpringService.SERVICE_NAME)
    private BusinessLayerService businessLayerService;

    @Autowired
    @Qualifier(Layer.DataAccess.Repository.SERVICE_NAME)
    private DataAccessLayerService dataAccessService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AngularSpringRepositoryTecnology.class);

    @Override
    public void doBeforeProcessDataModelGroup(GeneratorContext context, Map<String, Object> model) {
        LOGGER.debug("doBeforeProcessDataModelGroup");
        frontEndservice.doBeforeProcessDataModelGroup(context, model);
    }

    @Override
    public void visitDomainModelElement(ProjectConfiguration pConf, Collection<DomainModelElement> dmElementCollection,
            Map<String, Object> propertiesMap, String dmgName, DomainModelElement dmElement, String basePackage) {
        LOGGER.debug("visitDomainModelElement");
        frontEndservice.visitDomainModelElement(pConf, dmElementCollection, propertiesMap, dmgName, dmElement, basePackage);
        webLayerService.visitDomainModelElement(pConf, dmElementCollection, propertiesMap, dmgName, dmElement, basePackage);
        domainService.visitDomainModelElement(pConf, dmElementCollection, propertiesMap, dmgName, dmElement, basePackage);
        businessLayerService.visitDomainModelElement(pConf, dmElementCollection, propertiesMap, dmgName, dmElement, basePackage);
        dataAccessService.visitDomainModelElement(pConf, dmElementCollection, propertiesMap, dmgName, dmElement, basePackage);
    }
}
