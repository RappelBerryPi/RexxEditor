package org.imperfectmommy.rexxeditor.scanner;

import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.imperfectmommy.rexxeditor.Activator;
import org.imperfectmommy.rexxeditor.preferences.IRexxPreferenceFields;

public class RexxTokenList {
    public IToken word;

    public IToken symbol;
    public IToken equalsSymbol;
    public IToken pointSymbol;
    public IToken openBraceSymbol;
    public IToken closeBraceSymbol;
    public IToken tildeSymbol;
    public IToken doubleQuoteSymbol;
    public IToken singleQuoteSymbol;

    public IToken newLine;
    public IToken keyword;
    public IToken variable;
    public IToken method;
    public IToken environmentSymbol;
    public IToken rexxComment;
    public IToken rexxTag;

    public IToken CLASSToken;
    public IToken METHODToken;
    public IToken REQUIRESToken;
    public IToken ROUTINEToken;

    public IToken ADDRESSKeyword;
    public IToken VALUEKeyword;
    public IToken PARSEKeyword;
    public IToken UPPERKeyword;
    public IToken LOWERKeyword;
    public IToken ARGKeyword;
    public IToken CALLKeyword;
    public IToken OFFKeyword;
    public IToken ONKeyword;
    public IToken ANYKeyword;
    public IToken ERRORKeyword;
    public IToken FAILUREKeyword;
    public IToken HALTKeyword;
    public IToken NOTREADYKeyword;
    public IToken USERKeyword;
    public IToken NAMEKeyword;
    public IToken ENDKeyword;
    public IToken FOREVERKeyword;
    public IToken TOKeyword;
    public IToken OVERKeyword;
    public IToken BYKeyword;
    public IToken FORKeyword;
    public IToken DOKeyword;
    public IToken WHILEKeyword;
    public IToken UNTILKeyword;
    public IToken DROPKeyword;
    public IToken EXITKeyword;
    public IToken EXPOSEKeyword;
    public IToken FORWARDKeyword;
    public IToken CONTINUEKeyword;
    public IToken ARRAYKeyword;
    public IToken ARGUMENTSKeyword;
    public IToken MESSAGEKeyword;
    public IToken CLASSKeyword;
    public IToken GUARDKeyword;
    public IToken WHENKeyword;
    public IToken IFKeyword;
    public IToken ELSEKeyword;
    public IToken THENKeyword;
    public IToken INTERPRETKeyword;
    public IToken ITERATEKeyword;
    public IToken LEAVEKeyword;
    public IToken NOPKeyword;
    public IToken NUMERICKeyword;
    public IToken DIGITSKeyword;
    public IToken FORMKeyword;
    public IToken FUZZKeyword;
    public IToken SCIENTIFICKeyword;
    public IToken ENGINEERINGKeyword;
    public IToken CASELESSKeyword;
    public IToken LINEINKeyword;
    public IToken PULLKeyword;
    public IToken SOURCEKeyword;
    public IToken VARKeyword;
    public IToken VERSIONKeyword;
    public IToken PROCEDUREKeyword;
    public IToken PUSHKeyword;
    public IToken QUEUEKeyword;
    public IToken RAISEKeyword;
    public IToken SYNTAXKeyword;
    public IToken PROPAGATEKeyword;
    public IToken REPLYKeyword;
    public IToken RETURNKeyword;
    public IToken SAYKeyword;
    public IToken SELECTKeyword;
    public IToken SIGNALKeyword;
    public IToken TRACEKeyword;
    public IToken USEKeyword;
    public IToken ADDITIONALKeyword;
    public IToken DESCRIPTIONKeyword;

    public String procs;
    public String methodVar;
    public String symb;
    public String comment;
    public String string;
    public String normal;
    

