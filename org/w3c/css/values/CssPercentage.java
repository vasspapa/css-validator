//
// $Id: CssPercentage.java,v 1.3 2005-08-08 13:19:47 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssPercentage.java,v $
 * Revision 1.3  2005-08-08 13:19:47  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:19:46  plehegar
 * New
 *
 * Revision 2.2  1997/08/20 11:39:18  plehegar
 * Added equals
 *
 * Revision 2.1  1997/08/08 15:53:06  plehegar
 * Nothing
 *
 * Revision 1.3  1997/07/30 13:19:35  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/16 15:31:28  plehegar
 * Added documentation
 *
 * Revision 1.1  1997/07/11 13:59:47  plehegar
 * Initial revision
 *
 */

package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 *   <H3>
 *     &nbsp;&nbsp; Percentage units
 *   </H3>
 *   <P>
 *   The format of a percentage value is an optional sign character ('+' or '-',
 *   with '+' being the default) immediately followed by a number (with or without
 *   a decimal point) immediately followed by '%'.
 *   <P>
 *   Percentage values are always relative to another value, for example a length
 *   unit. Each property that allows percentage units also defines what value
 *   the percentage value refer to. Most often this is the font size of the element
 *   itself:
 *   <PRE>
 *   P { line-height: 120% }   / * 120% of the element's 'font-size' * /
 * </PRE>
 *   <P>
 *   In all inherited CSS1 properties, if the value is specified as a percentage,
 *   child elements inherit the resultant value, not the percentage value.
 * @version $Revision: 1.3 $
 */
public class CssPercentage extends CssValue {
    
    static Float defaultValue = new Float(0);
    Float value;
    
    /**
     * Create a new CssPercentage
     */
    public CssPercentage() {
	this(defaultValue);
    }
    
    /**
     * Create a new CssPercentage with a number
     *
     * @param value The value.
     */
    public CssPercentage(int value) {
	this(new Float(value));
    }  
    
    /**
     * Create a new CssPercentage with a float
     *
     * @param value the float value.
     */
    public CssPercentage(float value) {
	this(new Float(value));
    }  
    
    /**
     * Create a new CssPercentage with a Float value.
     *
     * @param value the Float object.
     */
    public CssPercentage(Float value) {
	this.value = value;
    }  
    
    /**
     * Set the value of this percentage.
     *
     * @param s     the string representation of the percentage.
     * @param frame For errors and warnings reports.
     * @exception InvalidParamException The unit is incorrect
     */  
    public void set(String s, ApplContext ac) throws InvalidParamException {
	if (s.charAt(s.length()-1) != '%') {
	    throw new InvalidParamException("percentage", s, ac);
	}
	this.value = new Float(s.substring(0, s.length()-1));
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
	return Util.displayFloat(value) + "%";
    }
    
    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */  
    public boolean equals(Object val) {
	return ((val instanceof CssPercentage)
		&& value.equals(((CssPercentage) val).value));
    }
    
}
