package org.imperfectmommy.rexxeditor.scanner;

import java.util.ArrayList;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.imperfectmommy.rexxeditor.contentoutliner.RexxContentCategory;
import org.imperfectmommy.rexxeditor.contentoutliner.RexxContentElement;

public class RexxVariableMethodContainer {
    ArrayList<RexxContentElement> fVariableList;

    IDocument fDocument;

    public RexxVariableMethodContainer() {
        fVariableList = new ArrayList<>();
    }

    public ArrayList<RexxContentElement> getVariableList() {
        return fVariableList;
    }

    public void update(IDocument document) {
        fVariableList = new ArrayList<RexxContentElement>();
        fDocument     = document;
    }

    public void addVariable(int offset, int length) {
        try {
            String variable = fDocument.get(offset, length).toUpperCase();
            fVariableList.add(new RexxContentElement(variable, offset, length, RexxContentCategory.variable));
        } catch (BadLocationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addSubroutine(int offset, int length) {
        try {
            String method = fDocument.get(offset, length).toUpperCase();
            fVariableList.add(new RexxContentElement(method, offset, length, RexxContentCategory.subroutine));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public boolean isVariable(int offset, int length) {
        try {
            final String variable = fDocument.get(offset, length).toUpperCase();
            return fVariableList.stream().anyMatch(e -> e.getCategory() == RexxContentCategory.variable && e.getName().compareToIgnoreCase(variable) == 0);
        } catch (BadLocationException e) {
            e.printStackTrace();
            return false;
        }
    }

}
