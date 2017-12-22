package mx.infotec.dads.kukulkan.engine.domain.editor;

/**
 * LanguageType for the language supported
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public enum LanguageType {

    SQL("sql"), 
    CSS("css"), 
    JSON("json"), 
    JAVA("java"), 
    YAML("yaml"), 
    HTML("html"),
    MYSQL("mysql"), 
    CSHARP("csharp"), 
    MARKDOWN("markdown"), 
    JAVASCRIPT("javascript"), 
    TYPESCRIPT("typescript"), 
    FREEMARKER("ftl"),
    DOCKERFILE("dockerfile"); 
    
    private String language;

    LanguageType(String language) {
        this.language = language;
    }

    String language() {
        return language;
    }
}
