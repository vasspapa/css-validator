//
// $Id: CssResolution.java,v 1.2 2005-08-08 13:19:47 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 25th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssResolution.java,v $
 * Revision 1.2  2005-08-08 13:19:47  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.1  2003/01/03 15:17:13  sijtsche
 * new value for media feature resolution
 *
 * Revision 1.2  2002/04/08 21:19:46  plehegar
 * New
 *
 * Revision 2.3  1997/08/29 13:10:52  plehegar
 * Updated
 *
 * Revision 1.1  1997/07/11 13:57:58  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;

/**
 *   <H3>
 *     &nbsp;&nbsp; Resolution units
 *   </H3>
 *   <P>
 *	 The dpi and dpcm units describe the resolution of an output device i.e. the density of
 *   the pixels. In dots per inch and dots per centimeter, respectively. These units are only used in the
 *   resolution media feature.
 *   </P>
 * @version $Revision: 1.2 $
 */
public class CssResolution extends CssValue {

  /**
   * Create a new CssResolution
   */
  public CssResolution() {
    value = defaultValue;
  }

  /**
   * Set the value of this Resolution.
   *
   * @param s     the string representation of the Resolution.
   * @param frame For errors and warnings reports.
   * @exception InvalidParamException The unit is incorrect
   */
  public void set(String s, ApplContext ac) throws InvalidParamException {
    s = s.toLowerCase();
    int length = s.length();
    String unit = "";
    if (s.toUpperCase().indexOf("DPI") != -1) {
		unit = s.substring(length-3, length);
		this.value = new Float(s.substring(0, length-3));
		if (unit.toUpperCase().equals("DPI")) {
			this.unit = unit;
		}
		return;
	} else if (s.toUpperCase().indexOf("DPCM") != -1) {
		unit = s.substring(length-4, length);
		this.value = new Float(s.substring(0, length-4));
		if (unit.toUpperCase().equals("DPCM")) {
			this.unit = unit;
		}
		return;
	}

    if (!ac.getCssVersion().equals("css3")) {
		throw new InvalidParamException("unit", unit, ac);
    }

    throw new InvalidParamException("unit", unit, ac);
  }

  /**
   * Returns the current value
   */
  public Object get() {
    return value;
  }

  /**
   * Returns the current value
   */
  public String getUnit() {
    return unit;
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
    return (value instanceof CssResolution &&
	    this.value.equals(((CssResolution) value).value) &&
	     unit == ((CssResolution) value).unit);
  }

  private Float value;
  private String unit;
  private static Float defaultValue = new Float(0);

}

