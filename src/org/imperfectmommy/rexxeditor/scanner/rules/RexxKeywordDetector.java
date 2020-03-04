package org.imperfectmommy.rexxeditor.scanner.rules;

import org.eclipse.jface.text.rules.IWordDetector;

public class RexxKeywordDetector implements IWordDetector {

    @Override
    public boolean isWordStart(char c) {
        return Character.isLetter(c);
    }

    @Override
    public boolean isWordPart(char c) {
        return (Character.isLetter(c) || Character.isDigit(c)) && c != '\'' && c != '"';
    }

}