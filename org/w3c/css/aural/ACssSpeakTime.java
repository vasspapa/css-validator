//
// $Id: ACssSpeakTime.java,v 1.2 2002-04-08 21:16:56 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: ACssSpeakTime.java,v $
 * Revision 1.2  2002-04-08 21:16:56  plehegar
 * New
 *
 */

package org.w3c.css.aural;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssNumber;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;

/**
 * <H3> &nbsp;&nbsp 'speak-time'</H3>
 * <P>
 * <EM>Value: </EM> 24 | 12 | none<BR>
 * <EM>Initial:</EM> none<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> yes<BR>
 * <EM>Percentage values:</EM> NA
 *
 * @version $Revision: 1.2 $
 */
public class ACssSpeakTime extends ACssProperty {
  
  CssValue value;
  
  /**
   * Create a new ACssSpeakTime
   */  
  public ACssSpeakTime() {
    value = none;
  }
  
  /**
   * Creates a new ACssSpeakTime
   *
   * @param expression the expression of the size
   * @exception InvalidParamException The expression is incorrect
   */  
  public ACssSpeakTime(ApplContext ac, CssExpression expression) 
    throws InvalidParamException {

    CssValue val = expression.getValue();
    
    if (val.equals(none)) {
      value = none;
      expression.next();
      return;
    } else if (val instanceof CssNumber) {
      float v = ((Float) val.get()).floatValue();
      if (v == 12 || v == 24) {
	value = val;
	expression.next();
	return;
      }
    }
    
    throw new InvalidParamException("value", val.toString(), getPropertyName(), ac);
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
    return value.toString();
  }
  
  
  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "speak-time";
  }
  
  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    if (((ACssStyle) style).acssSpeakTime != null)
      ((ACssStyle) style).addRedefinitionWarning(ac, this);
    ((ACssStyle) style).acssSpeakTime = this;
  }
  
  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
    return (property instanceof ACssSpeakTime && 
	    value.equals(((ACssSpeakTime) property).value));
  }
  
  
  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((ACssStyle) style).getSpeakTime();
    } else {
      return ((ACssStyle) style).acssSpeakTime;
    }
  }
  
  private static CssIdent none = new CssIdent("none");
}
