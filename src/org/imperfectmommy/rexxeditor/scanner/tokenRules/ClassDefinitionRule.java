package org.imperfectmommy.rexxeditor.scanner.tokenRules;

import org.imperfectmommy.rexxeditor.scanner.RexxLine;
import org.imperfectmommy.rexxeditor.scanner.RexxToken;
import org.imperfectmommy.rexxeditor.scanner.RexxTokenList;
import org.imperfectmommy.rexxeditor.scanner.RexxVariableMethodContainer;

public class ClassDefinitionRule implements IRexxTokenRule{
	RexxTokenList fTokenList;
	RexxVariableMethodContainer fContainer;
	
	public ClassDefinitionRule(RexxTokenList tokenList, RexxVariableMethodContainer varMetContainer) {
		fTokenList = tokenList;
		fContainer = varMetContainer;
	}
	
	public RexxToken evaluate(RexxToken token, RexxLine line) {
		
		if (token.getToken().equals(fTokenList.word) && line.getPosition()>0) {
			int position = line.getPrevTokenPos(line.getPosition(), true);
			if (position>-1) {
			    /*
				if (line.get(position).getToken().equals(fTokenList.CLASSToken)) {
					fContainer.addClass(token.getOffset(), token.getLength());
					token.setToken(fTokenList.method);
					return token;
				}
				*/
				if (line.get(position).getToken().equals(fTokenList.METHODToken)) {
				    fContainer.addSubroutine(token.getOffset(), token.getLength());
					token.setToken(fTokenList.method);
					return token;
				}
				/*
				if (line.get(position).getToken().equals(fTokenList.REQUIRESToken)) {
					fContainer.addRequires(token.getOffset(), token.getLength());
					token.setToken(fTokenList.method);
					return token;
				}
				if (line.get(position).getToken().equals(fTokenList.ROUTINEToken)) {
					fContainer.addRoutine(token.getOffset(), token.getLength());
					token.setToken(fTokenList.method);
					return token;
				}
				*/
			
			}
		}
		return RexxToken.NOTEXIST;
	}
}
