package org.imperfectmommy.rexxeditor.scanner;

import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.imperfectmommy.rexxeditor.Activator;

public class RexxTokenListToken extends Token implements IToken {

    public RexxTokenListToken(Object data) {
        super(data);
    }

    @Override
    public Object getData() {
        String dataString = (String) super.getData();
        return getAttributeFromColorName(dataString);
    }

    private TextAttribute getAttributeFromColorName(String iRexxPreferenceField) {
        Color color =  new Color(Display.getCurrent(), StringConverter.asRGB(Activator.getActivator().getPreferenceStore().getString(iRexxPreferenceField)));
        return new TextAttribute(color);
    }

}
