// $Id: CssRuleList.java,v 1.4 2003-10-20 12:52:17 ylafon Exp $
// Author: Sijtsche de Jong
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import org.w3c.css.parser.AtRule;
import java.util.Vector;

public class CssRuleList {

    AtRule atRule;
    String atRuleString;
    Vector rulelist;
    public String pseudopage;
    String indent;

    public CssRuleList() {
	atRule = null;
	atRuleString = new String();
	rulelist = new Vector();
	String pseudopage = new String();
	indent = new String();
    }

    public void addStyleRule(CssStyleRule stylerule) {
	rulelist.addElement(stylerule);
    }

    public Vector getStyleRules() {
	return rulelist;
    }

    public void addAtRule(AtRule atRule) {
	this.atRule = atRule;
	atRuleString = atRule.toString();
    }

    public String getAtRule() {
	return atRuleString;
    }

    public String toString() {
	StringBuffer ret = new StringBuffer();
	
	if (atRule.isEmpty()) {
	    if (null != atRule && !atRuleString.equals("")) {
		ret.append(atRuleString);
		ret.append(' ');
		ret.append('\n');
	    }
	} else {
	    if (!atRuleString.equals("")) {
		ret.append(atRuleString);
		ret.appent(' ');
		ret.append('{');
		ret.append('\n');
		indent = "   ";
	    }
	    for (int i = 0; i < rulelist.size() ; i++ ) {
		ret.append(indent);
		ret.append(((CssStyleRule)rulelist.elementAt(i)).toString());
	    }

	    if (!atRuleString.equals("")) {
		ret.append('}');
		ret.append('\n');
	    }
	}
	return ret.toString();
    }

    public String toHTML() {
	StringBuffer ret = new StringBuffer();

	if (null != atRule && atRule.isEmpty()) {
	    if (!atRuleString.equals("")) {
		ret.append("<li><span class='atSelector'>");
		ret.append(atRuleString);
		ret.append("</span></li> \n\n");
	    }
	} else {
	    if (!atRuleString.equals("")) {
		ret.append("<li><span class='atSelector'>");
		ret.append(atRuleString);
		ret.append("</span> {\n<ul>\n");
	    }
	    for (int i = 0; i < rulelist.size() ; i++ ) {
		ret.append(((CssStyleRule)rulelist.elementAt(i)).toHTML());
	    }

	    if (!atRuleString.equals("")) {
		ret.append("</ul>}</li>\n");
	    }
	}
	return ret.toString();
    }

    public void clear() {
	atRuleString = "";
	rulelist.clear();
	pseudopage = "";
    }
}
