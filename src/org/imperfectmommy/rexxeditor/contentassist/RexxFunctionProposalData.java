package org.imperfectmommy.rexxeditor.contentassist;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jface.text.templates.Template;
import org.osgi.framework.Bundle;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RexxFunctionProposalData {

    private String                            pattern;
    private ReturnValue                       returnValue;
    private TemplateVariableWithDescription[] variables;
    private String[]                          examples;
    private String                            additionalInfo;
    private String                            functionName;
    private Template                          template;

    public String getFunctionName() {
        return functionName;
    }

    public Template getTemplate() {
        if (template == null) {
            this.template =
                    new Template(this.pattern + ": " + this.returnValue.getType(), "builtin: " + this.getAdditionalInfo(), "org.imperfectmommy.rexxeditor.editors.RexxEditor", this.pattern, false);
        }
        return template;
    }

    public String getPattern() {
        return pattern;
    }

    /* Also sets Function Name */
    public void setPattern(String pattern) {
        this.functionName = pattern.split("(\\(| )")[0];
        this.pattern      = pattern;
    }

    public ReturnValue getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(ReturnValue returnValue) {
        this.returnValue = returnValue;
    }

    public TemplateVariableWithDescription[] getVariables() {
        return variables;
    }

    public void setVariables(TemplateVariableWithDescription[] variables) {
        this.variables = variables;
    }

    public String[] getExamples() {
        return examples;
    }

    public void setExamples(String[] examples) {
        this.examples = examples;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public static Map<String, RexxFunctionProposalData> getFunctionMap() {
        if (functionMap.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
                Bundle bundle = org.eclipse.core.runtime.Platform.getBundle("RexxEditor");
                URL    fUrl   = bundle.getEntry("resources/configuration/FunctionDefinitions.json");
                if (mapper.canSerialize(RexxFunctionProposalData.class)) {
                    RexxFunctionProposalData[] data = mapper.readValue(fUrl.openConnection().getInputStream(), RexxFunctionProposalData[].class);
                    for (RexxFunctionProposalData rexxFunctionProposalData : data) {
                        functionMap.putIfAbsent(rexxFunctionProposalData.pattern, rexxFunctionProposalData);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return functionMap;
    }

    public final static String baseHTMLTemplate = "<html><head>" + "<style>" + "* { font-size: 12px; }" + "html {overflow:scroll;}" + "body { font-family: \"Lucida Console\", Monaco, monospace;}"
            + "th { text-align: left;}" + "p span { font-weight: bold; }" + "p { margin-bottom: 0px;}" + "</style>" + "</head><body>";

    public final static String baseHTMLTemplateEnd = "<br /></body></html>";

    @Override
    public String toString() {
        String returnString = RexxFunctionProposalData.baseHTMLTemplate + "<p>" + this.getFunctionName() + "(";
        if (this.variables != null) {
            returnString += "<span>" + Arrays.stream(this.variables).map(var -> var.getVariable().getDefaultValue()).collect(Collectors.joining("</span>,<span>")) + "</span>";
        }
        returnString += ")";
        if (this.variables != null) {
            returnString += "<p><span>Variables:</span></p><table>";
            for (TemplateVariableWithDescription variable : this.variables) {
                returnString += "<tr><th>" + variable.getVariable().getDefaultValue() + "</th><td>-</td><td>" + variable.getVariableDescription() + "</td></tr>";
            }
            returnString += "</table>";
        }
        returnString += "<p><span>Returns:</span><br />";
        returnString += this.getReturnValue().getDescription() + "</p>";
        for (int i = 0; i < this.examples.length; i++) {
            returnString += "<p><span>Example " + (i + 1) + ":</span><br />";
            returnString += this.examples[i] + "</p>";
        }
        returnString += RexxFunctionProposalData.baseHTMLTemplateEnd;
        return returnString;
    }

    public static RexxFunctionProposalData getRexxFunctionProposalData(String functionPattern) {
        return functionMap.get(functionPattern);
    }

    private static Map<String, RexxFunctionProposalData> functionMap = new HashMap<>();
}