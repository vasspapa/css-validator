//
// $Id: CssBackgroundImageCSS2.java,v 1.1 2002-03-13 19:56:09 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBackgroundImageCSS2.java,v $
 * Revision 1.1  2002-03-13 19:56:09  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:30  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:13  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:03  plehegar
 * Nothing
 *
 * Revision 1.5  1997/08/06 17:29:48  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.4  1997/07/30 13:19:45  plehegar
 * Updated package
 *
 * Revision 1.3  1997/07/23 23:38:35  plehegar
 * bug fix
 *
 * Revision 1.2  1997/07/23 23:34:19  plehegar
 * bug fix "none"
 *
 * Revision 1.1  1997/07/22 17:51:17  plehegar
 * Initial revision
 *
 */

package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssURL;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'background-image'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;url&gt; | none<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> This property sets the background image of an element. When setting a
 *   background image, one should also set a background color that will be used
 *   when the image is unavailable. When the image is available, it is overlaid
 *   on top of the background color.
 *   <PRE>
 *   BODY { background-image: url(marble.gif) }
 *   P { background-image: none }
 *   </PRE>
 * @version $Revision: 1.1 $ */
public class CssBackgroundImageCSS2 extends CssProperty {
    
    CssValue url;
    
    private static CssIdent none = new CssIdent("none");

    /**
     * Create a new CssBackgroundImageCSS2
     */
    public CssBackgroundImageCSS2() {
	url = none;
    }  
    
    /**
     * Creates a new CssBackgroundImageCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssBackgroundImageCSS2(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	
	setByUser();

	CssValue val = expression.getValue();
	if (val instanceof CssURL) {
	    url = val;
	    expression.next();
	} else if (val.equals(inherit)) {
	    url = inherit;
	    expression.next();
	} else if (val.equals(none)) {
	    url = none;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(), 
					    getPropertyName(), ac);
	}
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return url;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return url.equals(inherit);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return url.toString();
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "background-image";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBackgroundCSS2 cssBackground = ((Css1Style) style).cssBackgroundCSS2;
	if (cssBackground.image != null)
	    style.addRedefinitionWarning(ac, this);
	cssBackground.image = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackgroundImageCSS2();
	} else {
	    return ((Css1Style) style).cssBackgroundCSS2.image;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBackgroundImageCSS2 && 
		url.equals(((CssBackgroundImageCSS2) property).url));
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return url == none;
    }
    
}
