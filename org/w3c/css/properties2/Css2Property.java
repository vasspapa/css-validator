//
// $Id: Css2Property.java,v 1.2 2002-04-08 21:18:00 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: Css2Property.java,v $
 * Revision 1.2  2002-04-08 21:18:00  plehegar
 * New
 *
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.1  1997/08/26 15:57:58  plehegar
 * Initial revision
 *
 */

package org.w3c.css.properties2;

import org.w3c.css.aural.ACssProperty;

/**
 * @version $Revision: 1.2 $
 */
public abstract class Css2Property extends ACssProperty {

  /**
   * Returns true if the property is inherited
   */
  public boolean Inherited() {
    return Css2Properties.getInheritance(this);
  }

}
