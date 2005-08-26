//
// $Id: CssAngle.java,v 1.4 2005-08-26 14:09:50 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssAngle.java,v $
 * Revision 1.4  2005-08-26 14:09:50  ylafon
 * All changes made by Jean-Guilhem Rouel:
 *
 * Fix for bugs: 1269, 979, 791, 777, 776, 767, 765, 763, 576, 363
 *
 * Errors in font, the handling of 'transparent', CSS Parser reinits...
 *
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=1269
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=979
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=791
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=777
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=776
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=767
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=765
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=763
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=576
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=363
 *
 * Revision 1.3  2005/08/08 13:19:46  ylafon
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
 * Revision 1.1  1997/08/29 13:10:38  plehegar
 * Initial revision
 *
 * Revision 1.1  1997/08/21 08:29:35  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 * <H3>Angle</H3>
 
 * <P>Angle units are used with aural cascading style sheets.
 *
 * <P>These are the legal angle units:
 *
 * <UL>
 * <LI>deg: degrees
 * <LI>grad: gradians
 * <LI>rad: radians
 * </UL>
 *
 * <p>Values in these units may be negative. They should be normalized to the
 * range 0-360deg by the UA. For example, -10deg and 350deg are equivalent.
 *
 * @version $Revision: 1.4 $ */
public class CssAngle extends CssValue implements CssValueFloat {
    
    Float value;
    int unit;
    static String[] units = { "deg", "grad", "rad" };
    static int[] hash_units;
    static Float defaultValue = new Float(0);
    
    /**
     * Create a new CssAngle.
     */
    public CssAngle() {
	this(defaultValue);
    }  
    
    /**
     * Create a new CssAngle
     */
    public CssAngle(float v) {
	this(new Float(v));
    } 
    
    /**
     * Create a new CssAngle
     */
    public CssAngle(Float angle) {
	value = angle;
    }  
    
    /**
     * Set the value of this angle.
     *
     * @param s The string representation of the angle
     * @param frame For errors and warnings reports
     * @exception InvalidParamException The unit is incorrect
     */  
    public void set(String s, ApplContext ac) throws InvalidParamException {
	s = s.toLowerCase();
	int length = s.length();
	String unit;
	//float v;
	if (s.indexOf("grad") == -1) {
	    unit = s.substring(length-3, length);
	    value = new Float(s.substring(0, length-3));
	} else {
	    unit = "grad";
	    value = new Float(s.substring(0, length-4));
	}
	int hash = unit.hashCode();
	
	
	int i = 0;
	while (i<units.length) {
	    if (hash == hash_units[i]) {
		this.unit = i;
		break;
	    }
	    i++;
	}
	
	if (i > 2) {
	    throw new InvalidParamException("unit", unit, ac);
	}
	
	this.unit = i; // there is no unit by default
	
	/* clipping with degree */
	/*
	  while (v < 0) {
	  v += 360;
	  }
	  while (v > 360) {
	  v -= 360;
	  }
	*/
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	return value;
    }
    
    public float getValue() {
	return value.floatValue();
    }

    /**
     * Returns the current value
     */  
    public String getUnit() {
	return units[unit];
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	
	if (value.floatValue() != 0) {
	    return Util.displayFloat(value) + getUnit();
	} else {
	    return Util.displayFloat(value);
	}
    }
    
    /**
     * Compares two values for equality.
     *
     * @param value The other value.
     */  
    public boolean equals(Object value) {
	return (value instanceof CssAngle && 
		this.value.equals(((CssAngle) value).value) &&
		unit == ((CssAngle) value).unit);
    }


    private float normalize(float degree) {
	while (degree < 0) {
	    degree += 360;
	}
	while (degree > 360) {
	    degree -= 360;
	}
	return degree;
    }
    
    //@@FIXME I should return the remainder for all ...

    public float getDegree() {
	float angle = value.floatValue();
	switch (unit) {
	case 0:	
	    // angle % 360
	    return normalize(angle);
	case 1:	    
	    return normalize(angle * (9.f / 10.f));	    
	case 2:	    
	    return normalize(angle * (180.f / ((float) Math.PI)));
	default:	    
	    System.err.println("[ERROR] in org.w3c.css.values.CssAngle");
	    System.err.println("[ERROR] Please report (" + unit + ")");
	    return (float) 0;
	}
    }
/*
 // These functions are not used, don't normalize angles, and are false
 // (int operations instead of float ones)
  
    public float getGradian() {
	float grad = value.floatValue();
	switch (unit) {
	case 0:
	    return (grad * (((float) Math.PI) / 180));
	case 1:
	    return grad;
	case 2:
	    return (grad * (((float) Math.PI) / 100));
	default:
	    System.err.println("[ERROR] in org.w3c.css.values.CssAngle");
	    System.err.println("[ERROR] Please report (" + unit + ")");
	    return (float) 0;
	}
    }

    public float getRadian() {
	float rad = value.floatValue();
	switch (unit) {
	case 0:
	    return (rad * (5 / 9));
	case 1:
	    return (rad * (100 / ((float) Math.PI)));
	case 2:
	    return rad;
	default:
	    System.err.println("[ERROR] in org.w3c.css.values.CssAngle");
	    System.err.println("[ERROR] Please report (" + unit + ")");
	    return (float) 0;
	}
    }
*/
    public boolean isDegree() {
	return unit == 0;
    }
    
    public boolean isGradian() {
	return unit == 1;
    }
    
    public boolean isRadian() {
	return unit == 2;
    }
    
    static {
	hash_units = new int[units.length];
	for (int i=0; i<units.length; i++)
	    hash_units[i] = units[i].hashCode();
    }
}

