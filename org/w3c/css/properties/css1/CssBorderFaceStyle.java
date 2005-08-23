//
// $Id: CssBorderFaceStyle.java,v 1.1 2005-08-23 16:23:12 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderFaceStyle.java,v $
 * Revision 1.1  2005-08-23 16:23:12  ylafon
 * Patch by Jean-Guilhem Rouel
 *
 * Better handling of media and properties files
 * Major reorganization of those properties files
 *
 * Revision 1.4  2005/08/08 13:18:12  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.3  2002/12/20 16:06:03  sijtsche
 * new values added
 *
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 2.3  1997/09/09 08:50:28  plehegar
 * Added getStyle()
 *
 * Revision 2.2  1997/08/20 11:41:17  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:08  plehegar
 * Nothing
 *
 * Revision 1.2  1997/07/30 13:19:52  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 12:34:00  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision: 1.1 $
 */
public class CssBorderFaceStyle {

    int value;

    private static String[] BORDERSTYLE = {
	"none", "hidden", "dotted", "dashed", "solid", "double", "dot-dash",
	"dot-dot-dash", "wave", "groove",
	"ridge", "inset", "outset", "inherit" };

    private static int[] hash_values;

    /**
     * Create a new CssBorderFaceStyle
     */
    public CssBorderFaceStyle() {
	// nothing to do
    }

    /**
     * Create a new CssBorderFaceStyle with an another CssBorderFaceStyle
     *
     * @param another An another side.
     */
    public CssBorderFaceStyle(CssBorderFaceStyle another) {
	value = another.value;
    }

    /**
     * Create a new CssBorderFaceStyle
     *
     * @param expression The expression for this face
     * @exception InvalidParamException The expression is incorrect
     */
    public CssBorderFaceStyle(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();

	if (val instanceof CssIdent) {
	    int hash = val.hashCode();
	    for (int i = 0; i < BORDERSTYLE.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}

	throw new InvalidParamException("value", val.toString(), "style", ac);
    }

    public CssBorderFaceStyle(ApplContext ac, CssExpression expression) 
	throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == (BORDERSTYLE.length - 1);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return BORDERSTYLE[value];
    }

    /**
     * Returns the value
     */
    public String getStyle() {
	return BORDERSTYLE[value];
    }

    /**
     * Compares two side for equality.
     *
     * @param value The another side.
     */
    public boolean equals(CssBorderFaceStyle style) {
	return value == style.value;
    }

    static {
	hash_values = new int[BORDERSTYLE.length];
	for (int i=0; i<BORDERSTYLE.length; i++)
	    hash_values[i] = BORDERSTYLE[i].hashCode();
    }

}
