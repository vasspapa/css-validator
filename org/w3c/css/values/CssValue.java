//
// $Id: CssValue.java,v 1.4 2005-09-08 12:24:10 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;

/**
 * @version $Revision: 1.4 $
 */
public abstract class CssValue {

    String cssversion;

  /**
   * Set the value of this value.
   *
   * @param s     the string representation of the value.
   * @param frame For errors and warnings reports.
   * @exception InvalidParamException The unit is incorrect
   */  
  public abstract void set(String s, ApplContext ac) 
      throws InvalidParamException;

  /**
   * Returns the internal value
   */  
  public abstract Object get();

  /**
   * Compares two values for equality.
   *
   * @param value The other value.
   */  
  public boolean equals(Object value) {
    return super.equals(value);
  }

    public void setCssVersion(String cssversion) {
	this.cssversion = cssversion;
    }
    
    public boolean isDefault() {
	return false;
    }

}
