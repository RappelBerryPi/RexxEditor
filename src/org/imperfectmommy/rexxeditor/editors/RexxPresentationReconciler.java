package org.imperfectmommy.rexxeditor.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.swt.custom.StyleRange;

public class RexxPresentationReconciler extends PresentationReconciler {
    private Map<String, IPresentationRepairer> fRepairers;

    
    public IPresentationRepairer getRepairer(String contentType) {
        if (fRepairers == null)
            return null;
        return fRepairers.get(contentType);
    }
    
    
    protected void setDocumentToRepairers(IDocument document) {
        if (fRepairers != null) {
            Iterator<IPresentationRepairer> e= fRepairers.values().iterator();
            while (e.hasNext()) {
                IPresentationRepairer repairer=  e.next();
                repairer.setDocument(document);
            }
        }
    }
    
    public void setRepairer(IPresentationRepairer repairer, String contentType) {

        if (fRepairers == null)
            fRepairers= new HashMap<String, IPresentationRepairer>();

        if (repairer == null)
            fRepairers.remove(contentType);
        else
            fRepairers.put(contentType, repairer);
    }
    
    protected TextPresentation createPresentation(IRegion damage, IDocument document) {
        try {
            if (fRepairers == null || fRepairers.isEmpty()) {
                TextPresentation presentation= new TextPresentation(damage, 1);
                presentation.setDefaultStyleRange(new StyleRange(damage.getOffset(), damage.getLength(), null, null));
                return presentation;
            }


            
            TextPresentation presentation= new TextPresentation(damage, 1000);

            ITypedRegion[] partitioning= TextUtilities.computePartitioning(document, getDocumentPartitioning(), 0, document.getLength(), false);
            for (int i= 0; i < partitioning.length; i++) {
                ITypedRegion r= partitioning[i];
                IPresentationRepairer repairer= getRepairer(r.getType());
                if (repairer != null)
                    repairer.createPresentation(presentation, r);
            }

            return presentation;

        } catch (BadLocationException x) {
        }

        return null;
    }
}