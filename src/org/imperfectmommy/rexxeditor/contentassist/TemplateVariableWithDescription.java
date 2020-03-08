package org.imperfectmommy.rexxeditor.contentassist;

import org.eclipse.jface.text.templates.TemplateVariable;

public class TemplateVariableWithDescription {
    private String           type;
    private String           defaultValue;
    private int[]            offsets;
    private TemplateVariable variable;
    private String           variableDescription;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }

    public TemplateVariable getVariable() {
        if (this.variable == null) {
            this.variable = new TemplateVariable(this.type, this.defaultValue, this.offsets);
        }
        return variable;
    }

    public String getVariableDescription() {
        return variableDescription;
    }

    public void setVariableDescription(String variableDescription) {
        this.variableDescription = variableDescription;
    }

}