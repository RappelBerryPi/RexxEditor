package org.imperfectmommy.rexxeditor.editors;

import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.imperfectmommy.rexxeditor.Activator;
import org.imperfectmommy.rexxeditor.contentassist.RexxContentAssistProcessor;
import org.imperfectmommy.rexxeditor.contentassist.RexxFunctionContentAssistProcessor;
import org.imperfectmommy.rexxeditor.preferences.IRexxPreferenceFields;
import org.imperfectmommy.rexxeditor.scanner.RexxLineScanner;

public class RexxConfiguration extends SourceViewerConfiguration {
    private RexxDoubleClickStrategy doubleClickStrategy;
    private RexxLineScanner         scanner;

    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] { IDocument.DEFAULT_CONTENT_TYPE };
    }

    /*
     * @Override public IInformationControlCreator
     * getInformationControlCreator(ISourceViewer sourceViewer) {
     * IInformationControlCreator creator = new IInformationControlCreator() {
     * @Override public IInformationControl createInformationControl(Shell parent) {
     * // TODO Auto-generated method stub return null; } }; }
     */

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant        assistant = new ContentAssistant();
        IContentAssistProcessor processor = new RexxContentAssistProcessor();
        IContentAssistProcessor processor2 = new RexxFunctionContentAssistProcessor(this.getScanner());
        assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
        assistant.addContentAssistProcessor(processor2, "Functions");
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
        DefaultDamagerRepairer dr         = new DefaultDamagerRepairer(this.getScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
        return reconciler;
    }

    public ITokenScanner getScanner() {
        if (this.scanner == null) {
            this.scanner = new RexxLineScanner(this);
            String rgbString = Activator.getActivator().getPreferenceStore().getString(IRexxPreferenceFields.PREF_DEFAULT_COLOR);
            RGB    rgb       = StringConverter.asRGB(rgbString);
            Color  color     = new Color(Display.getCurrent(), rgb);
            Token  token     = new Token(new TextAttribute(color));
            this.scanner.setDefaultReturnToken(token);
        }
        return this.scanner;
    }
}