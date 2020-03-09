package org.imperfectmommy.rexxeditor.contentassist;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
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
        IDocument document = viewer.getDocument();
        FindReplaceDocumentAdapter adapter = new FindReplaceDocumentAdapter(document);
        String foundString = "";
        try {
            IRegion region2 = adapter.find(offset - 1,"\\s", false, false, false, true);
            foundString = document.get(region2.getOffset() + 1, offset - region2.getOffset() - 1);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
        final String finalFoundString = foundString;
        
        List<RexxCompletionProposal> proposals = new ArrayList<>();
        String[] functionNames= RexxFunctionProposalData.getFunctionMap().keySet().stream().filter(k -> k.contains(finalFoundString)).sorted().toArray(String[]::new);
        for (String functionName: functionNames) {
            proposals.add(buildCompletionProposalFromKey(functionName, viewer, offset));
        }
        return proposals.toArray(ICompletionProposal[]::new);
    }

    private RexxCompletionProposal buildCompletionProposalFromKey(String string, ITextViewer viewer, int offset) {
        RexxFunctionProposalData data =  RexxFunctionProposalData.getFunctionMap().get(string);
        Template template = data.getTemplate();
        TemplateContextType type = new TemplateContextType();
        type.addResolver(new TemplateVariableResolver());
        TemplateContext context = new TemplateContext(type) {
            
            @Override
            public TemplateBuffer evaluate(Template template) throws BadLocationException, TemplateException {
                TemplateVariable[] templateVariables = new  TemplateVariable[0];
                if (data.getVariables() != null) {
                    templateVariables = Arrays.stream(data.getVariables()).map(var -> var.getVariable()).toArray(TemplateVariable[]::new);
                }
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
        return "ADP".toCharArray();
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return "ADP".toCharArray();
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
