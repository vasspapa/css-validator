//
// $Id: MediaMinWidth.java,v 1.1 2005-08-23 16:24:20 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: MediaMinWidth.java,v $
 * Revision 1.1  2005-08-23 16:24:20  ylafon
 * Patch by Jean-Guilhem Rouel
 *
 * Better handling of media and properties files
 * Major reorganization of those properties files
 *
 * Revision 1.2  2005/08/08 13:18:54  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.1  2003/01/08 10:01:44  sijtsche
 * new media feature for media queries
 *
 * Revision 1.1  2002/12/24 13:18:36  sijtsche
 * new version for CSS3: value initial added
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:14:07  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:30  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:39  plehegar
 * Nothing
 *
 * Revision 1.4  1997/08/06 17:30:25  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.3  1997/07/30 13:20:23  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/25 15:44:54  plehegar
 * bug fix in documentation
 *
 * Revision 1.1  1997/07/25 15:42:02  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssPercentage;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision: 1.1 $
 */
public class MediaMinWidth extends CssProperty {

    CssValue value;

    /**
     * Create a new MediaMinWidth
     */
    public MediaMinWidth() {
		//empty
    }

    /**
     * Create a new MediaMinWidth.
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public MediaMinWidth(ApplContext ac, CssExpression expression, 
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = null;
	
	if (expression != null) {
	    val = expression.getValue();
	}
	
	setByUser();
	
	if (val != null) {
	    if (val instanceof CssLength || val instanceof CssPercentage) {
		float f = ((Float) val.get()).floatValue();
		if (f < 0) {
		    throw new InvalidParamException("negative-value",
			    val.toString(), ac);
		}
		value = val;
	    } else if (val instanceof CssNumber) {
		value = ((CssNumber) val).getLength();
	    } else {
		throw new InvalidParamException("value", expression.getValue(),
			getPropertyName(), ac);
	    }
	    
	    expression.next();
	}
    }
    
    public MediaMinWidth(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property.
     */
    public Object get() {
	return value;
    }

    /**
     * Returns the name of this property.
     */
    public String getPropertyName() {
	return "min-width";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
		if (value != null) {
			return value.toString();
		} else {
			return null;
		}
    }


    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css3Style style0 = (Css3Style) style;
	if (style0.mediaMinWidth != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.mediaMinWidth = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMediaMinWidth();
	} else {
	    return ((Css3Style) style).mediaMinWidth;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof MediaMinWidth && value.equals(((MediaMinWidth) property).value));
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
