package mx.infotec.dads.kukulkan.engine.domain.core;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

import mx.infotec.dads.kukulkan.engine.domain.editor.Editor;

/**
 * KukulkanProject represent a generated project
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class GeneratedElement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The Path where the content must be generated
     */
    private Path path;
    
    private String simplePath;

    /**
     * The content of the generated Element, it is filled from the freemarker
     * Template
     */
    private String content;

    /**
     * Properties related to the Editor
     */
    private Editor editor;

    public GeneratedElement() {
    }

    public GeneratedElement(Path path, String simplePath, String content, Editor editor) {
        this.path = path;
        this.setSimplePath(simplePath);
        this.content = content;
        this.editor = editor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public String getSimplePath() {
        return simplePath;
    }

    public void setSimplePath(String simplePath) {
        this.simplePath = simplePath;
    }
}
