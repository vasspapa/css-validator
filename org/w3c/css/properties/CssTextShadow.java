//
// $Id: CssTextShadow.java,v 1.1 2002-03-13 19:56:10 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssTextShadow.java,v $
 * Revision 1.1  2002-03-13 19:56:10  plehegar
 * New
 *
 */
package org.w3c.css.properties;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssColor;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision: 1.1 $
 */
public class CssTextShadow extends CssProperty 
        implements CssTextPropertiesConstants {
    
    CssValue value;

    Vector faces = new Vector();

    private static CssIdent none = new CssIdent("none");
    
    /**
     * Create a new CssTextShadow
     */
    public CssTextShadow() {
	value = none;
    }  
    
    /**
     * Create a new CssTextShadow
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssTextShadow(ApplContext ac, CssExpression expression) 
	    throws InvalidParamException {
	CssValue val = expression.getValue();
	
	setByUser();

	if (val.equals(none)) {
	    value = none;
	    expression.next();
	    return;
	} else if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	} else {
	    TextShadowFace face;
	    char op = CssOperator.COMMA;
	    while (op == CssOperator.COMMA) {
		face = new TextShadowFace(ac, expression);
		value = null;
		op = face.op;
		faces.addElement(face);
	    }
	}
	val = null;
	
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	if (value != null) {
	    return value;
	}
	return faces.elementAt(0);
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "text-shadow";
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
	if (value != null) {
	    return value.toString();
	} else {
	    int i = 0;
	    int l = faces.size();
	    String ret = "";
	    while (i != l) {
		ret += new Character(CssOperator.COMMA) 
		    + " " + faces.elementAt(i++);
	    }
	    return ret.substring(2);
	}
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssTextShadow != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.cssTextShadow = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getTextShadow();
	} else {
	    return ((Css1Style) style).cssTextShadow;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	// @@ FIXME
	return false;
    }
}

