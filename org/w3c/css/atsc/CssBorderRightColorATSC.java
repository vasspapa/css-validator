//
// $Id: CssBorderRightColorATSC.java,v 1.1 2002-07-24 14:42:28 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBorderRightColorATSC.java,v $
 * Revision 1.1  2002-07-24 14:42:28  sijtsche
 * ATSC TV profile files
 *
 * Revision 1.1  2002/05/31 09:00:16  dejong
 * ATSC TV profile objects
 *
 * Revision 3.3  1997/09/09 10:52:03  plehegar
 * bugs
 *
 * Revision 3.2  1997/09/09 10:51:43  plehegar
 * Added getColor()
 *
 * Revision 3.1  1997/08/29 13:13:38  plehegar
 * Freeze
 *
 * Revision 1.1  1997/08/20 11:41:19  plehegar
 * Initial revision
 *
 */
package org.w3c.css.atsc;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.properties.CssProperty;

/**
 * Be careful, this is not a CSS1 property !
 * @version $Revision: 1.1 $
 */
public class CssBorderRightColorATSC extends CssProperty {

  CssBorderFaceColorATSC face;
  
  /**
   * Create a new CssBorderRightColorATSC
   */
  public CssBorderRightColorATSC() {
    face = new CssBorderFaceColorATSC();
  }
  
  /**
   * Create a new CssBorderRightColorATSC with an another CssBorderFaceColorATSC
   *
   * @param another The another side.
   */
  public CssBorderRightColorATSC(CssBorderFaceColorATSC another) {

      setByUser();

    face = another;
  }
  
  /**
   * Create a new CssBorderRightColorATSC
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssBorderRightColorATSC(ApplContext ac, CssExpression expression) 
    throws InvalidParamException {

	setByUser();
    face = new CssBorderFaceColorATSC(ac, expression);
  }
  
  /**
   * Returns the value of this property
   */
  public Object get() {
    return face;
  }

  /**
   * Returns the color of this property
   */
  public CssValue getColor() {
    return face.getColor();
  }

  /**
   * Returns a string representation of the object.
   */
  public String toString() {
    return face.toString();
  }

  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "border-right-color";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    CssBorderRightATSC right = ((ATSCStyle) style).cssBorderATSC.right;
    if (right.color != null)
      style.addRedefinitionWarning(ac, this);
    right.color = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((ATSCStyle) style).getBorderRightColorATSC();
    } else {
      return ((ATSCStyle) style).cssBorderATSC.getRight().color;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof CssBorderRightColorATSC && 
	    face.equals(((CssBorderRightColorATSC) property).face));
  }

  /**
   * Print this property.
   *
   * @param printer The printer.
   */  
  public void print(CssPrinterStyle printer) {
    if (!face.isDefault())
      printer.print(this);
  }
}
