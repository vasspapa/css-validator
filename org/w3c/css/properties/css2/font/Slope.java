//
// $Id: Slope.java,v 1.1 2005-08-23 16:33:50 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.properties.css2.font;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 */
public class Slope extends CssProperty {
    
    CssValue value;
    
    /**
     * Create a new Slope
     */
    public Slope() {
	// nothing to do
    }
    
    /**
     * Creates a new Slope
     *
     * @param expression the unicode em
     * @exception InvalidParamException values are incorrect
     */  
    public Slope(ApplContext ac, CssExpression expression, boolean check)
    	throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();

	if (val instanceof CssNumber) {
	    value = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(), 
					    getPropertyName(), ac);
	}
    }
    
    public Slope(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {  
	return value.toString();
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "slope";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.slope != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.slope = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getSlope();
	} else {
	    return ((Css2Style) style).slope;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	// @@TODO
	return false;
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return false;
    }
    
}
