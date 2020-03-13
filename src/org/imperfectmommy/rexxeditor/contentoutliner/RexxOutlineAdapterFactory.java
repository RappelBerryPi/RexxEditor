package org.imperfectmommy.rexxeditor.contentoutliner;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.imperfectmommy.rexxeditor.editors.RexxEditor;

public class RexxOutlineAdapterFactory implements IAdapterFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (IContentOutlinePage.class.equals(adapterType)) {
            RexxEditor editor = (RexxEditor) adaptableObject;
            return (T) new RexxContentOutlinePage(editor.getDocumentProvider(), editor);
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class<?>[] { IContentOutlinePage.class };
    }

}
