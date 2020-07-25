package org.imperfectmommy.rexxeditor.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.imperfectmommy.rexxeditor.scanner.RexxTokenList;

public class RexxFunctionContentAssistProcessor implements IContentAssistProcessor {

    IContextInformationValidator validator;
    ITokenScanner scanner;

    public RexxFunctionContentAssistProcessor(ITokenScanner scanner) {
        this.scanner = scanner;
        this.validator = new RexxContextInformationValidator();
    }

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        scanner.setRange(viewer.getDocument(), 0, viewer.getDocument().getLength());
        List<ICompletionProposal> returnList = new ArrayList<>();
        if (scanner.nextToken() == new RexxTokenList().variable) {
            returnList.add(new ICompletionProposal() {
                
                @Override
                public Point getSelection(IDocument document) {
                    // TODO Auto-generated method stub
                    return null;
                }
                
                @Override
                public Image getImage() {
                    return null;
                }
                
                @Override
                public String getDisplayString() {
                    return "Display String";
                }
                
                @Override
                public IContextInformation getContextInformation() {
                    return new IContextInformation() {
                        
                        @Override
                        public String getInformationDisplayString() {
                            return "Context Information Information Display String";
                        }
                        
                        @Override
                        public Image getImage() {
                            return null;
                        }
                        
                        @Override
                        public String getContextDisplayString() {
                            return "Context Information Context Display String";
                        }
                    };
                }
                
                @Override
                public String getAdditionalProposalInfo() {
                    return "Additional Proposal Info";
                }
                
                @Override
                public void apply(IDocument document) {
                    //TODO
                    System.out.print("Help. in apply statement");
                }
            });
        }
        ICompletionProposal[] returnCompletionProposals = new ICompletionProposal[0];
        return returnList.toArray(returnCompletionProposals);
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
