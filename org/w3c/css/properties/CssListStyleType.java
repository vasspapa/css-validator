//
// $Id: CssListStyleType.java,v 1.1 2002-03-13 19:56:10 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 14th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssListStyleType.java,v $
 * Revision 1.1  2002-03-13 19:56:10  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:53  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:25  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:25  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:09  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:08  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 14:41:43  plehegar
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
 *      &nbsp;&nbsp; 'list-style-type'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> disc | circle | square | decimal | lower-roman | 
 *   upper-roman | lower-alpha | upper-alpha | none
 *   check | diamond | menu-check | radio | radio-on | radio-off | radio-ind | 
 *   enabled-radio-on | enabled-radio-off | enabled-radio-ind | 
 *   disabled-radio-on | disabled-radio-off | disabled-radio-ind | 
 *   active-radio-off | active-radio-on | active-radio-ind | 
 *   hover-radio-off | hover-radio-on | hover-radio-ind | 
 *   checkbox | checkbox-on | checkbox-off | checkbox-ind | 
 *   enabled-checkbox-on | enabled-checkbox-off | enabled-checkbox-ind | 
 *   disabled-checkbox-on | disabled-checkbox-off | disabled-checkbox-ind | 
 *   active-checkbox-on | active-checkbox-off | active-checkbox-ind | 
 *   hover-checkbox-on | hover-checkbox-off | hover-checkbox-ind
 <BR>
 *   <EM>Initial:</EM> disc<BR>
 *   <EM>Applies to:</EM> elements with 'display' value 'list-item'<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> This property is used to determine the appearance of the list-item
 *   marker if 'list-style-image' is 'none' or if the image pointed to by the
 *   URL cannot be displayed.
 *   <PRE>
 *   OL { list-style-type: decimal }       /* 1 2 3 4 5 etc. * /
					    *   OL { list-style-type: lower-alpha }   /* a b c d e etc. * /
										       *   OL { list-style-type: lower-roman }   /* i ii iii iv v etc. * /
																  *   </PRE>
																  * @version $Revision: 1.1 $ */
public class CssListStyleType extends CssProperty 
    implements CssListStyleConstants {
    
    int value;
    
    private static int[] hash_values;
    
    /**
     * Create a new CssListStyleType
     */
    public CssListStyleType() {
	// nothing to do
    }  
    
    /**
     * Create a new CssListStyleType
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssListStyleType(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	
	setByUser();
	
	if ( val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < LISTSTYLETYPE.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}
	
	throw new InvalidParamException("value", val, getPropertyName(), ac);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return LISTSTYLETYPE[value];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "list-style-type";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == (LISTSTYLETYPE.length - 1);
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return LISTSTYLETYPE[value];
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssListStyle cssListStyle = ((Css1Style) style).cssListStyle;
	if (cssListStyle.listStyleType != null)
	    style.addRedefinitionWarning(ac, this);
	cssListStyle.listStyleType = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getListStyleType();
	} else {
	    return ((Css1Style) style).cssListStyle.listStyleType;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssListStyleType && 
		((CssListStyleType) property).value == value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    static {
	hash_values = new int[LISTSTYLETYPE.length];
	for (int i = 0; i < LISTSTYLETYPE.length; i++)
	    hash_values[i] = LISTSTYLETYPE[i].hashCode();
    }
}
