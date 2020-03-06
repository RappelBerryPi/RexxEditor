package org.imperfectmommy.rexxeditor.contentoutliner;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.imperfectmommy.rexxeditor.Activator;

/**
 * A label provider for Rexx content elements.
 * <p>
 * This class provides a Rexx icon for the content outliner classes.
 * </p>
 */
public class RexxLabelProvider extends LabelProvider {
    /**
     * Returns an icon from the image registry.
     * 
     * @param the element
     */
    public Image getImage(RexxContentElement element) {
        if (element != null) {
            return Activator.getActivator().getImageRegistry().get(element.getCategory().toString());
        }
        return null;
    }

}