    public void setTokens() {

        procs     = IRexxPreferenceFields.PREF_PROC_INSTR;    
        procs     = IRexxPreferenceFields.PREF_PROC_INSTR;
        methodVar = IRexxPreferenceFields.PREF_VAR_COLOR;
        symb      = IRexxPreferenceFields.PREF_REXX_SYMBOL;
        comment   = IRexxPreferenceFields.PREF_REXX_COMMENT;
        string    = IRexxPreferenceFields.PREF_STRING_COLOR;
        normal    = IRexxPreferenceFields.PREF_DEFAULT_COLOR;

        word = new RexxTokenListToken(normal);

        symbol            = new RexxTokenListToken(symb);
        equalsSymbol      = new RexxTokenListToken(symb);
        pointSymbol       = new RexxTokenListToken(symb);
        openBraceSymbol   = new RexxTokenListToken(symb);
        tildeSymbol       = new RexxTokenListToken(symb);
        doubleQuoteSymbol = new RexxTokenListToken(string);
        singleQuoteSymbol = new RexxTokenListToken(string);

        newLine = new RexxTokenListToken(symb);
        // keyword = new RexxTokenListToken(procs);
        variable          = new RexxTokenListToken(methodVar);
        method            = new RexxTokenListToken(methodVar);
        rexxComment       = new RexxTokenListToken(comment);
        rexxTag           = new RexxTokenListToken(string);
        environmentSymbol = new RexxTokenListToken(environmentSymbol);

        ADDITIONALKeyword  = new RexxTokenListToken(procs);
        DESCRIPTIONKeyword = new RexxTokenListToken(procs);
        ADDRESSKeyword     = new RexxTokenListToken(procs);
        VALUEKeyword       = new RexxTokenListToken(procs);
        PARSEKeyword       = new RexxTokenListToken(procs);
        UPPERKeyword       = new RexxTokenListToken(procs);
        LOWERKeyword       = new RexxTokenListToken(procs);
        ARGKeyword         = new RexxTokenListToken(procs);
        CALLKeyword        = new RexxTokenListToken(procs);
        OFFKeyword         = new RexxTokenListToken(procs);
        ONKeyword          = new RexxTokenListToken(procs);
        ANYKeyword         = new RexxTokenListToken(procs);
        ERRORKeyword       = new RexxTokenListToken(procs);
        FAILUREKeyword     = new RexxTokenListToken(procs);
        HALTKeyword        = new RexxTokenListToken(procs);
        NOTREADYKeyword    = new RexxTokenListToken(procs);
        USERKeyword        = new RexxTokenListToken(procs);
        NAMEKeyword        = new RexxTokenListToken(procs);
        ENDKeyword         = new RexxTokenListToken(procs);
        FOREVERKeyword     = new RexxTokenListToken(procs);
        TOKeyword          = new RexxTokenListToken(procs);
        BYKeyword          = new RexxTokenListToken(procs);
        FORKeyword         = new RexxTokenListToken(procs);
        DOKeyword          = new RexxTokenListToken(procs);
        WHILEKeyword       = new RexxTokenListToken(procs);
        UNTILKeyword       = new RexxTokenListToken(procs);
        DROPKeyword        = new RexxTokenListToken(procs);
        EXITKeyword        = new RexxTokenListToken(procs);
        EXPOSEKeyword      = new RexxTokenListToken(procs);
        FORWARDKeyword     = new RexxTokenListToken(procs);
        CONTINUEKeyword    = new RexxTokenListToken(procs);
        ARRAYKeyword       = new RexxTokenListToken(procs);
        OVERKeyword        = new RexxTokenListToken(procs);
        ARGUMENTSKeyword   = new RexxTokenListToken(procs);
        MESSAGEKeyword     = new RexxTokenListToken(procs);
        CLASSKeyword       = new RexxTokenListToken(procs);
        GUARDKeyword       = new RexxTokenListToken(procs);
        WHENKeyword        = new RexxTokenListToken(procs);
        IFKeyword          = new RexxTokenListToken(procs);
        ELSEKeyword        = new RexxTokenListToken(procs);
        THENKeyword        = new RexxTokenListToken(procs);
        INTERPRETKeyword   = new RexxTokenListToken(procs);
        ITERATEKeyword     = new RexxTokenListToken(procs);
        LEAVEKeyword       = new RexxTokenListToken(procs);
        NOPKeyword         = new RexxTokenListToken(procs);
        NUMERICKeyword     = new RexxTokenListToken(procs);
        DIGITSKeyword      = new RexxTokenListToken(procs);
        FORMKeyword        = new RexxTokenListToken(procs);
        FUZZKeyword        = new RexxTokenListToken(procs);
        SCIENTIFICKeyword  = new RexxTokenListToken(procs);
        ENGINEERINGKeyword = new RexxTokenListToken(procs);
        CASELESSKeyword    = new RexxTokenListToken(procs);
        LINEINKeyword      = new RexxTokenListToken(procs);
        PULLKeyword        = new RexxTokenListToken(procs);
        SOURCEKeyword      = new RexxTokenListToken(procs);
        VARKeyword         = new RexxTokenListToken(procs);
        VERSIONKeyword     = new RexxTokenListToken(procs);
        PROCEDUREKeyword   = new RexxTokenListToken(procs);
        PUSHKeyword        = new RexxTokenListToken(procs);
        QUEUEKeyword       = new RexxTokenListToken(procs);
        RAISEKeyword       = new RexxTokenListToken(procs);
        SYNTAXKeyword      = new RexxTokenListToken(procs);
        PROPAGATEKeyword   = new RexxTokenListToken(procs);
        REPLYKeyword       = new RexxTokenListToken(procs);
        RETURNKeyword      = new RexxTokenListToken(procs);
        SAYKeyword         = new RexxTokenListToken(procs);
        SELECTKeyword      = new RexxTokenListToken(procs);
        SIGNALKeyword      = new RexxTokenListToken(procs);
        TRACEKeyword       = new RexxTokenListToken(procs);
        USEKeyword         = new RexxTokenListToken(procs);

        CLASSToken    = new RexxTokenListToken(procs);
        METHODToken   = new RexxTokenListToken(procs);
        REQUIRESToken = new RexxTokenListToken(procs);
        ROUTINEToken  = new RexxTokenListToken(procs);
    }

