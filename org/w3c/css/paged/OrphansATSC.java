//
// $Id: OrphansATSC.java,v 1.1 2002-03-13 19:55:21 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.paged;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssNumber;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;


/**
 */
public class OrphansATSC extends PagedProperty {
    
    CssValue value;
    
    /**
     * Create a new OrphansATSC
     */  
    public OrphansATSC() {
	value = new CssNumber(null, 2);
    }
    
    /**
     * Creates a new CssOrphansATSC
     *
     * @param expression the expression of the size
     * @exception InvalidParamException The expression is incorrect
     */  
    public OrphansATSC(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else if (val instanceof CssNumber) {
	    if (((CssNumber) val).isInteger()) {
		value = val;
		expression.next();
		return;
	    } else {
		throw new InvalidParamException("integer", 
						val.toString(), 
						getPropertyName(), ac);
	    }
	} 
	
	throw new InvalidParamException("value", 
					    val.toString(), 
					    getPropertyName(), ac);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value;
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
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
	return "orphans";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.orphansATSC != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	style0.orphansATSC = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getOrphansATSC();
	} else {
	    return ((Css2Style) style).orphansATSC;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	if (value == null) {
	    return (property instanceof OrphansATSC && 
		    ((OrphansATSC) property).value == value);
	} else {
	    return (property instanceof OrphansATSC && 
		    ((OrphansATSC) property).value.equals(value));
	}
    }
    
}
