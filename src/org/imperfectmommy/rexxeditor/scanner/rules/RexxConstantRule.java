package org.imperfectmommy.rexxeditor.scanner.rules;

import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.imperfectmommy.rexxeditor.scanner.RexxTokenList;

public class RexxConstantRule implements IRule {
    protected Map<Character, IToken> fSymbols = new HashMap<>();

    public RexxConstantRule(RexxTokenList tokenList) {
        fSymbols.put('"', tokenList.doubleQuoteSymbol);
        fSymbols.put('\'', tokenList.singleQuoteSymbol);
    }

    public IToken evaluate(ICharacterScanner scanner) {
        IToken initialToken = null;
        char   c            = 0;
        do {
            c            = (char) scanner.read();
            initialToken = fSymbols.get(c);
        } while (initialToken == null && c != '\n' && c != ICharacterScanner.EOF && scanner.getColumn() != -1);

        if (initialToken != null) {
            c = (char) scanner.read();
            IToken token = fSymbols.get(c);
            while (token != initialToken && c != ICharacterScanner.EOF && c != '\n') {
                c     = (char) scanner.read();
                token = fSymbols.get(c);
            }
            if (token == null) {
                initialToken = Token.UNDEFINED;
            }
        } else {
            initialToken = Token.UNDEFINED;
        }
        scanner.unread();
        return initialToken;
    }
}
