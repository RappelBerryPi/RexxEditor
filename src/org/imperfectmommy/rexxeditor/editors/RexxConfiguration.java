package org.imperfectmommy.rexxeditor.editors;

import java.util.ArrayList;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.imperfectmommy.rexxeditor.contentassist.RexxContentAssistProcessor;
import org.imperfectmommy.rexxeditor.contentoutliner.RexxContentElement;
import org.imperfectmommy.rexxeditor.preferences.IRexxPreferenceFields;
import org.imperfectmommy.rexxeditor.scanner.RexxLineScanner;

public class RexxConfiguration extends SourceViewerConfiguration {
    private RexxDoubleClickStrategy doubleClickStrategy;
    private RexxLineScanner         scanner;
    private ColorManager            colorManager;
    private RexxEditor              editor;

    public RexxConfiguration(ColorManager colorManager, RexxEditor editor) {
        this.editor       = editor;
        this.colorManager = colorManager;
    }

    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] { IDocument.DEFAULT_CONTENT_TYPE };
    }
    

    /*
    @Override
    public IInformationControlCreator getInformationControlCreator(ISourceViewer sourceViewer) {
        IInformationControlCreator creator = new IInformationControlCreator() {
            @Override
            public IInformationControl createInformationControl(Shell parent) {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }
    */

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant        assistant = new ContentAssistant();
        IContentAssistProcessor processor = new RexxContentAssistProcessor();
        assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setInformationControlCreator(this.getInformationControlCreator(sourceViewer));
        assistant.enableAutoActivation(true);
        assistant.enableColoredLabels(true);
        return assistant;
    }

    @Override
    public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer, String contentType) {
        if (this.doubleClickStrategy == null) {
            this.doubleClickStrategy = new RexxDoubleClickStrategy();
        }
        return this.doubleClickStrategy;
    }

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new RexxPresentationReconciler();
        DefaultDamagerRepairer dr         = new DefaultDamagerRepairer(this.getRexxScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
        return reconciler;
    }

    protected RexxLineScanner getRexxScanner() {
        if (this.scanner == null) {
            this.scanner = new RexxLineScanner(this.colorManager, this);
            this.scanner.setDefaultReturnToken(new Token(new TextAttribute(this.colorManager.getColor(IRexxPreferenceFields.PREF_DEFAULT_COLOR))));
        }
        return this.scanner;
    }

    public void updateList(ArrayList<RexxContentElement> update) {
        this.editor.update(update);
    }

}