//
// $Id: CssIdent.java,v 1.3 2005-08-08 13:19:47 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssIdent.java,v $
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
 * Revision 2.2  1997/08/20 11:38:36  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:53:04  plehegar
 * Nothing
 *
 * Revision 1.5  1997/07/30 13:19:33  plehegar
 * Updated package
 *
 * Revision 1.4  1997/07/24 00:20:45  plehegar
 * Added equals()
 *
 * Revision 1.3  1997/07/22 12:36:08  plehegar
 * Updated hashCode() Added toLowerCase()
 *
 * Revision 1.2  1997/07/16 15:12:32  plehegar
 * Added hashCode()
 *
 * Revision 1.1  1997/07/16 13:58:34  plehegar
 * Initial revision
 *
 */
package org.w3c.css.values;

import org.w3c.css.util.ApplContext;

/**
 * @version $Revision: 1.3 $
 */
public class CssIdent extends CssValue {

  /**
   * Create a new CssIdent
   */
  public CssIdent() {
  }

  /**
   * Create a new CssIdent
   *
   * @param s The identificator
   */
  public CssIdent(String s) {
    value = s;   
  }  
  
  /**
   * Set the value of this ident.
   *
   * @param s     the string representation of the identificator.
   * @param frame For errors and warnings reports.
   */  
  public void set(String s, ApplContext ac) {      
    value = s;
  }

  /**
   * Returns the internal value.
   */  
  public Object get() {
    return value;
  }

  /**
   * Returns a string representation of the object.
   */
  public String toString() {
    return value;
  }

  /**
   * Compares two values for equality.
   *
   * @param value The other value.
   */  
  public boolean equals(Object value) {
    return (value instanceof CssIdent && value.hashCode() == hashCode());
  }

  /**
   * Returns a hashcode for this ident.
   */
  public int hashCode() {
    return value.toLowerCase().hashCode();
  }

  private String value;
}
