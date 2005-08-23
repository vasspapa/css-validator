//
// $Id: CssBorderWidthATSC.java,v 1.1 2005-08-23 16:23:11 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderWidthATSC.java,v $
 * Revision 1.1  2005-08-23 16:23:11  ylafon
 * Patch by Jean-Guilhem Rouel
 *
 * Better handling of media and properties files
 * Major reorganization of those properties files
 *
 * Revision 1.2  2005/08/08 13:18:03  ylafon
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
 * Revision 3.1  1997/08/29 13:13:43  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 13:58:13  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:20  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:13  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:29:57  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:19:53  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 11:20:18  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.atsc;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'border-width'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> [thin | medium | thick | &lt;length&gt;]{1,4}<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property is a shorthand property for setting 'border-width-top',
 *   'border-width-right', 'border-width-bottom' and 'border-width-left' at the
 *   same place in the style sheet.
 *   <P>
 *   There can be from one to four values, with the following interpretation:
 *   <UL>
 *     <LI>
 *       one value: all four border widths are set to that value
 *     <LI>
 *       two values: top and bottom border widths are set to the first value, right
 *       and left are set to the second
 *     <LI>
 *       three values: top is set to the first, right and left are set to the second,
 *       bottom is set to the third
 *     <LI>
 *       four values: top, right, bottom and left, respectively
 *   </UL>
 *   <P>
 *   In the examples below, the comments indicate the resulting widths of the
 *   top, right, bottom and left borders:
 *   <PRE>
 *   H1 { border-width: thin }                   / * thin thin thin thin * /
 *   H1 { border-width: thin thick }             / * thin thick thin thick * /
 *   H1 { border-width: thin thick medium }      / * thin thick medium thin * /
 *   H1 { border-width: thin thick medium 12cm } / * thin thick medium 12cm * /
 * </PRE>
 *   <P>
 *   Border widths cannot be negative.
 * @version $Revision: 1.1 $
 */
public class CssBorderWidthATSC extends CssProperty implements CssOperator {
    
    CssBorderTopWidthATSC top;
    CssBorderBottomWidthATSC bottom;
    CssBorderRightWidthATSC right;
    CssBorderLeftWidthATSC left;
    
    /**
     * Create a new CssBorderWidthATSC
     */
    public CssBorderWidthATSC(CssBorderTopWidthATSC top,
			  CssBorderBottomWidthATSC bottom,
			  CssBorderRightWidthATSC right,
			  CssBorderLeftWidthATSC left) {
	this.top = top;
	this.bottom = bottom;
	this.left = left;
	this.right = right;
    }  
    
    /**
     * Create a new CssBorderATSC
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */  
    public CssBorderWidthATSC(ApplContext ac, CssExpression expression,
	    boolean check)  throws InvalidParamException {

	setByUser();
	switch (expression.getCount()) {
	case 1:
	    top = new CssBorderTopWidthATSC(ac, expression);
	    bottom = new CssBorderBottomWidthATSC((CssBorderFaceWidthATSC) top.get());
	    right = new CssBorderRightWidthATSC((CssBorderFaceWidthATSC) top.get());
	    left = new CssBorderLeftWidthATSC((CssBorderFaceWidthATSC) top.get());
	    break;
	case 2:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidthATSC(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidthATSC(ac, expression);
	    bottom = new CssBorderBottomWidthATSC((CssBorderFaceWidthATSC) top.get());
	    left = new CssBorderLeftWidthATSC((CssBorderFaceWidthATSC) right.get());
	    break;
	case 3:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidthATSC(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidthATSC(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomWidthATSC(ac, expression);
	    left = new CssBorderLeftWidthATSC((CssBorderFaceWidthATSC) right.get());
	    break;
	case 4:
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    top = new CssBorderTopWidthATSC(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    right = new CssBorderRightWidthATSC(ac, expression);
	    if (expression.getOperator() != SPACE)
		throw new InvalidParamException("operator", 
						((new Character(expression.getOperator())).toString()),
						ac);
	    if(expression.getValue().equals(inherit)) {		
		throw new InvalidParamException("unrecognize", ac);
	    }
	    bottom = new CssBorderBottomWidthATSC(ac, expression);
	    if(expression.getValue().equals(inherit)) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	    left = new CssBorderLeftWidthATSC(ac, expression);
	    break;
	default:
	    if(check) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
    }
    
    public CssBorderWidthATSC(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
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
	return "border-width";
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
	    top.setImportant();
	}
	if(right != null) {
	    right.setImportant();
	}
	if(left != null) {
	    left.setImportant();	    
	}
	if(bottom != null) {
	    bottom.setImportant();
	}
    }
    
    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((top == null || top.getImportant()) &&
		(right == null || right.getImportant()) &&
		(left == null || left.getImportant()) &&
		(bottom == null || bottom.getImportant()));
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
	     (!top.getImportant() &&
	      !right.getImportant() &&
	      !left.getImportant() &&
	      !bottom.getImportant()))) {
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
	return false;
    }
    
}
