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
package mx.infotec.dads.kukulkan.engine.service.layers;

import static mx.infotec.dads.kukulkan.engine.service.layers.LayerUtils.addAuthoringData;
import static mx.infotec.dads.kukulkan.engine.service.layers.LayerUtils.addCommonDataModelElements;

import java.util.Collection;
import java.util.Map;

import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelGroup;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratorContext;
import mx.infotec.dads.kukulkan.engine.domain.core.ProjectConfiguration;

/**
 * Abstract Template ControllerLayerTask
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public abstract class TemplateLayerTask implements LayerTask {

    @Override
    public void doTask(GeneratorContext context) {
        Map<String, Object> propertiesMap = addAuthoringData(context);
        doBeforeProcessDataModelGroup(context, propertiesMap);
        doForEachDataModelGroupTemplate(context.getProjectConfiguration(),
                context.getDomainModel().getDomainModelGroup(), propertiesMap);
    }

    public void doForEachDataModelGroupTemplate(ProjectConfiguration pConf, Collection<DomainModelGroup> dmGroup,
            final Map<String, Object> propertiesMap) {
        for (DomainModelGroup dataModelGroup : dmGroup) {
            doForEachDataModelElement(pConf, dataModelGroup.getDomainModelElements(), propertiesMap,
                    dataModelGroup.getName());
        }
    }

    public void doForEachDataModelElement(ProjectConfiguration pConf,
            Collection<DomainModelElement> dmElementCollection, final Map<String, Object> propertiesMap,
            String dmgName) {
        String basePackage = pConf.getPackaging() + dmgName;
        for (DomainModelElement dmElement : dmElementCollection) {
            addCommonDataModelElements(pConf, propertiesMap, basePackage, dmElement);
            visitDomainModelElement(pConf, dmElementCollection, propertiesMap, dmgName, dmElement, basePackage);
        }
    }

    public abstract void visitDomainModelElement(ProjectConfiguration pConf,
            Collection<DomainModelElement> dmElementCollection, Map<String, Object> propertiesMap, String dmgName,
            DomainModelElement dmElement, String basePackage);

    /**
     * doBeforeProcessDataModelGroup, It is used for add new functionality
     * before process each DataModelGroup. General information could be added
     * here
     * 
     * @param context
     * @param propertiesMap
     */
    public void doBeforeProcessDataModelGroup(GeneratorContext context, final Map<String, Object> propertiesMap) {

    }

}
