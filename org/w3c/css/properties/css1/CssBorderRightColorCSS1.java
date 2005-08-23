//
// $Id: CssBorderRightColorCSS1.java,v 1.1 2005-08-23 16:23:12 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderRightColorCSS1.java,v $
 * Revision 1.1  2005-08-23 16:23:12  ylafon
 * Patch by Jean-Guilhem Rouel
 *
 * Better handling of media and properties files
 * Major reorganization of those properties files
 *
 * Revision 1.3  2005/08/08 13:18:12  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 3.3  1997/09/09 10:52:03  plehegar
 * bugs
 *
 * Revision 3.2  1997/09/09 10:51:43  plehegar
 * Added getColor()
 *
 * Revision 3.1  1997/08/29 13:13:38  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:19  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision: 1.1 $
 */
public class CssBorderRightColorCSS1 extends CssProperty {
    
    CssBorderFaceColorCSS1 face;
    
    /**
     * Create a new CssBorderRightColorCSS1
     */
    public CssBorderRightColorCSS1() {
	face = new CssBorderFaceColorCSS1();
    }
    
    /**
     * Create a new CssBorderRightColorCSS1 with an another CssBorderFaceColorCSS1
     *
     * @param another The another side.
     */
    public CssBorderRightColorCSS1(CssBorderFaceColorCSS1 another) {
	
	setByUser();
	
	face = another;
    }
    
    /**
     * Create a new CssBorderRightColorCSS1
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderRightColorCSS1(ApplContext ac, CssExpression expression,
	    boolean check) 
    throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	face = new CssBorderFaceColorCSS1(ac, expression);
    }
    
    public CssBorderRightColorCSS1(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
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
	return "border-right-color";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderRightCSS1 right = ((Css1Style) style).cssBorderCSS1.right;
	if (right.color != null)
	    style.addRedefinitionWarning(ac, this);
	right.color = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderRightColorCSS1();
	} else {
	    return ((Css1Style) style).cssBorderCSS1.getRight().color;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderRightColorCSS1 && 
		face.equals(((CssBorderRightColorCSS1) property).face));
    }
    
    /**
     * Print this property.
     *
     * @param printer The printer.
     */  
    public void print(CssPrinterStyle printer) {
	if (face != null && !face.isDefault())
	    printer.print(this);
    }
}
