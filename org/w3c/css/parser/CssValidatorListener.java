//
// $Id: CssValidatorListener.java,v 1.4 2005-08-08 13:18:11 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssValidatorListener.java,v $
 * Revision 1.4  2005-08-08 13:18:11  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.3  2003/07/02 14:39:50  plehegar
 * Removed HttpServletResponse import
 *
 * Revision 1.2  2002/04/08 21:24:12  plehegar
 * New
 *
 * Revision 2.1  1997/08/08 15:50:53  plehegar
 * Freeze
 *
 * Revision 1.2  1997/07/30 13:20:34  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/28 21:35:52  plehegar
 * Initial revision
 *
 */
package org.w3c.css.parser;

import java.util.Vector;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.Warnings;

/**
 * Implements this interface if you want to use the CSS1 parser.
 *
 * @version $Revision: 1.4 $
 */
public interface CssValidatorListener {  

  /**
   * Adds a vector of properties to a selector.
   *
   * @param selector     the selector
   * @param declarations Properties to associate with contexts
   */  
  public void handleRule(ApplContext ac, CssSelectors selector, 
			 Vector declarations);

  /**
   * Handles an at-rule.
   *
   * <p>The parameter <code>value</code> can be :
   * <DL>
   * <DT>CssString
   * <DD>The value coming from a string.
   * <DT>CssURL
   * <DD>The value coming from an URL.
   * <DT>Vector
   * <DD>The value is a vector of declarations (it contains Couple).
   * </DL>
   *
   * @param ident The ident for this at-rule (for example: 'font-face')
   * @param value The string representation of this at-rule
   * @see         org.w3c.css.parser.analyzer.Couple
   */  
  public void handleAtRule(ApplContext ac, String ident, String string);

  /**
   * Notify all errors
   *
   * @param errors All errors in the style sheet
   * @see CssError
   * @see CssErrorDeclaration
   * @see CssErrorToken
   */  
  public void notifyErrors(Errors errors);

  /**
   * Notify all warnings
   *
   * @param warnings All warnings in the style sheet
   * @see org.w3c.css.util.Warning
   */  
  public void notifyWarnings(Warnings warnings);

    public void addCharSet(String charset);

    public void newAtRule(AtRule atRule);

    public void endOfAtRule();

    public void setImportant(boolean important);

    public void setSelectorList(Vector selectors);
       
    public void setProperty(Vector properties);
	
    public void endOfRule();

    public void removeThisRule();

    public void removeThisAtRule();

}
