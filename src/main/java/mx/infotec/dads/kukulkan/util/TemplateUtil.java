package mx.infotec.dads.kukulkan.util;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * TemplateUtil class used for basic operation with freemarker
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class TemplateUtil {

    private static final Logger log = LoggerFactory.getLogger(TemplateUtil.class);

    private TemplateUtil() {
    }

    public static Optional<Template> get(Configuration config, String templateName) {
        Template template = null;
        try {
            template = config.getTemplate(templateName);
        } catch (IOException e) {
            log.debug("getTemplate", e);
        }
        return Optional.ofNullable(template);
    }
}
