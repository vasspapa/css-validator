//
// $Id: StemvATSC.java,v 1.1 2002-07-24 14:42:28 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.atsc;

import java.util.Vector;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssNumber;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 */
public class StemvATSC extends CssProperty {
    
    CssValue value;
    
    /**
     * Create a new StemvATSC
     */
    public StemvATSC() {
	// nothing to do
    }
    
    /**
     * Creates a new StemvATSC
     *
     * @param expression the unicode em
     * @exception InvalidParamException values are incorrect
     */  
    public StemvATSC(ApplContext ac, CssExpression expression) throws InvalidParamException {
	CssValue val = expression.getValue();
	setByUser();

	ac.getFrame().addWarning("atsc", val.toString());

	if (val instanceof CssNumber) {
	    value = val;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(), 
					    getPropertyName(), ac);
	}
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
	return "stemv";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	ATSCStyle style0 = (ATSCStyle) style;
	if (style0.stemvATSC != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.stemvATSC = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getStemvATSC();
	} else {
	    return ((ATSCStyle) style).stemvATSC;
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
