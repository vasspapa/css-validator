//
// $Id: CssSelectorsConstant.java,v 1.3 2002-07-22 14:19:06 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
// Updated September 20th 2000 Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssSelectorsConstant.java,v $
 * Revision 1.3  2002-07-22 14:19:06  sijtsche
 * new elements and classes added and order of constants changed because of representation errors in output
 *
 * Revision 1.2  2002/05/23 09:03:47  dejong
 * new elements and classes added and order of constants changed due to representation errors in output
 *
 */
package org.w3c.css.parser;

/**
 * @version $Revision: 1.3 $
 */
public interface CssSelectorsConstant {

    public static final String[] PSEUDOCLASS_CONSTANTS =
    { "link", "visited", "target", "active",
      "hover", "focus", "first-child",
      "enabled", "disabled",
      "checked", "indeterminate", "root", "last-child",
      "first-of-type", "last-of-type", "only-of-type",
      "only-child", "empty" };

    public static final String[] PSEUDOCLASS_CONSTANTSCSS2 =
    {
      "link", "visited", "target", "active",
      "hover", "focus", "first-child"
    };

    public static final String[] PSEUDOCLASS_CONSTANTSCSS1 =
    {
      "link", "visited", "target", "active"
    };

	public static final String[] PSEUDOCLASS_CONSTANTS_MOBILE =
	{
	   "link", "visited", "active", "focus"
	};

    // lang is special (and not available for mobile profile)

    public static final String[] PSEUDOELEMENT_CONSTANTS =
    { "first-line", "first-letter", "before", "after",
      "selection"
    };

    // lang is special, and contains and nth-element and nth-type-of

    public static final String[] PSEUDOELEMENT_CONSTANTSCSS2 =
    { "first-line", "first-letter",
      "before", "after"
    };

    public static final String[] PSEUDOELEMENT_CONSTANTSCSS1 =
    {
		"first-line", "first-letter"
    };

    /** [lang="fr"] */
    public static final int ATTRIBUTE_EXACT = ' ';
    /** [lang~="fr"] */
    public static final int ATTRIBUTE_ONE_OF = '~';
    /** class selector == like [lang~="fr"] */
    public static final int ATTRIBUTE_CLASS_SEL = '.';
    /** [lang|="fr"] */
    public static final int ATTRIBUTE_BEGIN = '|';
    /** [lang] */
    public static final int ATTRIBUTE_ANY = -1;
    /** [foo^="bar"] */
    public static final int ATTRIBUTE_START = '^';
    /** [foo$="bar"] */
    public static final int ATTRIBUTE_SUFFIX = '$';
    /** [foo*="bar"] */
    public static final int ATTRIBUTE_SUBSTR = '*';

    /** Maximun of ATTRIBUTE_ONE_OF */
    public static final int ATTRIBUTE_LENGTH = 10;

    /** descendant connector  */
    public static final char DESCENDANT = ' ';
    /** child connector */
    public static final char CHILD = '>';
    /** adjacent connector */
    public static final char ADJACENT = '+';
}
