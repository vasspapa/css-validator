//
// $Id: CssBorderLeftColor.java,v 1.4 2010-01-05 13:49:41 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;


/**
 * Be careful, this is not a CSS1 property !
 *
 * @version $Revision: 1.4 $
 */
public class CssBorderLeftColor extends CssProperty {

    CssBorderFaceColor face;

    /**
     * Create a new CssBorderLeftColor
     */
    public CssBorderLeftColor() {
	face = new CssBorderFaceColor();
    }

    /**
     * Create a new CssBorderLeftColor with an another CssBorderFaceColor
     * @param another The another side.
     */
    public CssBorderLeftColor(CssBorderFaceColor another) {

	setByUser();

	face = another;
    }

    /**
     * Create a new CssBorderLeftColor
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderLeftColor(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	face = new CssBorderFaceColor(ac, expression);
    }

    public CssBorderLeftColor(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }

    /**
     * Returns the color of this property
     */
    public CssValue getColor() {
	if(face != null) {
	    return face.getColor();
	}
	return null;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if(face != null) {
	    return face.toString();
	}
	return "";
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "border-left-color";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderLeft left = ((Css1Style) style).cssBorder.left;
	if (left.color != null)
	    style.addRedefinitionWarning(ac, this);
	left.color = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderLeftColor();
	} else {
	    return ((Css1Style) style).cssBorder.getLeft().color;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderLeftColor && face.equals(((CssBorderLeftColor) property).face));
    }

    /**
     * Print this property
     *
     * @param printer The printer.
     */
    public void print(CssPrinterStyle printer) {
	if (face != null && !face.isDefault())
	    printer.print(this);
    }
}
