//
// $Id: CssFontConstant.java,v 1.3 2002-12-18 09:21:01 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssFontConstant.java,v $
 * Revision 1.3  2002-12-18 09:21:01  sijtsche
 * values changed
 *
 * Revision 1.2  2002/04/08 21:17:43  plehegar
 * New
 *
 * Revision 1.3  1997/07/30 13:19:59  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/17 12:42:03  plehegar
 * Added font-weight
 *
 * Revision 1.1  1997/07/17 12:28:44  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties;

/**
 * @version $Revision: 1.3 $
 */
public interface CssFontConstant {

  /**
   * Array of font-style values
   */
  static String[] FONTSTYLE = { "normal", "italic", "oblique", "inherit" };

  /**
   * Array of font-variant values
   */
  static String[] FONTVARIANT = { "normal", "small-caps", "inherit" };

  /**
   * Array of font-size values
   */
  static String[] FONTSIZE = {
    "xx-small", "x-small", "small", "medium", "large", "x-large", "xx-large",
    "larger", "smaller", "inherit"
  }; // relative-size

  /**
   * Array of font-weight values
   */
  static String[] FONTWEIGHT = { "normal", "bold", "bolder",
				 "lighter", "inherit" };

  /**
   * Array of font-stretch values
   */
  static String[] FONTSTRETCH = { "normal", "wider", "narrower",
				  "ultra-condensed", "extra-condensed",
				  "condensed", "semi-condensed",
				  "semi-expanded", "expanded", "extra-expanded",
				  "ultra-expanded", "inherit" };

  /**
   * Array of font values
   */
  static String[] FONT = { "icon", "window", "status-bar", "document", "workspace", "desktop",
  						   "tooltip", "message-box", "dialog", "small-caption", "caption", "button",
  						   "default-button", "hyperlink", "menu", "pull-down-menu", "pop-up-menu",
  						   "list-menu", "field", "inherit"
	  					 };

}
