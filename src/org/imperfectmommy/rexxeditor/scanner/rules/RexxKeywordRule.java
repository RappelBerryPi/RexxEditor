package org.imperfectmommy.rexxeditor.scanner.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.imperfectmommy.rexxeditor.scanner.RexxTokenList;

public class RexxKeywordRule extends WordRule {
    private StringBuffer fBuffer = new StringBuffer();

    public RexxKeywordRule(IWordDetector wordDetector, RexxTokenList tokenList) {
        super(wordDetector);
        // TODO: Clean up words for TSO REXX
        fWords.put("ADDRESS", tokenList.ADDRESSKeyword);
        fWords.put("VALUE", tokenList.VALUEKeyword);
        fWords.put("PARSE", tokenList.PARSEKeyword);
        fWords.put("UPPER", tokenList.UPPERKeyword);
        fWords.put("LOWER", tokenList.LOWERKeyword);
        fWords.put("ARG", tokenList.ARGKeyword);
        fWords.put("CALL", tokenList.CALLKeyword);
        fWords.put("OFF", tokenList.OFFKeyword);
        fWords.put("ON", tokenList.ONKeyword);
        fWords.put("ANY", tokenList.ANYKeyword);
        fWords.put("ERROR", tokenList.ERRORKeyword);
        fWords.put("FAILURE", tokenList.FAILUREKeyword);
        fWords.put("HALT", tokenList.HALTKeyword);
        fWords.put("NOTREADY", tokenList.NOTREADYKeyword);
        fWords.put("USER", tokenList.USERKeyword);
        fWords.put("NAME", tokenList.NAMEKeyword);
        fWords.put("END", tokenList.ENDKeyword);
        fWords.put("FOREVER", tokenList.FOREVERKeyword);
        fWords.put("TO", tokenList.TOKeyword);
        fWords.put("BY", tokenList.BYKeyword);
        fWords.put("FOR", tokenList.FORKeyword);
        fWords.put("DO", tokenList.DOKeyword);
        fWords.put("WHILE", tokenList.WHILEKeyword);
        fWords.put("UNTIL", tokenList.UNTILKeyword);
        fWords.put("DROP", tokenList.DROPKeyword);
        fWords.put("EXIT", tokenList.EXITKeyword);
        fWords.put("EXPOSE", tokenList.EXPOSEKeyword);
        fWords.put("FORWARD", tokenList.FORWARDKeyword);
        fWords.put("CONTINUE", tokenList.CONTINUEKeyword);
        fWords.put("ARRAY", tokenList.ARRAYKeyword);
        fWords.put("ARGUMENTS", tokenList.ARGUMENTSKeyword);
        fWords.put("MESSAGE", tokenList.MESSAGEKeyword);
        fWords.put("CLASS", tokenList.CLASSKeyword);
        fWords.put("GUARD", tokenList.GUARDKeyword);
        fWords.put("WHEN", tokenList.WHENKeyword);
        fWords.put("IF", tokenList.IFKeyword);
        fWords.put("ELSE", tokenList.ELSEKeyword);
        fWords.put("THEN", tokenList.THENKeyword);
        fWords.put("INTERPRET", tokenList.INTERPRETKeyword);
        fWords.put("ITERATE", tokenList.ITERATEKeyword);
        fWords.put("LEAVE", tokenList.LEAVEKeyword);
        fWords.put("NOP", tokenList.NOPKeyword);
        fWords.put("NUMERIC", tokenList.NUMERICKeyword);
        fWords.put("DIGITS", tokenList.DIGITSKeyword);
        fWords.put("FORM", tokenList.FORMKeyword);
        fWords.put("FUZZ", tokenList.FUZZKeyword);
        fWords.put("SCIENTIFIC", tokenList.SCIENTIFICKeyword);
        fWords.put("ENGINEERING", tokenList.ENGINEERINGKeyword);
        fWords.put("CASELESS", tokenList.CASELESSKeyword);
        fWords.put("LINEIN", tokenList.LINEINKeyword);
        fWords.put("PULL", tokenList.PULLKeyword);
        fWords.put("SOURCE", tokenList.SOURCEKeyword);
        fWords.put("VAR", tokenList.VARKeyword);
        fWords.put("OVER", tokenList.OVERKeyword);
        fWords.put("VERSION", tokenList.VERSIONKeyword);
        fWords.put("PROCEDURE", tokenList.PROCEDUREKeyword);
        fWords.put("EXPOSE", tokenList.EXPOSEKeyword);
        fWords.put("PUSH", tokenList.PUSHKeyword);
        fWords.put("QUEUE", tokenList.QUEUEKeyword);
        fWords.put("RAISE", tokenList.RAISEKeyword);
        fWords.put("SYNTAX", tokenList.SYNTAXKeyword);
        fWords.put("PROPAGATE", tokenList.PROPAGATEKeyword);
        fWords.put("REPLY", tokenList.REPLYKeyword);
        fWords.put("RETURN", tokenList.RETURNKeyword);
        fWords.put("SAY", tokenList.SAYKeyword);
        fWords.put("SELECT", tokenList.SELECTKeyword);
        fWords.put("SIGNAL", tokenList.SIGNALKeyword);
        fWords.put("TRACE", tokenList.TRACEKeyword);
        fWords.put("USE", tokenList.USEKeyword);
        fWords.put("ADDITIONAL", tokenList.ADDITIONALKeyword);
        fWords.put("DESCRIPTION", tokenList.DESCRIPTIONKeyword);
    }

    public IToken evaluate(ICharacterScanner scanner) {
        int c = scanner.read();
        if (fDetector.isWordStart((char) c)) {
            if (fColumn == UNDEFINED || (fColumn == scanner.getColumn() - 1)) {

                fBuffer.setLength(0);
                do {
                    fBuffer.append((char) c);
                    c = scanner.read();
                } while (c != ICharacterScanner.EOF && fDetector.isWordPart((char) c));
                scanner.unread();

                IToken token = (IToken) fWords.get(fBuffer.toString().toUpperCase());
                if (token != null) return token;

                if (fDefaultToken.isUndefined()) unreadBuffer(scanner);

                scanner.unread();
                return fDefaultToken;
            }
        }

        scanner.unread();
        return Token.UNDEFINED;
    }
}
