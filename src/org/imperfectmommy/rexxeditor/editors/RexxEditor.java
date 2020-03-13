package org.imperfectmommy.rexxeditor.editors;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.editors.text.TextEditor;
import org.imperfectmommy.rexxeditor.Activator;

public class RexxEditor extends TextEditor {

    public RexxEditor() {
        super();
        this.setSourceViewerConfiguration(new RexxConfiguration());

        Activator.getActivator().getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
            
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                getSourceViewer().invalidateTextPresentation();
                handlePreferenceStoreChanged(event);
            }
        });
    }

}