//
// $Id: CssBorderStyleCSS2.java,v 1.3 2005-08-08 13:18:12 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderStyleCSS2.java,v $
 * Revision 1.3  2005-08-08 13:18:12  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:13:40  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 13:59:15  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:19  plehegar
 * Freeze
 *
 */

package org.w3c.css.properties;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;

/**
 *   <H4>
 *      &nbsp;&nbsp; 'border-style'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> none | dotted | dashed | solid | double | groove | ridge
 *   | inset | outset<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   The 'border-style' property sets the style of the four borders. It can have
 *   from one to four values, and the values are set on the different sides as
 *   for 'border-width' above.
 *   <PRE>
 *   #xy34 { border-style: solid dotted }
 * </PRE>
 *   <P>
 *   In the above example, the horizontal borders will be 'solid' and the vertical
 *   borders will be 'dotted'.
 *   <P>
 *   Since the initial value of the border styles is 'none', no borders will be
 *   visible unless the border style is set.
 *   <P>
 *   The border styles mean:
 *   <DL>
 *     <DT>
 *       none
 *     <DD>
 *       no border is drawn (regardless of the 'border-width' value)
 *     <DT>
 *       dotted
 *     <DD>
 *       the border is a dotted line drawn on top of the background of the element
 *     <DT>
 *       dashed
 *     <DD>
 *       the border is a dashed line drawn on top of the background of the element
 *     <DT>
 *       solid
 *     <DD>
 *       the border is a solid line
 *     <DT>
 *       double
 *     <DD> the border is a double line drawn on top of the background of the
 *     element.  The sum of the two single lines and the space between equals
 *     the &lt;border-width&gt; value.
 *     <DT>
 *       groove
 *     <DD>
 *       a 3D groove is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       ridge
 *     <DD>
 *       a 3D ridge is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       inset
 *     <DD>
 *       a 3D inset is drawn in colors based on the &lt;color&gt; value.
 *     <DT>
 *       outset
 *     <DD>
 *       a 3D outset is drawn in colors based on the &lt;color&gt; value.
 *   </DL>
 *   <P>
 *   <EM>CSS1 core:</EM> UAs may interpret all of 'dotted', 'dashed', 'double',
 *   'groove', 'ridge', 'inset' and 'outset' as 'solid'.
 *
 * @version $Revision: 1.3 $ 
 */
public class CssBorderStyleCSS2 extends CssProperty implements CssOperator {
    
    CssBorderTopStyleCSS2 top;
    CssBorderBottomStyleCSS2 bottom;
    CssBorderRightStyleCSS2 right;
    CssBorderLeftStyleCSS2 left;
    
    /**
     * Create a new CssBorderStyleCSS2
     */
    public CssBorderStyleCSS2(CssBorderTopStyleCSS2 top,
	    CssBorderBottomStyleCSS2 bottom,
	    CssBorderRightStyleCSS2 right,
	    CssBorderLeftStyleCSS2 left) {
	this.top = top;
	this.bottom = bottom;
	this.left = left;
	this.right = right;
    }  
    
    /**
     * Create a new CssBorderStyleCSS2
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssBorderStyleCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	setByUser();
	
	switch (expression.getCount()) {
	case 1:
	    top = new CssBorderTopStyleCSS2(ac, expression);
	    bottom = new CssBorderBottomStyleCSS2((CssBorderFaceStyleCSS2) top.get());
	    right = new CssBorderRightStyleCSS2((CssBorderFaceStyleCSS2) top.get());
	    left = new CssBorderLeftStyleCSS2((CssBorderFaceStyleCSS2) top.get());
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopStyleCSS2(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightStyleCSS2(ac, expression);
	    bottom = new CssBorderBottomStyleCSS2((CssBorderFaceStyleCSS2) top.get());
	    left = new CssBorderLeftStyleCSS2((CssBorderFaceStyleCSS2) right.get());
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopStyleCSS2(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightStyleCSS2(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomStyleCSS2(ac, expression);
	    left = new CssBorderLeftStyleCSS2((CssBorderFaceStyleCSS2) right.get());
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopStyleCSS2(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightStyleCSS2(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
			((new Character(expression.getOperator())).toString()),
			ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomStyleCSS2(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    left = new CssBorderLeftStyleCSS2(ac, expression);
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }
    
    public CssBorderStyleCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression,false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return top;
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "border-style";
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {      
	if (right.face.equals(left.face)) {
	    if (top.face.equals(bottom.face)) {
		if (top.face.equals(right.face)) {
		    return top.toString();
		} else {
		    return top + " " + right;
		}
	    } else {
		return top + " " + right + " " + bottom;
	    }
	} else {
	    return top + " " + right + " " + bottom + " " + left;
	}
    }
    
    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */  
    public void setImportant() {
	if(top != null) {
	    top.important = true;
	}
	if(right != null) {
	    right.important = true;
	}
	if(left != null) {
	    left.important = true;
	}
	if(bottom != null) {
	    bottom.important = true;
	}
    }
    
    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((top == null || top.important) &&
		(right == null || right.important) &&
		(left == null || left.important) &&
		(bottom == null || bottom.important));
    }
    
    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */  
    public void print(CssPrinterStyle printer) {
	if ((top != null && right != null &&
		left != null && bottom != null) &&
		(getImportant() ||
			(!top.important &&
				!right.important &&
				!left.important &&
				!bottom.important))) {
	    printer.print(this);
	} else {
	    if (top != null)
		top.print(printer);
	    if (right != null)
		right.print(printer);
	    if (left != null)
		left.print(printer);
	    if (bottom != null)
		bottom.print(printer);
	}
	
    }
    
    /**
     * Set the context.
     * Overrides this method for a macro
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     * @see org.w3c.css.css.StyleSheetParser#handleRule
     */
    public void setSelectors(CssSelectors selector) {
	super.setSelectors(selector);
	if (top != null) {
	    top.setSelectors(selector);
	}
	if (right != null) {
	    right.setSelectors(selector);
	}
	if (bottom != null) {
	    bottom.setSelectors(selector);
	}
	if (left != null) {
	    left.setSelectors(selector);
	}
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if(top != null) {
	    top.addToStyle(ac, style);
	}
	if(right != null) {
	    right.addToStyle(ac, style);
	}
	if(left != null) {
	    left.addToStyle(ac, style);
	}
	if(bottom != null) {
	    bottom.addToStyle(ac, style);
	}
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	throw new IllegalStateException("Can't invoke this method on the property " + 
		getPropertyName());
    }
    
    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line The line number where this property is defined
     * @param source The source file where this property is defined
     */  
    public void setInfo(int line, String source) {
	super.setInfo(line, source);
	if(top != null) {
	    top.setInfo(line, source);
	}
	if(right != null) {
	    right.setInfo(line, source);
	}
	if(left != null) {
	    left.setInfo(line, source);
	}
	if(bottom != null) {
	    bottom.setInfo(line, source);
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return false; // FIXME
    }
    
}
