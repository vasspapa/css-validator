//
// $Id: Page.java,v 1.1 2002-03-13 19:55:21 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.paged;
import java.util.Vector;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssOperator;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision: 1.1 $
 */
public class Page extends CssProperty 
    implements CssOperator {
    
    CssValue value;
    CssValue pseudo;

    private static CssIdent auto = new CssIdent("auto");
    
    
    /**
     * Create a new CssPage
     */
    public Page() {
	value = auto;
    }
    
    /**
     * Create a new CssPage
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public Page(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	CssValue val = expression.getValue();
	char op = expression.getOperator();
	
	setByUser();
		
	if (val.equals(auto)) {
	    value = val;
	    expression.next();
	} else if (val instanceof CssIdent) {
	    value = val;
	    expression.next();
	    if (!expression.end()) {
		val = expression.getValue();
		if ((op == SPACE) && (val instanceof CssIdent)) {
		    pseudo = val;
		    expression.next();
		    return;
		} else {
		    throw new InvalidParamException("value", 
						    val.toString(), 
						    getPropertyName(), ac);
		}
	    }
	} else {
	    throw new InvalidParamException("value", 
					    val.toString(), getPropertyName(), ac);
	}
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return null;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "page";
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (pseudo != null) {
	    return value + " " + pseudo;
	} else {
	    return value.toString();
	}
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.page != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.page = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getPage();
	} else {
	    return ((Css2Style) style).page;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof Page 
		&& value == ((Page) property).value
		&& ((pseudo == null)
		    || pseudo.equals(((Page) property).pseudo)));
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == auto;
    }
    
}
