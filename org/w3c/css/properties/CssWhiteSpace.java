//
// $Id: CssWhiteSpace.java,v 1.1 2002-03-13 19:56:10 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssWhiteSpace.java,v $
 * Revision 1.1  2002-03-13 19:56:10  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:14:07  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:30  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:38  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:24  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:23  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 14:30:25  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'white-space'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> normal | pre | nowrap<BR>
 *   <EM>Initial:</EM> normal<BR>
 *   <EM>Applies to:</EM> block-level elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *
 *   <P> This property declares how whitespace inside the element is handled:
 *   the 'normal' way (where whitespace is collapsed), as 'pre' (which behaves
 *   like the 'PRE' element in HTML) or as 'nowrap' (where wrapping is done only
 *   through BR elements):
 *
 *   <PRE>
 *   PRE { white-space: pre }
 *   P   { white-space: normal }
 * </PRE>
 *
 *   <P> The initial value of 'white-space' is 'normal', but a UA will typically
 *   have default values for all HTML elements according to the suggested
 *   rendering of elements in the HTML specification.
 *
 * @version $Revision: 1.1 $ 
 */
public class CssWhiteSpace extends CssProperty {
    
    int value;
    
    private static String[] WHITESPACE = {
	"normal", "pre", "nowrap", "inherit" };
    private static int[] hash_values;
    
    /**
     * Create a new CssWhiteSpace
     */
    public CssWhiteSpace() {
	// nothing to do
    }
    
    /**
     * Create a new CssWhiteSpace
     *
     * @param expression The expression for this property
     * @exception InvalidParamException values are incorrect
     */  
    public CssWhiteSpace(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	
	setByUser();
	
	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < WHITESPACE.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}
	
	throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
    }
    
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return WHITESPACE[value];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "white-space";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == (WHITESPACE.length - 1);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return WHITESPACE[value];
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssWhiteSpace != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssWhiteSpace = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getWhiteSpace();
	} else {
	    return ((Css1Style) style).cssWhiteSpace;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssWhiteSpace && 
		value == ((CssWhiteSpace) property).value);
    }
    
    static {
	hash_values = new int[WHITESPACE.length];
	for (int i = 0; i < WHITESPACE.length; i++)
	    hash_values[i] = WHITESPACE[i].hashCode();
    }
}
