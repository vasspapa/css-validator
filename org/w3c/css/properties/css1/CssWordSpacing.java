//
// $Id: CssWordSpacing.java,v 1.1 2005-08-23 16:23:12 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssWordSpacing.java,v $
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
 * Revision 3.1  1997/08/29 13:14:08  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:31  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:40  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:30:25  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:24  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/23 21:35:36  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'word-spacing'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> normal | &lt;length&gt; <BR>
 *   <EM>Initial:</EM> normal<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> The length unit indicates an addition to the default space between
 *   words.  Values can be negative, but there may be implementation-specific
 *   limits.  The UA is free to select the exact spacing algorithm. The word
 *   spacing may also be influenced by justification (which is a value of the
 *   'align' property).
 *   <PRE>
 *   H1 { word-spacing: 0.4em }
 * </PRE>
 *   <P>
 *   Here, the word-spacing between each word in 'H1' elements would be increased
 *   by '1em'.
 *
 * @version $Revision: 1.1 $ 
 */
public class CssWordSpacing extends CssProperty {
    
    private CssValue length;
    private static CssIdent normal = new CssIdent("normal");
    
    /**
     * Create a new CssWordSpacing.
     */
    public CssWordSpacing() {
	length = normal;
    }  
    
    /**
     * Create a new CssWordSpacing with an expression
     *
     * @param expression The expression
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssWordSpacing(ApplContext ac, CssExpression expression,
	boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	
	setByUser();
	
	if (val instanceof CssLength) {
	    length = (CssLength) val;
	    expression.next();
	} else if (val instanceof CssNumber) {
	    length = ((CssNumber) val).getLength();
	    expression.next();
	} else if (val.equals(inherit)) {
	    length = inherit;
	    expression.next();
	} else if (val.equals(normal)) {
	    length = normal;
	    expression.next();
	} else {
	    throw new InvalidParamException("value", expression.getValue(), 
					    getPropertyName(), ac);
	}
    }
    
    public CssWordSpacing(ApplContext ac, CssExpression expression) 
    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return length;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "word-spacing";
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return length == inherit;
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return length.toString();
    }
    
    /**
     * Adds this property to a style.
     *
     * @param style The style.
     */  
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css1Style style0 = (Css1Style) style;
	if (style0.cssWordSpacing != null)
	    style0.addRedefinitionWarning(ac, this);
	style0.cssWordSpacing = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getWordSpacing();
	} else {
	    return ((Css1Style) style).cssWordSpacing;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssWordSpacing && 
		length.equals(((CssWordSpacing) property).length));
    }    
}
