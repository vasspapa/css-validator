//
// $Id: PageBreakBefore.java,v 1.3 2005-08-08 13:18:11 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */
package org.w3c.css.paged;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision: 1.3 $
 */
public class PageBreakBefore extends CssProperty 
    implements CssOperator {
    
    int value;
    
    private static String PAGEBREAKBEFORE[] = {
	"auto", "always", "avoid", "left", "right", "inherit" };
    
    private static int[] hash_values;
    
    
    /**
     * Create a new CssPageBreakBefore
     */
    public PageBreakBefore() {
	value = 0;
    }
    
    /**
     * Create a new CssPageBreakBefore
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public PageBreakBefore(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
//	char op = expression.getOperator();
	
	setByUser();
		
	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    
	    for (int i = 0; i < PAGEBREAKBEFORE.length; i++) {
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	    }
	}
	
	throw new InvalidParamException("value", 
					val.toString(), getPropertyName(), ac);
    }
    
    public PageBreakBefore(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
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
	return "page-break-before";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == PAGEBREAKBEFORE.length - 1;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return PAGEBREAKBEFORE[value];
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css2Style style0 = (Css2Style) style;
	if (style0.pageBreakBefore != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.pageBreakBefore = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css2Style) style).getPageBreakBefore();
	} else {
	    return ((Css2Style) style).pageBreakBefore;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof PageBreakBefore 
		&& value == ((PageBreakBefore) property).value);
    }
    
    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */  
    public boolean isDefault() {
	return value == 0;
    }
    
    static {
	hash_values = new int[PAGEBREAKBEFORE.length];
	for (int i=0; i<PAGEBREAKBEFORE.length; i++)
	    hash_values[i] = PAGEBREAKBEFORE[i].hashCode();
    }
}
