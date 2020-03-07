package org.imperfectmommy.rexxeditor.contentassist;

import org.eclipse.jface.text.templates.TemplateVariable;

public class TemplateVariableWithDescription {
    private TemplateVariable variable;
    private String           variableDescription;

    public TemplateVariableWithDescription(TemplateVariable variable, String variableDescription) {
        this.variable            = variable;
        this.variableDescription = variableDescription;
    }

    public TemplateVariable getVariable() {
        return this.variable;
    }

    public String getVariableDescription() {
        return this.variableDescription;
    }

}
