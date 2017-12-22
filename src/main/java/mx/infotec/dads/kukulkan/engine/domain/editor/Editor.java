package mx.infotec.dads.kukulkan.engine.domain.editor;

import java.io.Serializable;

/**
 * Language Represent a programming languages
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public interface Editor extends Serializable {

    String getTheme();

    String getLanguage();

    boolean isReadOnly();

    boolean isShowGutter();

    int getFirstLineNumber();
}
