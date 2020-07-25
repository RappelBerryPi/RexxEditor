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
import org.imperfectmommy.rexxeditor.Activator;
import org.imperfectmommy.rexxeditor.scanner.RexxLineScanner;
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
        IRegion region2 = null;
        try {
            region2 = adapter.find(offset - 1,"\\s", false, false, false, true);
            region2 = new Region(region2.getOffset() + 1, region2.getLength());
            foundString = document.get(region2.getOffset(), offset - region2.getOffset());
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
        final String finalFoundString = foundString.toUpperCase();
        
        List<RexxCompletionProposal> proposals = new ArrayList<>();
        String[] functionNames= RexxFunctionProposalData.getFunctionMap().keySet().stream().filter(k -> k.startsWith(finalFoundString)).sorted().toArray(String[]::new);
        TemplateContextType type = new TemplateContextType("TemplateVariableContext");
        type.addResolver(new TemplateVariableResolver());
        Image image = null;
        try {
            Bundle bundle = org.eclipse.core.runtime.Platform.getBundle("RexxEditor");
            URL fUrl = bundle.getEntry("icons/function.gif");
            image = new Image(null, fUrl.openConnection().getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String functionName: functionNames) {
            proposals.add(buildCompletionProposalFromKey(functionName, region2, type, image));
        }
        ICompletionProposal[] completionProposals = new ICompletionProposal[0];
        return proposals.toArray(completionProposals);
    }

    private RexxCompletionProposal buildCompletionProposalFromKey(String string, IRegion region, TemplateContextType type, Image image) {
        RexxFunctionProposalData data =  RexxFunctionProposalData.getFunctionMap().get(string);
        Template template = data.getTemplate();
        TemplateContext context = new TemplateContext(type) {
            
            @Override
            public TemplateBuffer evaluate(Template template) throws BadLocationException, TemplateException {
                TemplateVariable[] templateVariables = new TemplateVariable[0];
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
        return new RexxCompletionProposal(template, context, region, image, 0); 
    }

    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        ContextInformation information = new ContextInformation("display string", "information string");
        return new IContextInformation[] { information };
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
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
