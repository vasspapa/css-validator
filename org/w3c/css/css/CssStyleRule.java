// $Id: CssStyleRule.java,v 1.13 2006-12-12 04:07:48 kdubost Exp $
// Author: Sijtsche de Jong
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.io.PrintWriter;
import java.util.Vector;

import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.Util;


public class CssStyleRule {

    public CssStyleRule(String indent, String selectors,
	    Vector properties, boolean important) {
	this.selectors = selectors;
	this.properties = properties;
	this.important = important;
	this.indent = indent;
    }

    public String toString() {
	StringBuffer ret = new StringBuffer();
	if (selectors != null) {
	    ret.append(selectors);
	    ret.append(' ');
	    ret.append('{');
	    ret.append('\n');
	}

	for (int i = 0; i < properties.size() ; i++) {
	    CssProperty property = (CssProperty)properties.elementAt(i);
	    ret.append(indent);
	    ret.append("   ");
	    ret.append(property.getPropertyName());
	    ret.append(" : ");
	    ret.append(property.toString());
	    if (property.getImportant()) {
		ret.append(" important");
	    }
	    ret.append(';');
	    ret.append('\n');
	}
	if (selectors != null) {
	    ret.append(indent);
	    ret.append('}');
	    ret.append('\n');
	    ret.append('\n');
	}
	return ret.toString();
    }
    /*
     public String toHTML() {
     StringBuffer ret = new StringBuffer("<li><span class='selector'>");
     if (selectors != null) {
     ret.append(selectors);
     ret.append("</span> {<ul class='vRule'>\n");
     }

     for (int i = 0; i < properties.size() ; i++) {
     CssProperty property = (CssProperty)properties.elementAt(i);
     ret.append("<li>");
     ret.append(property.getPropertyName());
     ret.append(" : <span class='vPropertyValue'>");
     ret.append(property.toString());
     ret.append("</span>");
     if (property.getImportant()) {
     ret.append(" !important");
     }
     ret.append(";</li>\n");
     }
     ret.append("</ul>}</li>\n\n");
     return ret.toString();
     }
     */
    public void toHTML(PrintWriter out) {
	if(properties != null) {
	    out.print("<div class='selector'><span class='selectorValue'>");
	    if (selectors != null) {
		out.print(selectors);
		out.print("</span> {<div class='vRuleList'>\n");
	    }

	    for (int i = 0; i < properties.size() ; i++) {
		CssProperty property = (CssProperty)properties.elementAt(i);
		out.print("<div class='Rule'>");
		out.print("<span class='Property'>");
		out.print(Util.escapeHTML(property.getPropertyName()));
		out.print("</span>");
		out.print(" : <span class='PropertyValue'>");
		out.print(Util.escapeHTML(property.toString()));
		out.print("</span>");
		if (property.getImportant()) {
		    out.print(" !important");
		}
		out.print(";</div>\n");
	    }
	    out.print("</div>}</div>\n\n");
	}
    }

    private String indent;
    private String selectors;
    private Vector properties;
    private boolean important;

}
