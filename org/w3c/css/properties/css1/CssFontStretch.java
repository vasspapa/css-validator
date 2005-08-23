//
// $Id: CssFontStretch.java,v 1.1 2005-08-23 16:23:12 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssFontStretch.java,v $
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
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;

/**
 *
 * @see CssFont
 * @version $Revision: 1.1 $ 
 */
public class CssFontStretch extends CssProperty implements CssFontConstant {
    
    int value;
    
    private static int[] hash_values;
    
    /**
     * Create a new CssFontStretch
     */
    public CssFontStretch() {
	// nothing to do
    }
    
    /**
     * Creates a new CssFontStretch
     *
     * @param expression the font stretch
     * @exception InvalidParamException Values are incorrect
     */  
    public CssFontStretch(ApplContext ac, CssExpression expression,
	    boolean check) 
	throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	if (expression.getValue() instanceof CssIdent) {
	    int hash = expression.getValue().hashCode();
	    for (int i=0; i<hash_values.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}
	
	throw new InvalidParamException("value", expression.getValue(), 
					getPropertyName(), ac);
    }
    
    public CssFontStretch(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return FONTSTRETCH[value];
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == FONTSTRETCH.length - 1;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return FONTSTRETCH[value];
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "font-stretch";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssFontStretch != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssFontStretch = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFontStretch();
	} else {
	    return ((Css1Style) style).cssFontStretch;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontStretch && 
		((CssFontStretch) property).value == value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    static {
	hash_values = new int[FONTSTRETCH.length];
	for (int i=0;i<FONTSTRETCH.length;i++)
	    hash_values[i] = FONTSTRETCH[i].hashCode();
    }
}
