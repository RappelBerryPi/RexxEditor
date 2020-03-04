package org.imperfectmommy.rexxeditor.editors;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.source.AnnotationRulerColumn;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.imperfectmommy.rexxeditor.Activator;
import org.imperfectmommy.rexxeditor.contentoutliner.RexxContentOutlinePage;

public class RexxEditor extends TextEditor {
	private RexxContentOutlinePage fOutlinePage;
	private ColorManager colorManager;

	public RexxEditor() {
		super();
		Activator plugin = Activator.getActivator();
		colorManager = new ColorManager(plugin.getPreferenceStore());
		setSourceViewerConfiguration(new RexxConfiguration(colorManager, this));
		setPreferenceStore(plugin.getPreferenceStore());
	}

	public void dispose() {
		colorManager.dispose();
		if (fOutlinePage != null)
			fOutlinePage.setInput(null);
		super.dispose();
	}

	protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
		colorManager.handlePreferenceStoreChanged(event);
		super.handlePreferenceStoreChanged(event);
	}

	@SuppressWarnings("unchecked")
    @Override
	public <T> T getAdapter(Class<T> adapter) {
		if (IContentOutlinePage.class.equals(adapter)) {
			if (fOutlinePage == null) {
				fOutlinePage = new RexxContentOutlinePage(getDocumentProvider(), this);
				if (getEditorInput() != null)
					fOutlinePage.setInput(getEditorInput());
			}
			return (T) fOutlinePage;
		}
		return super.getAdapter(adapter);
	}


	public void update(ArrayList updateList) {

		if (fOutlinePage != null) {
			fOutlinePage.update(updateList);
		}
	}

	public void doSetInput(IEditorInput input) throws CoreException {
		super.doSetInput(input);

		if (fOutlinePage != null)
			fOutlinePage.setInput(input);
	}
	
	protected IVerticalRuler createVerticalRuler() {
	    CompositeRuler ruler = new CompositeRuler();
	    ruler.addDecorator(0, new AnnotationRulerColumn(VERTICAL_RULER_WIDTH));
	    ruler.addDecorator(1, createLineNumberRulerColumn());
	    return ruler;
	}
	

}
