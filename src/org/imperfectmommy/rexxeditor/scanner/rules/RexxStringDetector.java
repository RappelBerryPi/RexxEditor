package org.imperfectmommy.rexxeditor.scanner.rules;

import org.eclipse.jface.text.rules.IWordDetector;

public class RexxStringDetector implements IWordDetector {
    
    boolean inQuotes;

    @Override
    public boolean isWordStart(char c) {
        return (c == '"' || c == '\'');
    }

    @Override
    public boolean isWordPart(char c) {
        return !(c == '"' || c == '\'' || Character.isWhitespace(c));
    }

}
