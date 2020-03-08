package org.imperfectmommy.rexxeditor.contentassist;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public class RexxContentAssistProcessor implements IContentAssistProcessor {

    IContextInformationValidator validator;

    public RexxContentAssistProcessor() {
        this.validator = new RexxContextInformationValidator();
    }

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        //TODO: Finish creating mapping function for a function to a the display information and stuff....
        RexxCompletionProposal proposal = buildCompletionProposalFromKey("POS(Needle,Haystack)", viewer, offset);
        RexxCompletionProposal proposal2 = buildCompletionProposalFromKey("POS(Needle,Haystack,Start)", viewer, offset);
        return new ICompletionProposal[] { proposal, proposal2 };
    }

    private RexxCompletionProposal buildCompletionProposalFromKey(String string, ITextViewer viewer, int offset) {
       RexxFunctionProposalData data = RexxFunctionProposalData.getRexxFunctionProposalData(string);
        Template template = data.getTemplate();
        TemplateContextType type = new TemplateContextType();
        type.addResolver(new TemplateVariableResolver());
        TemplateContext context = new TemplateContext(type) {
            
            @Override
            public TemplateBuffer evaluate(Template template) throws BadLocationException, TemplateException {
                TemplateVariable[] templateVariables = Arrays.stream(data.getVariables()).map(var -> var.getVariable()).toArray(TemplateVariable[]::new);
                return new TemplateBuffer(template.getPattern(),templateVariables);
            }
            
            @Override
            public boolean canEvaluate(Template template) {
                return true;
            }
        };
        IRegion region = new Region(offset - 1 , 1);
        Image image = null;
        try {
            Bundle bundle = org.eclipse.core.runtime.Platform.getBundle("RexxEditor");
            URL fUrl = bundle.getEntry("icons/function.gif");
            image = new Image(null, fUrl.openConnection().getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new RexxCompletionProposal(template, context, region, image, 0); 
    }

    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        ContextInformation information = new ContextInformation("display string", "information string");
        return new IContextInformation[] { information };
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] { 'p' };
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return new char[] { 'p' };
    }

    @Override
    public String getErrorMessage() {
        return "Error processing Content Assist";
    }

    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return validator;
    }

}
