//
// $Id: CssListStyleConstants.java,v 1.3 2002-08-19 07:40:17 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssListStyleConstants.java,v $
 * Revision 1.3  2002-08-19 07:40:17  sijtsche
 * new values added
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 1.2  1997/07/30 13:20:06  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/25 15:20:46  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

/**
 * @version $Revision: 1.3 $
 */
public interface CssListStyleConstants {

  public static String[] LISTSTYLETYPE = {
      "disc", "circle", "square", "decimal", "decimal-leading-zero",
      "lower-roman", "upper-roman",
      "lower-greek", "upper-greek",
      "lower-latin", "upper-latin",
      "lower-alpha", "upper-alpha",
      "hebrew", "armenian", "georgian", "cjk-ideographic",
      "katakana", "katakana-iroha",
      "hiragana", "hiragana-iroha",
      "none", "inherit" };

    public static String[] LISTSTYLETYPECSS1 = {
	"disc", "circle", "square", "decimal",
	"lower-roman", "upper-roman",
	"lower-alpha", "upper-alpha",
	"none", "inherit"
    };

    public static String[] LISTSTYLETYPETV = {
	"disc", "circle", "square", "decimal",
	"lower-roman", "upper-roman",
	"lower-alpha", "upper-alpha",
	"none", "lower-latin", "upper-latin", "inherit"
	};

    /* inherit is not accepted by CSS1, but is filtered out automatically
     by the parser, now LISTSTYLETYPECSS1 can be used by the mobile profile
     which does accept the same values as the CSS1 property + inherit */

    //for CSS1 inherit is already corriged by the parser itself
  public static String[] LISTSTYLEPOSITION = {
      "outside", "inside", "inherit" };
}
