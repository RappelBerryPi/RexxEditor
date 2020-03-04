package org.imperfectmommy.rexxeditor.scanner.rules;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class RexxWhitespaceDetector implements IWhitespaceDetector {
	public boolean isWhitespace( char c) {
		boolean bool = false;
		if (c == ' ' || c == '\t') {
			bool = true;
		}
		return bool;
	}
}
