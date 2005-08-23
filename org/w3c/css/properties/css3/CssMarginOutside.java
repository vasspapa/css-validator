//
// $Id: CssMarginOutside.java,v 1.1 2005-08-23 16:24:20 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssMarginOutside.java,v $
 * Revision 1.1  2005-08-23 16:24:20  ylafon
 * Patch by Jean-Guilhem Rouel
 *
 * Better handling of media and properties files
 * Major reorganization of those properties files
 *
 * Revision 1.2  2005/08/08 13:18:54  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.1  2002/07/19 20:30:12  sijtsche
 * files representing CSS3 properties
 *
 * Revision 1.1  2002/05/08 09:30:52  dejong
 * CSS version 3 specific properties as in March 2002, all modules
 *
 * Revision 3.1  1997/08/29 13:13:56  plehegar
 * Freeze
 *
 * Revision 1.4  1997/08/20 11:41:26  plehegar
 * Freeze
 *
 * Revision 1.3  1997/08/06 17:30:12  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:20:11  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/24 00:08:48  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssMarginSide;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'margin-outside'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;length&gt; | &lt;percentage&gt; | auto<BR>
 *   <EM>Initial:</EM> 0<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> refer to parent element's width<BR>
 *   <P>
 *   This property sets the right margin of an element:
 *   <PRE>
 *   H1 { margin-outside: 12.3% }
 * </PRE>
 *   <P> A negative value is allowed, but there may be implementation-specific
 *   limits.
 *
 * @version $Revision: 1.1 $ */
public class CssMarginOutside extends CssMarginSide {

  /**
   * Create a new CssMarginOutside
   */
  public CssMarginOutside() {
    super();
  }
  
  /**
   * Create a new CssMarginOutside with an another CssMarginSide
   * @param another The another side.
   */
  public CssMarginOutside(CssMarginSide another) {
    super(another);
  }
  
  /**
   * Create a new CssMarginOutside
   *
   * @param expression The expression for this property.
   * @exception InvalidParamException Values are incorrect
   */
  public CssMarginOutside(ApplContext ac, CssExpression expression,
	  boolean check) throws InvalidParamException {
    super(ac, expression, check);
  }
  
  public CssMarginOutside(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
    this(ac, expression, false);
  }  
  
  /**
   * Returns the name of this property
   */  
  public String getPropertyName() {
    return "margin-outside";
  }

  /**
   * Add this property to the CssStyle.
   *
   * @param style The CssStyle
   */
  public void addToStyle(ApplContext ac, CssStyle style) {
    Css3Style style0 = (Css3Style) style;
    if (style0.cssMarginOutside != null)
      style0.addRedefinitionWarning(ac, this);
    style0.cssMarginOutside = this;
  }

  /**
   * Get this property in the style.
   *
   * @param style The style where the property is
   * @param resolve if true, resolve the style to find this property
   */  
  public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
    if (resolve) {
      return ((Css3Style) style).getMarginOutside();
    } else {
      return ((Css3Style) style).cssMarginOutside;
    }
  }

  /**
   * Compares two properties for equality.
   *
   * @param value The other property.
   */  
  public boolean equals(CssProperty property) {
      //    return (property instanceof CssMarginOutside && 
      //    value.equals(((CssMarginOutside) property).value));
      return false;
  }

}
