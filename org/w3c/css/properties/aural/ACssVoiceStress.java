//
// $Id: ACssVoiceStress.java,v 1.1 2005-08-23 16:51:43 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: ACssVoiceStress.java,v $
 * Revision 1.1  2005-08-23 16:51:43  ylafon
 * reorg (cvs funkyness...)
 *
 * Revision 1.2  2005/08/08 13:18:04  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.1  2003/07/28 14:56:30  sijtsche
 * new CSS3 speech property
 *
 * Revision 1.2  2002/04/08 21:16:56  plehegar
 * New
 *
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.4  1997/08/25 13:51:32  plehegar
 * Added getValue()
 *
 * Revision 1.3  1997/08/22 18:01:18  plehegar
 * Updated
 *
 * Revision 1.2  1997/08/22 17:58:30  plehegar
 * Updated
 *
 * Revision 1.1  1997/08/22 17:07:38  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssValue;

/**
 * @version $Revision: 1.1 $
 */
public class ACssVoiceStress extends ACssProperty {

    CssValue value;

    private static int[] hash_values;

    private static String[] STRESS = { "strong", "moderate", "none", "reduced" };
    private static CssIdent defaultValue = new CssIdent(STRESS[2]);

    /**
     * Create a new ACssVoiceStress
     */
    public ACssVoiceStress() {
	value = defaultValue;
    }

    /**
     * Creates a new ACssVoiceStress
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Expressions are incorrect
     */
    public ACssVoiceStress(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	this();
	
	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	CssValue val = expression.getValue();
	int index;

	setByUser();
	if (val.equals(inherit)) {
	    value = inherit;
	} else if (val instanceof CssIdent) {
	    value = checkIdent(ac, (CssIdent) val);
	} else if (val instanceof CssNumber) {
	    value = val;
	} else {
	    throw new InvalidParamException("value",
					    expression.getValue().toString(),
					    getPropertyName(), ac);
	}
	expression.next();
    }

    public ACssVoiceStress(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }


    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "voice-stress";
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((ACssStyle) style).acssVoiceStress != null)
	    style.addRedefinitionWarning(ac, this);
	((ACssStyle) style).acssVoiceStress = this;
    }

    /**
     * Compares two properties for equality.
     *
     * @param property The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof ACssVoiceStress &&
		value.equals(((ACssVoiceStress) property).value));
    }

    private CssIdent checkIdent(ApplContext ac, CssIdent ident) throws InvalidParamException {
	int hash = ident.hashCode();
	for (int i = 0; i < STRESS.length; i++) {
	    if (hash_values[i] == hash) {
		return ident;
	    }
	}

	throw new InvalidParamException("value",
					ident.toString(),
					getPropertyName(), ac);
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getVoiceStress();
	} else {
	    return ((ACssStyle) style).acssVoiceStress;
	}
    }

    static {
	hash_values = new int[STRESS.length];
	for (int i = 0; i < STRESS.length; i++)
	    hash_values[i] = STRESS[i].hashCode();
    }
}

