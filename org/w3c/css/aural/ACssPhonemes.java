//
// $Id: ACssPhonemes.java,v 1.1 2003-07-30 06:34:52 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: ACssPhonemes.java,v $
 * Revision 1.1  2003-07-30 06:34:52  sijtsche
 * new speech property
 *
 * Revision 1.2  2002/04/08 21:16:56  plehegar
 * New
 *
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.6  1997/08/25 13:52:36  plehegar
 * Added getValue()
 *
 * Revision 1.5  1997/08/22 15:00:35  plehegar
 * Bugs
 *
 * Revision 1.4  1997/08/22 14:58:25  plehegar
 * Added getPropertyInStyle()
 *
 * Revision 1.3  1997/08/21 21:13:38  plehegar
 * Added time
 *
 * Revision 1.2  1997/08/21 14:34:19  vmallet
 * Minor modifications so we could compile it.
 *
 * Revision 1.1  1997/08/14 12:58:48  plehegar
 * Initial revision
 *
 */

package org.w3c.css.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssString;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**

 *
 * @version $Revision: 1.1 $
 */
public class ACssPhonemes extends ACssProperty {

    CssValue value;


    /**
     * Create a new ACssPhonemes
     */
    public ACssPhonemes() {
    }

    /**
     * Creates a new ACssPhonemes
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */
    public ACssPhonemes(ApplContext ac, CssExpression expression)
	   throws InvalidParamException {
	CssValue val = expression.getValue();

	setByUser();

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssString) {
	    value = val;
	    expression.next();
	    return;
	}

	throw new InvalidParamException("value", val.toString(),
					getPropertyName(), ac);
    }

    /**
     * Returns the current value
     */
    public Object get() {
	return value;
    }

    /**
     * Returns some usable value of this property...
     */
    public int getValue() { // vm
		return 0;
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (value != null)
	    return value.toString();
	else
	    return null;
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "phonemes";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssPhonemes != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssPhonemes = this;
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	if (value != null) {
	    return (property instanceof ACssPhonemes &&
		    value.equals(((ACssPhonemes) property).value));
	} else {
	    return false;
	}
    }


    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getPhonemes();
	} else {
	    return ((ACssStyle) style).acssPhonemes;
	}
    }

}
