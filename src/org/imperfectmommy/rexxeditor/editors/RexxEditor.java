package org.imperfectmommy.rexxeditor.editors;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.imperfectmommy.rexxeditor.Activator;
import org.imperfectmommy.rexxeditor.contentoutliner.RexxContentElement;
import org.imperfectmommy.rexxeditor.contentoutliner.RexxContentOutlinePage;
import org.imperfectmommy.rexxeditor.preferences.RexxPreferencePage;

public class RexxEditor extends TextEditor {
    private RexxContentOutlinePage fOutlinePage;
    private ColorManager           colorManager;

    public RexxEditor() {
        super();
        Activator plugin = Activator.getActivator();
        this.colorManager = new ColorManager(plugin.getPreferenceStore());
        this.setSourceViewerConfiguration(new RexxConfiguration(this.colorManager, this));
        // setPreferenceStore(plugin.getPreferenceStore());
    }

    @Override
    public void dispose() {
        this.colorManager.dispose();
        if (this.fOutlinePage != null) {
            this.fOutlinePage.setInput(null);
        }
        super.dispose();
    }

    @Override
    public void doSetInput(IEditorInput input) throws CoreException {
        super.doSetInput(input);

        if (this.fOutlinePage != null) {
            this.fOutlinePage.setInput(input);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (IContentOutlinePage.class.equals(adapter)) {
            if (this.fOutlinePage == null) {
                this.fOutlinePage = new RexxContentOutlinePage(this.getDocumentProvider(), this);
                if (this.getEditorInput() != null) {
                    this.fOutlinePage.setInput(this.getEditorInput());
                }
            }
            return (T) this.fOutlinePage;
        }
        return super.getAdapter(adapter);
    }

    @Override
    protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
        super.handlePreferenceStoreChanged(event);
    }

    public void update(ArrayList<RexxContentElement> updateList) {
        if (this.fOutlinePage != null) {
            this.fOutlinePage.update(updateList);
        }
    }

    /*
     * protected IVerticalRuler createVerticalRuler() { CompositeRuler ruler = new
     * CompositeRuler(); ruler.addDecorator(0, new
     * AnnotationRulerColumn(VERTICAL_RULER_WIDTH)); ruler.addDecorator(1,
     * createLineNumberRulerColumn()); return ruler; }
     */

}