//
// $Id: CssBorderTopWidthATSC.java,v 1.2 2005-08-08 13:18:03 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderTopWidthATSC.java,v $
 * Revision 1.2  2005-08-08 13:18:03  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.1  2002/07/24 14:42:28  sijtsche
 * ATSC TV profile files
 *
 * Revision 1.1  2002/05/31 09:00:16  dejong
 * ATSC TV profile objects
 *
 * Revision 3.2  1997/09/09 08:45:08  plehegar
 * Added getValue()
 *
 * Revision 3.1  1997/08/29 13:13:42  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:20  plehegar
 * Freeze
 *
 */
package org.w3c.css.atsc;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'border-top-width'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> thin | medium | thick | &lt;length&gt;<BR>
 *   <EM>Initial:</EM> 'medium'<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property sets the width of an element's top border. The width of the
 *   keyword values are UA dependent, but the following holds: 'thin' &lt;= 'medium'
 *   &lt;= 'thick'.
 *   <P>
 *   The keyword widths are constant throughout a document:
 *   <PRE>
 *   H1 { border: solid thick red }
 *   P  { border: solid thick blue }
 * </PRE>
 *   <P>
 *   In the example above, 'H1' and 'P' elements will have the same border width
 *   regardless of font size. To achieve relative widths, the 'em' unit can be
 *   used:
 *   <PRE>
 *   H1 { border: solid 0.5em }
 * </PRE>
 *   <P>
 *   Border widths cannot be negative.
 * @version $Revision: 1.2 $
 */
public class CssBorderTopWidthATSC extends CssProperty {
    
    CssBorderFaceWidthATSC face;
    
    /**
     * Create a new CssBorderTopWidth
     */
    public CssBorderTopWidthATSC() {
	face = new CssBorderFaceWidthATSC();
    }
    
    /**
     * Create a new CssBorderTopWidth with an another CssBorderFaceWidth
     *
     * @param another The another side.
     */
    public CssBorderTopWidthATSC(CssBorderFaceWidthATSC another) {
	setByUser();
	
	face = another;
    }
    
    /**
     * Create a new CssBorderTopWidthATSC
     *
     * @param expression The expression for this property.
     * @exception InvalidParamException Values are incorrect
     */
    public CssBorderTopWidthATSC(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	face = new CssBorderFaceWidthATSC(ac, expression);
    }
    
    public CssBorderTopWidthATSC(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return face;
    }
    
    /**
     * Return the value of this property
     */
    public CssValue getValue() {
	return face.getValue();
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return face.toString();
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "border-top-width";
    }
    
    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssBorderTopATSC top = ((ATSCStyle) style).cssBorderATSC.top;

	if (top.width != null) {
	    style.addRedefinitionWarning(ac, this);
	}
	top.width = this;
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ATSCStyle) style).getBorderTopWidthATSC();
	} else {
	    return ((ATSCStyle) style).cssBorderATSC.getTop().width;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return (property instanceof CssBorderTopWidthATSC &&
		face.equals(((CssBorderTopWidthATSC) property).face));
    }
    
}
