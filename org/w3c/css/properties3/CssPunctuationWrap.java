//
// $Id: CssPunctuationWrap.java,v 1.1 2002-07-19 20:30:12 sijtsche Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *  <P>
 *  <EM>Value:</EM> none || start || end || both || inherit<BR>
 *  <EM>Initial:</EM>none<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property determines whether a punctuation mark, if one is present, 
 *  should be placed outside the content area  in the padding or margin 
 *  area, at the start or at the end of a full line of text.
 */

public class CssPunctuationWrap extends CssProperty {
 
    CssValue wrap;

    static CssIdent none = new CssIdent("none");
    static CssIdent start = new CssIdent("start");
    static CssIdent end = new CssIdent("end");
    static CssIdent both = new CssIdent("both");

    /**
     * Create a new CssPunctuationWrap
     */
    public CssPunctuationWrap() {
	wrap = none;
    }

    /**
     * Create a new CssPunctuationWrap
     * 
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect values
     */
    public CssPunctuationWrap(ApplContext ac, CssExpression expression) throws InvalidParamException {
	setByUser();
	CssValue val = expression.getValue();
	if (val.equals(none)) {
	    wrap = none;
	    expression.next();
	}
	else if (val.equals(start)) {
	    wrap = start;
	    expression.next();
	}
	else if (val.equals(end)) {
	    wrap = end;
	    expression.next();
	}
	else if (val.equals(both)) {
	    wrap = both;
	    expression.next();
	}
	else if (val.equals(inherit)) {
	    wrap = inherit;
	    expression.next();
	}
	else {
	    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssPunctuationWrap != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssPunctuationWrap = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getPunctuationWrap();
	}
	else {
	    return ((Css3Style) style).cssPunctuationWrap;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssPunctuationWrap &&
	        wrap.equals(((CssPunctuationWrap) property).wrap));
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "punctuation-wrap";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return wrap;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return wrap.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return wrap.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return wrap == none;
    }

}
