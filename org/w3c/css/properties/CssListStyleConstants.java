//
// $Id: CssListStyleConstants.java,v 1.5 2002-12-20 16:03:36 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssListStyleConstants.java,v $
 * Revision 1.5  2002-12-20 16:03:36  sijtsche
 * new values added
 *
 * Revision 1.4  2002/08/21 09:01:24  sijtsche
 * new values added for list-style-type
 *
 * Revision 1.3  2002/08/19 07:40:17  sijtsche
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
 * @version $Revision: 1.5 $
 */
public interface CssListStyleConstants {

  public static String[] LISTSTYLETYPE = {
      "disc", "circle", "square", "box", "decimal", "decimal-leading-zero",
      "lower-roman", "upper-roman", "lower-greek", "lower-latin", "upper-latin",
      "lower-alpha", "upper-alpha", "hebrew", "armenian", "georgian", "cjk-ideographic",
      "katakana", "katakana-iroha", "hiragana", "hiragana-iroha",
      "simp-chinese-formal", "simp-chinese-informal", "trad-chinese-formal",
      "trad-chinese-informal", "japanese-formal", "japanese-informal", "hangul",
      "hangul-consonant", "cjk-heavenly-stem", "cjk-earthly-branch", "arabic-indic",
      "persian", "devanagari", "gurmukhi", "gurajati", "kannada", "malayalam",
      "bengali", "tamil", "telugu", "thai", "lao", "myanmar", "khmer",
      "hangule-consonant", "ethiopic-halehame", "ethiopic-abegede", "ethiopic-numeric",
      "urdu", "oriya",
      "none", "inherit", "check", "diamond" };

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
