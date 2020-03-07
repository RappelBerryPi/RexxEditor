package org.imperfectmommy.rexxeditor.contentassist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateVariable;

public class RexxFunctionProposalData {

    public final static String baseHTMLTemplate = "<html><head>" + "<style>" + "* { font-size: 12px; }" + "html {overflow:scroll;}" + "body { font-family: \"Lucida Console\", Monaco, monospace;}"
            + "th { text-align: left;}" + "p span { font-weight: bold; }" + "p { margin-bottom: 0px;}" + "</style>" + "</head><body>";

    public final static String baseHTMLTemplateEnd = "<br /></body></html>";

    private String                                functionName;
    private String                                functionPattern;
    private List<TemplateVariableWithDescription> variables;
    private String                                returnValueDescription;
    private List<String>                          examples;
    private Template                              template;

    public RexxFunctionProposalData(String functionPattern, List<TemplateVariableWithDescription> variables, String returnValueDescription, List<String> examples, Template template) {
        this.functionPattern        = functionPattern;
        this.variables              = variables;
        this.returnValueDescription = returnValueDescription;
        this.examples               = examples;
        this.template               = template;
        this.functionName           = functionPattern.split("(\\(| )")[0];
    }

    @Override
    public String toString() {
        String returnString = RexxFunctionProposalData.baseHTMLTemplate + "<p>" + this.getFunctionName() + "(<span>";
        returnString += this.variables.stream().map(var -> var.getVariable().getDefaultValue()).collect(Collectors.joining("</span>,<span>")) + "</span>)";
        returnString += "<p><span>Variables:</span></p><table>";

        for (TemplateVariableWithDescription variable : this.variables) {
            returnString += "<tr><th>" + variable.getVariable().getDefaultValue() + "</th><td>-</td><td>" + variable.getVariableDescription() + "</td></tr>";
        }

        returnString += "</table><p><span>Returns:</span><br />";
        returnString += this.getReturnValueDescription() + "</p>";

        for (int i = 0; i < this.examples.size(); i++) {
            returnString += "<p><span>Example " + (i + 1) + ":</span><br />";
            returnString += this.examples.get(i) + "</p>";
        }
        returnString += RexxFunctionProposalData.baseHTMLTemplateEnd;
        return returnString;
    }

    public static RexxFunctionProposalData getRexxFunctionProposalData(String functionPattern) {
        return functionMap.get(functionPattern);
    }

    public String getFunctionName() {
        return this.functionName;
    }
    public String getFunctionPattern() {
        return this.functionPattern;
    }

    public List<TemplateVariableWithDescription> getVariables() {
        return this.variables;
    }

    public String getReturnValueDescription() {
        return this.returnValueDescription;
    }

    public List<String> getExamples() {
        return this.examples;
    }

    public Template getTemplate() {
        return this.template;
    }

    private static Map<String, RexxFunctionProposalData> functionMap = new HashMap<>();

    static {
        String                                functionPattern        = "POS(Needle,Haystack)";
        List<TemplateVariableWithDescription> variables              = new ArrayList<>();
        String                                returnValueDescription = "The value of the position that needle is found in haystack index 1 based, and 0 if it is not found.";
        List<String>                          examples               = new ArrayList<>();
        examples.add("Pos(\"A\",\"ABCDEFG\") ===> 1");
        examples.add("Pos(\"Doe\",\"John Doe\") ===> 6");
        examples.add("Pos(\"No\",\"Yes\") ===> 0");
        variables.add(new TemplateVariableWithDescription(new TemplateVariable("String", "Needle", new int[] { 4 }), "The string to search for"));
        variables.add(new TemplateVariableWithDescription(new TemplateVariable("String", "Haystack", new int[] { 11 }), "The string to search through."));
        Template template = new Template(functionPattern + ": Int", "*** ADDITIONAL INFO ***", "org.imperfectmommy.rexxeditor.editors.RexxEditor", functionPattern, false);
        functionMap.put(functionPattern, new RexxFunctionProposalData(functionPattern, variables, returnValueDescription, examples, template));

        functionPattern = "POS(Needle,Haystack,Start)";
        variables = new ArrayList<>();
        variables.add(new TemplateVariableWithDescription(new TemplateVariable("String", "Needle", new int[] { 4 }), "The string to search for"));
        variables.add(new TemplateVariableWithDescription(new TemplateVariable("String", "Haystack", new int[] { 11 }), "The string to search through."));
        variables.add(new TemplateVariableWithDescription(new TemplateVariable("String", "Start", new int[] { 20 }), "The Starting Position."));
        examples = new ArrayList<>();
        examples.add("Pos(\"A\",\"ABCDEFGA\",2) ===> 8");
        examples.add("Pos(\"Doe\",\"John Doe\",1) ===> 6");
        examples.add("Pos(\"Yes\",\"Yes\",2) ===> 0");
        template = new Template(functionPattern + ": Int", "*** ADDITIONAL INFO ***", "org.imperfectmommy.rexxeditor.editors.RexxEditor", functionPattern, false);
        functionMap.put(functionPattern, new RexxFunctionProposalData(functionPattern, variables, returnValueDescription, examples, template));

    }
}
