package org.imperfectmommy.rexxeditor.contentassist;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.CompletionProposal;
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
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;
import org.eclipse.swt.graphics.Image;

public class RexxContentAssistProcessor implements IContentAssistProcessor {

    IContextInformationValidator validator;

    public RexxContentAssistProcessor() {
        this.validator = new RexxContextInformationValidator();
    }

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        //TODO: Finish creating mapping function for a function to a the display information and stuff....
        Template template = new Template("POS(Needle,Haystack)","***INFO***","no-context","POS(Needle,Haystack)",false);
        TemplateContextType type = new TemplateContextType();
        TemplateVariableResolver resolver;
        resolver = new TemplateVariableResolver();
        resolver.setType("String");
        resolver.setDescription("hello World!");
        type.addResolver(resolver);
        TemplateContext context = new TemplateContext(type) {
            
            @Override
            public TemplateBuffer evaluate(Template template) throws BadLocationException, TemplateException {
                TemplateVariable[] varsTemplateVariables = new TemplateVariable[2];
                varsTemplateVariables[0] = new TemplateVariable("String", "needle", new int[] {4});
                varsTemplateVariables[1] = new TemplateVariable("String", "haystack", new int[] {11});
                //how to add html conten......
                return new TemplateBuffer(template.getPattern(),varsTemplateVariables);
            }
            
            @Override
            public boolean canEvaluate(Template template) {
                return true;
            }
        };
        IRegion region = new Region(offset - 1 , 1);
        Image image = null;

        RexxCompletionProposal proposal    = new RexxCompletionProposal(template, context, region, image,0); 
        return new ICompletionProposal[] { proposal };
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
