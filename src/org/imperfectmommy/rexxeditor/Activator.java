package org.imperfectmommy.rexxeditor;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.imperfectmommy.rexxeditor.editors.IRexxColorConstants;
import org.imperfectmommy.rexxeditor.preferences.IRexxPreferenceFields;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class Activator extends AbstractUIPlugin {

    // The shared instance.
    private static Activator plugin;

    /**
     * Returns the shared instance.
     *
     * @return the shared instance.
     */
    public static Activator getActivator() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in relative
     * path.
     *
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
    	return imageDescriptorFromPlugin("RexxEditor", path);
    }

    /**
     * The constructor.
     */
    public Activator() {
        super();
        plugin = this;
    }

    @Override
    protected void initializeDefaultPreferences(IPreferenceStore store) {
        // Colors for syntax highlighting.
        store.setDefault(IRexxPreferenceFields.PREF_REXX_COMMENT, StringConverter.asString(IRexxColorConstants.REXX_COMMENT));
        store.setDefault(IRexxPreferenceFields.PREF_REXX_SYMBOL, StringConverter.asString(IRexxColorConstants.REXX_SYMBOL));
        store.setDefault(IRexxPreferenceFields.PREF_DEFAULT_COLOR, StringConverter.asString(IRexxColorConstants.DEFAULT));
        store.setDefault(IRexxPreferenceFields.PREF_VAR_COLOR, StringConverter.asString(IRexxColorConstants.REXX_VAR));
        store.setDefault(IRexxPreferenceFields.PREF_PROC_INSTR, StringConverter.asString(IRexxColorConstants.PROC_INSTR));
        store.setDefault(IRexxPreferenceFields.PREF_STRING_COLOR, StringConverter.asString(IRexxColorConstants.REXX_STR));
        store.setDefault(IRexxPreferenceFields.PREF_REXX_PATH, "");
        store.setValue(AbstractTextEditor.PREFERENCE_USE_CUSTOM_CARETS, true);
    }

    @Override
    protected void initializeImageRegistry(ImageRegistry reg) {
        try {
            reg.put("method", Activator.getImageDescriptor("icons/jmeth_obj.gif"));
            reg.put("class", Activator.getImageDescriptor("icons/classes.gif"));
            reg.put("requires", Activator.getImageDescriptor("icons/jload_obj.gif"));
            reg.put("variable", Activator.getImageDescriptor("icons/brkpi_obj.gif"));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called upon plug-in activation
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /**
     * This method is called when the plug-in is stopped
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        super.stop(context);
        plugin = null;
    }

}
