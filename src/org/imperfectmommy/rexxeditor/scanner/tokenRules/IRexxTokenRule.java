package org.imperfectmommy.rexxeditor.scanner.tokenRules;

import org.imperfectmommy.rexxeditor.scanner.RexxLine;
import org.imperfectmommy.rexxeditor.scanner.RexxToken;

public interface IRexxTokenRule {
	
	public RexxToken evaluate(RexxToken token, RexxLine line);
}
