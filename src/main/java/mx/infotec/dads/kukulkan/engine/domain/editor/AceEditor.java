package mx.infotec.dads.kukulkan.engine.domain.editor;

/**
 * AceEditor implements the Ace Editor methods
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class AceEditor implements Editor {

    private static final long serialVersionUID = 1L;

    private String theme;
    private String language;
    private boolean readOnly;
    private boolean showGutter;
    private int firstLineNumber;

    @Override
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public boolean isShowGutter() {
        return showGutter;
    }

    public void setShowGutter(boolean showGutter) {
        this.showGutter = showGutter;
    }

    @Override
    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    public void setFirstLineNumber(int firstLineNumber) {
        this.firstLineNumber = firstLineNumber;
    }

    public static class AceEditorBuilder {
        // optional
        private String theme;
        private String language;
        private boolean readOnly;
        private boolean showGutter;
        private int firstLineNumber;

        public AceEditorBuilder withTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public AceEditorBuilder withLanguage(String language) {
            this.language = language;
            return this;
        }

        public AceEditorBuilder isReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
            return this;
        }

        public AceEditorBuilder showGutter(boolean showGutter) {
            this.showGutter = showGutter;
            return this;
        }

        public AceEditorBuilder fistLineNumber(int firstLineNumber) {
            this.firstLineNumber = firstLineNumber;
            return this;
        }

        public AceEditor build() {
            AceEditor editor = new AceEditor();
            editor.setLanguage(this.language);
            editor.setFirstLineNumber(this.firstLineNumber);
            editor.setReadOnly(this.readOnly);
            editor.setShowGutter(this.showGutter);
            editor.setTheme(this.theme);
            return editor;
        }

    }
}
