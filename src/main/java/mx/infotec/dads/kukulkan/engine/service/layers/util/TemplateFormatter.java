package mx.infotec.dads.kukulkan.engine.service.layers.util;

/**
 * Template Formatter
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class TemplateFormatter {
    private TemplateFormatter() {
    }

    public static String formatNameTemplate(String template) {
    	return template.replaceAll("entity|.ftl", "");
    }
}