    public RexxTokenList() {
        this.setTokens();
    }

    public boolean isLineStartKeyword(IToken token) {
        if (token.equals(IFKeyword) || token.equals(DOKeyword) || token.equals(ADDRESSKeyword) || token.equals(ARGKeyword) || token.equals(CALLKeyword) || token.equals(DROPKeyword)
                || token.equals(EXITKeyword) || token.equals(EXPOSEKeyword) || token.equals(FORWARDKeyword) || token.equals(GUARDKeyword) || token.equals(INTERPRETKeyword)
                || token.equals(ITERATEKeyword) || token.equals(LEAVEKeyword) || token.equals(NOPKeyword) || token.equals(NUMERICKeyword) || token.equals(PARSEKeyword)
                || token.equals(PROCEDUREKeyword) || token.equals(PULLKeyword) || token.equals(PUSHKeyword) || token.equals(QUEUEKeyword) || token.equals(RAISEKeyword) || token.equals(REPLYKeyword)
                || token.equals(RETURNKeyword) || token.equals(SAYKeyword) || token.equals(SELECTKeyword) || token.equals(SIGNALKeyword) || token.equals(ELSEKeyword) || token.equals(ENDKeyword)
                || token.equals(TRACEKeyword) || token.equals(USEKeyword)) {
            return true;
        }
        return false;
    }

    public boolean isKeyword(IToken token) {
        if (token.equals(ADDRESSKeyword) || token.equals(VALUEKeyword) || token.equals(PARSEKeyword) || token.equals(UPPERKeyword) || token.equals(LOWERKeyword) || token.equals(ARGKeyword)
                || token.equals(CALLKeyword) || token.equals(OFFKeyword) || token.equals(ONKeyword) || token.equals(ANYKeyword) || token.equals(ERRORKeyword) || token.equals(FAILUREKeyword)
                || token.equals(HALTKeyword) || token.equals(NOTREADYKeyword) || token.equals(USERKeyword) || token.equals(NAMEKeyword) || token.equals(ENDKeyword) || token.equals(FOREVERKeyword)
                || token.equals(TOKeyword) || token.equals(OVERKeyword) || token.equals(BYKeyword) || token.equals(FORKeyword) || token.equals(DOKeyword) || token.equals(WHILEKeyword)
                || token.equals(UNTILKeyword) || token.equals(DROPKeyword) || token.equals(EXITKeyword) || token.equals(EXPOSEKeyword) || token.equals(FORWARDKeyword) || token.equals(CONTINUEKeyword)
                || token.equals(ARRAYKeyword) || token.equals(ARGUMENTSKeyword) || token.equals(MESSAGEKeyword) || token.equals(CLASSKeyword) || token.equals(GUARDKeyword) || token.equals(WHENKeyword)
                || token.equals(IFKeyword) || token.equals(ELSEKeyword) || token.equals(THENKeyword) || token.equals(INTERPRETKeyword) || token.equals(ITERATEKeyword) || token.equals(LEAVEKeyword)
                || token.equals(NOPKeyword) || token.equals(NUMERICKeyword) || token.equals(DIGITSKeyword) || token.equals(FORMKeyword) || token.equals(FUZZKeyword) || token.equals(SCIENTIFICKeyword)
                || token.equals(ENGINEERINGKeyword) || token.equals(CASELESSKeyword) || token.equals(LINEINKeyword) || token.equals(PULLKeyword) || token.equals(SOURCEKeyword)
                || token.equals(VARKeyword) || token.equals(VERSIONKeyword) || token.equals(PROCEDUREKeyword) || token.equals(PUSHKeyword) || token.equals(QUEUEKeyword) || token.equals(RAISEKeyword)
                || token.equals(SYNTAXKeyword) || token.equals(PROPAGATEKeyword) || token.equals(REPLYKeyword) || token.equals(RETURNKeyword) || token.equals(SAYKeyword) || token.equals(SELECTKeyword)
                || token.equals(SIGNALKeyword) || token.equals(TRACEKeyword) || token.equals(USEKeyword) || token.equals(ADDITIONALKeyword) || token.equals(DESCRIPTIONKeyword)) {
            return true;
        }

        return false;
    }

}
