//
// $Id: ATSCNavLeft.java,v 1.3 2010-01-05 13:49:34 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.atsc;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssString;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> &lt;integer&gt; || &lt;identifier&gt; ||
 *   &lt;identifier&gt;  &lt;integer&gt; <BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 *  <P>
 *  This property is used to effect explicit directional navigation control by
 *  associating specific styled elements with directional navigation events.
 */
public class ATSCNavLeft extends CssProperty {

    CssValue navleft;
    ApplContext ac;
    CssIdent auto = new CssIdent("auto");

    public ATSCNavLeft() {
	// nothing to do
    }

    /**
     * Create a new ATSCNavLeft
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public ATSCNavLeft (ApplContext ac, CssExpression expression, boolean check)
	throws InvalidParamException {

	if(check && expression.getCount() >1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	this.ac = ac;
	setByUser(); // tell this property is set by the user
	CssValue val = expression.getValue();

	if (val instanceof CssNumber) {
	    navleft = val;
	    expression.next();
	} else if (val instanceof CssString) {
	    navleft = val;
	    expression.next();
	} else if (val.equals(auto)) {
	    navleft = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", val.toString(),
		    getPropertyName(), ac);
	}
    }

    public ATSCNavLeft(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	 if (((ATSCStyle) style).navleft != null)
	     style.addRedefinitionWarning(ac, this);
	 ((ATSCStyle) style).navleft = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getNavLeft();
	} else {
	    return ((ATSCStyle) style).navleft;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ATSCNavLeft &&
                navleft.equals( ((ATSCNavLeft) property).navleft));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "atsc-nav-left";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return navleft;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return false;
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return navleft.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}


