//
// $Id: CssTextHeight.java,v 1.1 2002-08-20 13:53:41 sijtsche Exp $
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
 *
 */

public class CssTextHeight extends CssProperty {

    CssValue textheight;

    static CssIdent auto = new CssIdent("auto");

    private static String[] values = {
	"auto", "font-size", "text-size", "max-size", "inherit", "initial"
    };

    /**
     * Create a new CssTextHeight
     */
    public CssTextHeight() {
		textheight = auto;
    }

    /**
     * Create a new CssTextHeight
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssTextHeight(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		textheight = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", expression.getValue(),
					    getPropertyName(), ac);
	}
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssTextHeight != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssTextHeight = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextHeight();
	}
	else {
	    return ((Css3Style) style).cssTextHeight;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssTextHeight &&
		textheight.equals(((CssTextHeight) property).textheight));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-height";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return textheight;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return textheight.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return textheight.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (textheight == auto);
    }

}
