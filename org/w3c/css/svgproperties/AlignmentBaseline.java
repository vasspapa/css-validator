//
// $Id: AlignmentBaseline.java,v 1.1 2002-07-19 20:58:00 sijtsche Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.svgproperties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *  <P>
 *  <EM>Value:</EM> auto | baseline | before-edge |
 *  text-before-edge | middle | after-edge |
 *  text-after-edge | ideographic | alphabetic | lower | hanging | mathematical
 *  | inherit <BR>
 *  <EM>Initial:</EM>see property description<BR>
 *  <EM>Applies to:</EM>text, tspan, tref, altGlyph, textPath elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class AlignmentBaseline extends CssProperty {

    CssValue albaseline;

    private static String[] values = {
	"auto", "baseline", "before-edge",
	"text-before-edge", "middle", "after-edge",
	"text-after-edge", "ideographic", "alphabetic", "hanging", "mathematical",
	"inherit"
    };

    /**
     * Create a new CssAlignmentBaseline
     */
    public AlignmentBaseline() {
	//nothing to do
    }

    /**
     * Create a new CssAlignmentBaseline
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public AlignmentBaseline(ApplContext ac, CssExpression expression) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		albaseline = val;
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
	if (((SVGStyle) style).alignmentBaseline != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGStyle) style).alignmentBaseline = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGStyle) style).getAlignmentBaseline();
	}
	else {
	    return ((SVGStyle) style).alignmentBaseline;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof AlignmentBaseline &&
		albaseline.equals(((AlignmentBaseline) property).albaseline));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "alignment-baseline";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return albaseline;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return albaseline.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return albaseline.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return false;
    }

}
