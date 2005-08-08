//
// $Id: CssBorderTopStyle.java,v 1.3 2005-08-08 13:18:12 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderTopStyle.java,v $
 * Revision 1.3  2005-08-08 13:18:12  ylafon
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
 * Revision 3.2  1997/09/09 08:52:16  plehegar
 * Added getStyle()
 *
 * Revision 3.1  1997/08/29 13:13:42  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:20  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision: 1.3 $
 */
public class CssBorderTopStyle extends CssProperty {
    
    CssBorderFaceStyle face;
    
    /**
     * Create a new CssBorderTopStyle
     */
    public CssBorderTopStyle() {
	face = new CssBorderFaceStyle();
    }
    
    /**
     * Create a new CssBorderTopStyle with an another CssBorderFaceStyle
     *
     * @param another The another side.
     */
    public CssBorderTopStyle(CssBorderFaceStyle another) {
	setByUser();
	
	face = another;
    }
    
    /**
     * Create a new CssBorderTopStyle
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopStyle(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	face = new CssBorderFaceStyle(ac, expression);
    }
    
    public CssBorderTopStyle(ApplContext ac, CssExpression expression)
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
     * Returns the value
     */
    public String getStyle() {
	if(face != null) {
	    return face.getStyle();
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
	return "border-top-style";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderTop top = ((Css1Style) style).cssBorder.getTop();
	if (top.getStyle() != null)
	    style.addRedefinitionWarning(ac, this);
	top.style = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBorderTopStyle();
	} else {
	    return ((Css1Style) style).cssBorder.getTop().style;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopStyle && 
		face.equals(((CssBorderTopStyle) property).face));
    }
    
}
