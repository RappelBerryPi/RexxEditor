package org.imperfectmommy.rexxeditor.scanner.tokenRules;

import org.imperfectmommy.rexxeditor.scanner.RexxLine;
import org.imperfectmommy.rexxeditor.scanner.RexxToken;
import org.imperfectmommy.rexxeditor.scanner.RexxTokenList;
import org.imperfectmommy.rexxeditor.scanner.RexxVariableMethodContainer;

public class VariableRule implements IRexxTokenRule {
    RexxTokenList               fTokenList;
    RexxVariableMethodContainer fContainer;

    public VariableRule(RexxTokenList tokenList, RexxVariableMethodContainer varMetContainer) {
        fTokenList = tokenList;
        fContainer = varMetContainer;
    }

    public RexxToken evaluate(RexxToken token, RexxLine line) {
        if (token.getToken().equals(fTokenList.word)) {
            if (fContainer.isVariable(token.getOffset(), token.getLength())) {
                token.setToken(fTokenList.variable);
                return token;
            }
        }
        return RexxToken.NOTEXIST;
    }
}
