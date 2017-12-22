package mx.infotec.dads.kukulkan.engine.domain.editor;

/**
 * Editor Factory used for create common Editor Objects
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class EditorFactory {

    private EditorFactory() {

    }

    public static AceEditor createDefaultAceEditor(LanguageType languageType) {
        return new AceEditor.AceEditorBuilder()
                .fistLineNumber(1)
                .isReadOnly(true)
                .showGutter(true)
                .withTheme("twilight")
                .withLanguage(languageType.language())
                .build();
    }
}
