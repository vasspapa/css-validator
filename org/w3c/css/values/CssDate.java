//
// $Id: CssDate.java,v 1.1 2003-07-30 06:53:44 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssDate.java,v $
 * Revision 1.1  2003-07-30 06:53:44  sijtsche
 * date format dd/mm/yyyy
 *
 * Revision 1.2  2002/04/08 21:19:46  plehegar
 * New
 *
 * Revision 1.3  1997/08/29 13:11:08  plehegar
 * Updated
 *
 * Revision 1.2  1997/08/22 17:21:26  plehegar
 * Updated
 *
 * Revision 1.1  1997/08/21 08:29:35  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.util.ApplContext;

/**
 * <H3>Date</H3>
 *
 * Legal format is dd/mm/yyyy as in SSML
 *
 * @version $Revision: 1.1 $
 */
public class CssDate extends CssValue {

	String day = new String();
	String month = new String();
	String year = new String();

  /**
   * Create a new CssDate.
   */
  public CssDate() {
  }

  /**
   * Set the value of this date.
   *
   * @param s     the string representation of the time.
   * @param frame For errors and warnings reports.
   * @exception InvalidParamException The date format is incorrect
   */
  public void set(String s, ApplContext ac) throws InvalidParamException {
    s = s.toLowerCase();
    int length = s.length();

	if (s.indexOf("/") == -1) {
		// invalid date
		throw new InvalidParamException("value",
		      s, ac);

	} else if (s.indexOf("/") == s.lastIndexOf("/")) {
		// only one / in date is invalid date
	      throw new InvalidParamException("value",
		      s, ac);
	} else {
		day = s.substring(0,s.indexOf("/") - 1);
		month = s.substring(s.indexOf("/"), s.lastIndexOf("/") - 1);
		year = s.substring(s.lastIndexOf("/"),length);

		Integer d, m, y;

		try {
			d = new Integer(day);
			m = new Integer(month);
			y = new Integer(year);
		} catch (NumberFormatException e) {
	      throw new InvalidParamException("value", s, ac);
		}

		if (d.intValue() > 31 || d.intValue() < 0 || m.intValue() > 12 || m.intValue() < 0 || y.intValue() < 0) {
			throw new InvalidParamException("value", s, ac);
		}
	}

  }

  /**
   * Returns the current value
   *  Float
   */
  public Object get() {
    return new String(day + "/" + month + "/" + year);
  }

  /**
   * Returns a string representation of the object.
   */
  public String toString() {
	  return day + "/" + month + "/" + year;
  }

  /**
   * Compares two values for equality.
   *
   * @param value The other value.
   */
  public boolean equals(Object value) {
    return (value instanceof CssDate && this.day.equals(((CssDate)value).day) &&
    this.month.equals(((CssDate)value).month) && this.year.equals(((CssDate)value).year));
  }

}

