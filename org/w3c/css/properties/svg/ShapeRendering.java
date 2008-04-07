//
// $Id: ShapeRendering.java,v 1.3 2008-04-07 14:16:24 ylafon Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *  <P>
 *  <EM>Value:</EM> auto | optimizeSpeed | crispEdges |
 *  geometricPrecision | inherit  <BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM><BR>
 *  <EM>Inherited:</EM>yes<BR>
 *  <EM>Percentages:</EM>no<BR>
 *  <EM>Media:</EM>:visual
 */

public class ShapeRendering extends CssProperty {

    CssValue value;

    static CssIdent auto = new CssIdent("auto");

    private static String[] values = {
	"auto", "optimizeSpeed", "crispEdges", "geometricPrecision", "inherit"
    };

    /**
     * Create a new ShapeRendering
     */
    public ShapeRendering() {
	// nothing to do
    }

    /**
     * Create a new ShapeRendering
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public ShapeRendering(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	int i = 0;
	for (; i < values.length; i++) {
	    if (val.toString().equals(values[i])) {
		value = val;
		expression.next();
		break;
	    }
	}
	if (i == values.length) {
	    throw new InvalidParamException("value", expression.getValue(),
		    getPropertyName(), ac);
	}
    }

    public ShapeRendering(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((SVGBasicStyle) style).shapeRendering != null)
	    style.addRedefinitionWarning(ac, this);
	((SVGBasicStyle) style).shapeRendering = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((SVGBasicStyle) style).getShapeRendering();
	}
	else {
	    return ((SVGBasicStyle) style).shapeRendering;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ShapeRendering &&
		value.equals(((ShapeRendering) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "shape-rendering";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (value == auto);
    }

}
