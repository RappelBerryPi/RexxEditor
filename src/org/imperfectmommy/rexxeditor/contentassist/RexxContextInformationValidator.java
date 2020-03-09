package org.imperfectmommy.rexxeditor.contentassist;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class RexxContextInformationValidator implements IContextInformationValidator {

    protected int initialOffset;

    @Override
    public void install(IContextInformation info, ITextViewer viewer, int offset) {
        initialOffset = offset;
    }

    /* Only valid if in 5 characters */
    @Override
    public boolean isContextInformationValid(int offset) {
        return Math.abs(offset - initialOffset) < 5;
    }

}