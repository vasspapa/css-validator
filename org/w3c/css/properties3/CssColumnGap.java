//
// $Id: CssColumnGap.java,v 1.1 2002-08-20 08:51:50 sijtsche Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// COPYRIGHT (c) 1995-2000 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssNumber;
import org.w3c.css.properties.CssProperty;

/**
 *
 */

    public class CssColumnGap extends CssProperty {

	CssValue columngap;


	/**
	 * Create a new CssColumnGap
	 */
	public CssColumnGap() {
	    columngap = new CssNumber(0);
	}

	/**
	 * Create a new CssColumnGap
	 *
	 *
	 */
	public CssColumnGap(ApplContext ac, CssExpression expression) throws InvalidParamException {
	    setByUser();
	    CssValue val = expression.getValue();
	    if (val instanceof CssPercentage) {
			columngap = val;
			expression.next();
	    }
	    else if (val instanceof CssLength) {
			columngap = val;
			expression.next();
	    }
	    else if (val.equals(inherit)) {
			columngap = inherit;
			expression.next();
	    }

	    else {
		throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
	    }
	}

	/**
	 * Add this property to the CssStyle.
	 *
	 * @param style The CssStyle
	 */
	public void addToStyle(ApplContext ac, CssStyle style) {
	    if (((Css3Style) style).cssColumnGap != null)
		style.addRedefinitionWarning(ac, this);
	    ((Css3Style) style).cssColumnGap = this;

	}

	/**
	 * Get this property in the style.
	 *
	 * @param style The style where the property is
	 * @param resolve if true, resolve the style to find this property
	 */
        public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	    if (resolve) {
		return ((Css3Style) style).getColumnGap();
	    } else {
		return ((Css3Style) style).cssColumnGap;
	    }
	}

	/**
	 * Compares two properties for equality.
	 *
	 * @param value The other property.
	 */
	public boolean equals(CssProperty property) {
	    return (property instanceof CssColumnGap &&
		    columngap.equals( ((CssColumnGap) property).columngap));
	}

	/**
	 * Returns the name of this property
	 */
	public String getPropertyName() {
	    return "column-gap";
	}

	/**
	 * Returns the value of this property
	 */
	public Object get() {
	    return columngap;
	}

	/**
	 * Returns true if this property is "softly" inherited
	 */
	public boolean isSoftlyInherited() {
	    return columngap.equals(inherit);
	}

	/**
	 * Returns a string representation of the object
	 */
	public String toString() {
	    return columngap.toString();
	}

	/**
	 * Is the value of this property a default value
	 * It is used by all macro for the function <code>print</code>
	 */
	public boolean isDefault() {
	    return columngap == new CssNumber(0);
	}

    }
