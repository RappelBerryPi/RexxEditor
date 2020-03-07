package org.imperfectmommy.rexxeditor.contentassist;

import org.eclipse.jface.internal.text.html.BrowserInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension6;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class RexxCompletionProposal extends TemplateProposal implements ICompletionProposalExtension6 {
    
    public RexxCompletionProposal(Template template, TemplateContext context, IRegion region, Image image, int relevance) {
        super(template, context, region, image, relevance);
        this.setInformationControlCreator(new IInformationControlCreator() {
            
            @Override
            public IInformationControl createInformationControl(Shell parent) {
                return new BrowserInformationControl(parent, "Times New Roman", false);
            }
        });
    }

    @Override
    public String getAdditionalProposalInfo() {
        return RexxFunctionProposalData.getRexxFunctionProposalData(this.getTemplate().getPattern()).toString();
    }

    @Override
    public StyledString getStyledDisplayString() {
        String string = getDisplayString();
        String[] strings = string.split("-");
        StyledString string2 = new StyledString(strings[0] + " - ");
        string2.append(new StyledString(strings[1],StyledString.QUALIFIER_STYLER));
        return string2;
    }
    
}