package org.imperfectmommy.rexxeditor.contentassist;

import org.eclipse.jface.internal.text.html.BrowserInformationControl;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class RexxCompletionProposal extends TemplateProposal {
    
    private final static String baseHTMLTemplate = "<html><head>" +
            "<style>" +
            "* { font-size: 12px; }" +
            "html {overflow:scroll;}" +
            "body { font-family: \"Lucida Console\", Monaco, monospace;}" +
            "th { text-align: left;}" +
            "p span { font-weight: bold; }" +
            "p { margin-bottom: 0px;}" +
            "</style>" +
            "</head><body>";
    /*
            "table, th{ margin-left: 0px;}" +
            "ul { list-style: none;}" +
            */

    public RexxCompletionProposal(Template template, TemplateContext context, IRegion region, Image image, int relevance) {
        super(template, context, region, image, relevance);
        this.setInformationControlCreator(new IInformationControlCreator() {
            
            @Override
            public IInformationControl createInformationControl(Shell parent) {
                return new BrowserInformationControl(parent, "Times New Roman", false);
            }
        });
        /*
         * Leaving so that way in the future if BrowserInformationControl becomes unavailbile then
         * This data can be retrieved and used.
         * */
        /*
        this.setInformationControlCreator(new IInformationControlCreator() {
            public IInformationControl createInformationControl(final Shell parent) {
                return new DefaultInformationControl(parent, true) {
                    @Override
                    public void setInformation(String content) {
                        super.setInformation(content);
                    }
                };
            }
        });
        */
    }
    
    @Override
    public String getAdditionalProposalInfo() {
        /* TODO Create a lookup table for an input and map that to the proposals.
         * have information for the variables, returns and one example. ... multiple examples???
         */
        String additionalInfoString = super.getAdditionalProposalInfo();
        return RexxCompletionProposal.baseHTMLTemplate + "<p>POS(<span>Needle</span>,<span>Haystack</span>)</p>" +
                "<p><span>Variables:</span></p>" +
                "<table>" +
                "<tr><th>Needle</th><td>-</td><td>the string to search for</td></tr>" +
                "<tr><th>Haystack</th><td>-</td><td>the string to search through</td></tr>" +
                "</table>" +
                "<p><span>Returns:</span>" +
                "<br />" +
                "The value of the position that needle is found in haystack index 1 based, and 0 if it is not found." +
                "</p>" +
                "<p><span>Example 1:</span>" +
                "<br />" +
                "Pos(\"A\",\"ABCDEFG\") ===> 1</p>" +
                "<p><span>Example 2:</span>" +
                "<br />" +
                "Pos(\"Doe\",\"John Doe\") ===> 6</p>" +
                "<p><span>Example 3:</span>" +
                "<br />" +
                "Pos(\"No\",\"Yes\") ===> 0</p>" +
                "<br >" +
                "<p> actual additional info: " + additionalInfoString + "</p>" +
                "</body></html>";
    }
    
}