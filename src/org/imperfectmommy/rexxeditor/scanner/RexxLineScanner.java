package org.imperfectmommy.rexxeditor.scanner;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.imperfectmommy.rexxeditor.contentoutliner.RexxContentElement;
import org.imperfectmommy.rexxeditor.editors.ColorManager;
import org.imperfectmommy.rexxeditor.editors.RexxConfiguration;
import org.imperfectmommy.rexxeditor.scanner.rules.RexxEndLineCommentRule;
import org.imperfectmommy.rexxeditor.scanner.rules.RexxGeneralWordRule;
import org.imperfectmommy.rexxeditor.scanner.rules.RexxKeywordDetector;
import org.imperfectmommy.rexxeditor.scanner.rules.RexxKeywordRule;
import org.imperfectmommy.rexxeditor.scanner.rules.RexxNestedCommentRule;
import org.imperfectmommy.rexxeditor.scanner.rules.RexxNewLineRule;
import org.imperfectmommy.rexxeditor.scanner.rules.RexxSymbolRule;
import org.imperfectmommy.rexxeditor.scanner.rules.RexxWhitespaceDetector;

public class RexxLineScanner extends RuleBasedScanner {
    protected RexxLine fLine;

    protected RexxToken fCurrentToken;

    protected int fCurrentLinePosition = 0;

    RexxTokenList tokenList;

    RexxVariableMethodContainer varMetContainer;

    ArrayList<RexxContentElement> variableList;

    RexxConfiguration configuration;
    
    //TODO: Figure out why when a character is next to a special character, it turns gray

    public RexxLineScanner(ColorManager manager, RexxConfiguration configuration) {
        this.variableList    = new ArrayList<>();
        this.configuration   = configuration;
        this.varMetContainer = new RexxVariableMethodContainer();
        this.tokenList       = new RexxTokenList(manager);
        this.fLine           = new RexxLine(this.tokenList, this.varMetContainer);
        this.setRules();
    }

    @Override
    public int getTokenLength() {
        return this.fCurrentToken.getLength();
    }

    @Override
    public int getTokenOffset() {
        return this.fCurrentToken.getOffset();
    }

    @Override
    public IToken nextToken() {
        if (this.fCurrentLinePosition >= this.fLine.getLength()) {
            this.fLine.reset();
            // scan a new Line
            IToken token = this.fDefaultReturnToken;
            while (this.fOffset < this.fRangeEnd || !token.isEOF()) {
                int tokenOffset = this.fOffset;
                token = this.scanLine();
                this.fLine.add(new RexxToken(token, tokenOffset, this.fOffset - tokenOffset));

                if (token.equals(this.tokenList.newLine)) {
                    break;
                }
            }
            this.fCurrentLinePosition = 0;
        }

        this.fCurrentToken = this.fLine.get(this.fCurrentLinePosition);

        this.fCurrentLinePosition++;

        if (this.fCurrentToken.getToken().isEOF()) {
            if (!this.variableList.equals(this.varMetContainer.getVariableList())) {
                this.variableList = this.varMetContainer.getVariableList();
                this.configuration.updateList(this.variableList);
            }
        }

        return this.fCurrentToken.getToken();

    }

    private IToken scanLine() {
        if (this.fRules != null) {
            IToken token = null;
            for (IRule rule : this.fRules) {
                token = rule.evaluate(this);
                if (!token.isUndefined()) {
                    return token;
                }
            }
        }
        if (this.read() == EOF) {
            return Token.EOF;
        }
        this.unread();
        char c = (char) this.read();
        if (c == '\'' || c == '"') {
            this.unread();
        }
        return this.fDefaultReturnToken;
    }

    @Override
    public void setRange(IDocument document, int offset, int length) {
        this.varMetContainer.update(document);

        this.fDocument = document;
        this.fOffset   = offset;
        this.fColumn   = UNDEFINED;
        this.fRangeEnd = Math.min(this.fDocument.getLength(), offset + length - 1);

        String[] delimiters = this.fDocument.getLegalLineDelimiters();
        this.fDelimiters = new char[delimiters.length][];
        for (int i = 0; i < delimiters.length; i++) {
            this.fDelimiters[i] = delimiters[i].toCharArray();
        }

        if (this.fDefaultReturnToken == null) {
            this.fDefaultReturnToken = new Token(null);
        }

    }

    private void setRules() {
        IRule       whitespaceRule    = new WhitespaceRule(new RexxWhitespaceDetector());
        IRule       nestedCommentRule = new RexxNestedCommentRule(this.tokenList.rexxComment);
        IRule       eolCommentRule    = new RexxEndLineCommentRule(this.tokenList.rexxComment);
        IRule       doubleQuoteRule   = new SingleLineRule("\"", "\"", this.tokenList.rexxTag);
        IRule       singleQuoteRule   = new SingleLineRule("\"", "\"", this.tokenList.rexxTag);
        IRule       numberRule        = new NumberRule(this.tokenList.rexxComment);
        IRule       newLineRule       = new RexxNewLineRule(this.tokenList.newLine, this.tokenList.symbol);
        IRule       keywordRule       = new RexxKeywordRule(new RexxKeywordDetector(), this.tokenList);
        IRule       symbolRule        = new RexxSymbolRule(this.tokenList);
        IRule       generalRule       = new RexxGeneralWordRule(this.tokenList.word, new RexxKeywordDetector());
        List<IRule> rules             = new ArrayList<>();
        rules.add(whitespaceRule);
        rules.add(nestedCommentRule);
        rules.add(eolCommentRule);
        rules.add(doubleQuoteRule);
        rules.add(singleQuoteRule);
        rules.add(numberRule);
        rules.add(newLineRule);
        rules.add(keywordRule);
        rules.add(symbolRule);
        rules.add(generalRule);
        this.setRules(rules.toArray(new IRule[0]));
    }

}
