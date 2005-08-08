//
// $Id: CssBackgroundColorMob.java,v 1.3 2005-08-08 13:18:12 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBackgroundColorMob.java,v $
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
 * Revision 1.2  2002/04/08 21:17:42  plehegar
 * New
 *
 * Revision 3.3  1997/09/09 13:03:57  plehegar
 * Added getColor()
 *
 * Revision 3.2  1997/09/08 14:03:45  plehegar
 * Suppressed a conflict
 *
 * Revision 3.1  1997/08/29 13:13:29  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:12  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:03  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:29:47  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:19:44  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/22 11:20:50  plehegar
 * Initial revision
 *
 */

package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'background-color'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;color&gt; | transparent<BR>
 *   <EM>Initial:</EM> transparent<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property sets the background color of an element.
 *   <PRE>
 *   H1 { background-color: #F00 }
 *   </PRE>
 * @version $Revision: 1.3 $
 */
public class CssBackgroundColorMob extends CssProperty {
    
    CssValue color;
    
    static CssIdent transparent = new CssIdent("transparent");
    
    /**
     * Create a new CssBackgroundColorMob
     */
    public CssBackgroundColorMob() {
	color = transparent;
    }  
    
    /**
     * Create a new CssBackgroundColorMob
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssBackgroundColorMob(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	CssValue val = expression.getValue();
	
	if (val instanceof org.w3c.css.values.CssColorCSS2) {
	    color = val;
	    expression.next();
	} else if (val instanceof CssIdent) {
	    if (val.equals(transparent)) {
		color = transparent;
		expression.next();
	    } else if (val.equals(inherit)) {
		color = inherit;
		expression.next();
	    } else {
		color = new org.w3c.css.values.CssColorCSS2(ac, (String) val.get());
		expression.next();
	    }
	} else {
	    throw new InvalidParamException("value", val.toString(), 
					    getPropertyName(), ac);
	}
    }  
    
    public CssBackgroundColorMob(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }
    
    /**
     * Returns the color
     */
    public final CssValue getColor() {
	return color;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	if (color != null) {
	    return color.equals(inherit);
	}
	return false;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (color != null) {
	    return color.toString();
	}
	return "";
    }
    
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBackgroundMob cssBackground = ((Css1Style) style).cssBackgroundMob;
	if (cssBackground.color != null)
	    style.addRedefinitionWarning(ac, this);
	cssBackground.color = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackgroundColorMob();
	} else {
	    return ((Css1Style) style).cssBackgroundMob.color;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundColorCSS2 && color != null &&
		color.equals( ((CssBackgroundColorCSS2) property).color));
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "background-color";
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return color == transparent;
    }
    
}
