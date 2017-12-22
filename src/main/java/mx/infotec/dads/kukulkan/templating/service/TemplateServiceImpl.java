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
package mx.infotec.dads.kukulkan.templating.service;

import static mx.infotec.dads.kukulkan.engine.domain.editor.EditorFactory.createDefaultAceEditor;
import static mx.infotec.dads.kukulkan.engine.domain.editor.LanguageType.JAVA;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import mx.infotec.dads.kukulkan.KukulkanConfigurationProperties;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModel;
import mx.infotec.dads.kukulkan.engine.domain.core.DomainModelElement;
import mx.infotec.dads.kukulkan.engine.domain.core.GeneratedElement;
import mx.infotec.dads.kukulkan.engine.domain.editor.Editor;
import mx.infotec.dads.kukulkan.util.BasePathEnum;
import mx.infotec.dads.kukulkan.util.FileUtil;
import mx.infotec.dads.kukulkan.util.TemplateUtil;
import mx.infotec.dads.kukulkan.util.exceptions.ApplicationException;

/**
 * 
 * @author Daniel Cortes Pichardo
 * @since 1.0.0
 * @version 1.0.0
 */

@Service
public class TemplateServiceImpl implements TemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Autowired
    private Configuration fmConfiguration;

    @Autowired
    private KukulkanConfigurationProperties prop;

    @Override
    public void fillModel(DomainModelElement dme, String proyectoId, String templateName, Object model,
            BasePathEnum basePath, String filePath) {
        fillModel(dme, proyectoId, templateName, model, basePath, filePath, createDefaultAceEditor(JAVA));
    }

    @Override
    public void fillModel(DomainModelElement dme, String proyectoId, String templateName, Object model,
            BasePathEnum basePath, String filePath, Editor editor) {
        Optional<Template> templateOptional = TemplateUtil.get(fmConfiguration, templateName);
        if (templateOptional.isPresent()) {
            Path path = FileUtil.buildPath(proyectoId, basePath, filePath, prop.getConfig().getOutputdir());
            String simplePath = basePath.getPath() + filePath;
            dme.addGeneratedElement(processTemplate(model, templateOptional.get(), path, simplePath, editor));
        }
    }

    @Override
    public void fillModel(DomainModel dm, String proyectoId, String templateName, Object model, BasePathEnum basePath,
            String filePath, Editor editor) {
        Optional<Template> templateOptional = TemplateUtil.get(fmConfiguration, templateName);
        if (templateOptional.isPresent()) {
            Path path = FileUtil.buildPath(proyectoId, basePath, filePath, prop.getConfig().getOutputdir());
            String simplePath = basePath.getPath() + filePath;
            dm.addGeneratedElement(processTemplate(model, templateOptional.get(), path, simplePath, editor));
        } else {
            LOGGER.warn("Template not found for {}", templateName);
        }
    }

    public static GeneratedElement processTemplate(Object model, Template template, Path path, String simplePath,
            Editor editor) {
        try (StringWriter stringWriter = new StringWriter()) {
            LOGGER.info("Generating to: {}", path.normalize().toFile());
            template.process(model, stringWriter);
            return new GeneratedElement(path, simplePath, stringWriter.toString(), editor);
        } catch (IOException | TemplateException e) {
            throw new ApplicationException("Fill Model Error", e);
        }
    }

    @Override
    public void fillModel(String proyectoId, String templateName, Object model, BasePathEnum basePath, String filePath,
            Editor editor) {
        TemplateUtil.get(fmConfiguration, templateName).ifPresent(template -> {
            Path path = FileUtil.buildPath(proyectoId, basePath, filePath, prop.getConfig().getOutputdir());
            String simplePath = basePath.getPath() + filePath;
            processTemplate(model, template, path, simplePath, editor);
        });
    }
}
