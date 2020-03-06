package org.imperfectmommy.rexxeditor.scanner.tokenRules;

import org.eclipse.jface.text.TextAttribute;
import org.imperfectmommy.rexxeditor.scanner.RexxLine;
import org.imperfectmommy.rexxeditor.scanner.RexxToken;
import org.imperfectmommy.rexxeditor.scanner.RexxTokenList;
import org.imperfectmommy.rexxeditor.scanner.RexxVariableMethodContainer;

public class VariableDefinitionRule implements IRexxTokenRule {
    RexxTokenList               fTokenList;
    RexxVariableMethodContainer fContainer;

    public VariableDefinitionRule(RexxTokenList tokenList, RexxVariableMethodContainer varMetContainer) {
        fTokenList = tokenList;
        fContainer = varMetContainer;
    }

    public RexxToken evaluate(RexxToken token, RexxLine line) {
        if (token.getToken().equals(fTokenList.equalsSymbol) && line.getPosition() > 0) {
            int           position  = line.getPrevTokenPos(line.getPosition(), true);
            TextAttribute attribute = (TextAttribute) line.get(position).getToken().getData();
            if (attribute.equals(fTokenList.normal) || attribute.equals(fTokenList.procs)) {
                RexxToken oldToken = line.get(position);
                fContainer.addVariable(oldToken.getOffset(), oldToken.getLength());
                line.changeToken(position, new RexxToken(fTokenList.variable, oldToken.getOffset(), oldToken.getLength()));
            }
        }
        return RexxToken.NOTEXIST;
    }

}
