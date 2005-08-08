//
// $Id: CssTextDecorationCSS3.java,v 1.2 2005-08-08 13:18:54 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssTextDecorationCSS3.java,v $
 * Revision 1.2  2005-08-08 13:18:54  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.1  2002/08/07 14:22:12  sijtsche
 * separate version for CSS3: lining values added
 *
 * Revision 1.2  2002/04/08 21:17:44  plehegar
 * New
 *
 * Revision 3.1  1997/08/29 13:14:05  plehegar
 * Freeze
 *
 * Revision 2.2  1997/08/20 11:41:29  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:35  plehegar
 * Nothing
 *
 * Revision 1.4  1997/08/06 17:30:21  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.3  1997/07/30 13:20:19  plehegar
 * Updated package
 *
 * Revision 1.2  1997/07/23 23:38:44  plehegar
 * bug fix none
 *
 * Revision 1.1  1997/07/23 22:11:30  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'text-decoration'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> none | [ underline || overline || line-through || blink ]<BR>
 *   <EM>Initial:</EM> none<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no, but see clarification below<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P>
 *   This property describes decorations that are added to the text of an element.
 *   If the element has no text (e.g. the 'IMG' element in HTML) or is an empty
 *   element (e.g. '&lt;EM&gt;&lt;/EM&gt;'), this property has no effect. A value
 *   of 'blink' causes the text to blink.
 *   <P>
 *   The color(s) required for the text decoration should be derived from the
 *   'color' property value.
 *   <P>
 *   This property is not inherited, but elements should match their parent. E.g.,
 *   if an element is underlined, the line should span the child elements. The
 *   color of the underlining will remain the same even if descendant elements
 *   have different 'color' values.
 *   <PRE>
 *   A:link, A:visited, A:active { text-decoration: underline }
 * </PRE>
 *   <P>
 *   The example above would underline the text of all links (i.e., all 'A' elements
 *   with a 'HREF' attribute).
 *   <P>
 *   UAs must recognize the keyword 'blink', but are not required to support the
 *   blink effect.
 *
 * @version $Revision: 1.2 $
 */
public class CssTextDecorationCSS3 extends CssProperty
        implements CssTextPropertiesConstantsCSS3 {

    CssValue value;

    private boolean[] values = new boolean[TEXTDECORATION.length];

    private static int[] hash_values;

    private static CssIdent none = new CssIdent("none");

    private static final int INVALID = -1;

    /**
     * Create a new CssTextDecoration
     */
    public CssTextDecorationCSS3() {
    }

    /**
     * Create a new CssTextDecoration
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Values are incorrect
     */
    public CssTextDecorationCSS3(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	CssValue val = expression.getValue();
	boolean find = true;
	int computed = 0;
	int index = INVALID;

	setByUser();

	if (val.equals(none)) {
	    value = none;
	    expression.next();
	    return;
	} else if (val.equals(inherit)) {
	    value = inherit;
	    expression.next();
	    return;
	}
	val = null;
	
	while (find) {
	    find = false;
	    val = expression.getValue();
	    if (val instanceof CssIdent) {
		index = getIndex((CssIdent) val, ac);
		if (values[index] == true) {
		    throw new InvalidParamException("same-value",
			    TEXTDECORATION[index], ac);
		} else {
		    values[index] = true;
		    find = true;
		    expression.next();
		}
	    } else if (val != null) {
		throw new InvalidParamException("value", val.toString(),
			getPropertyName(), ac);
	    }
	}
    }
    
    public CssTextDecorationCSS3(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	if (value != null) {
	    return value;
	}
	for (int i = 0; i < TEXTDECORATION.length; i++) {
	    if (values[i] == true) {
		return TEXTDECORATION[i];
	    }
	}
	return null;
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "text-decoration";
    }

    private int getIndex(CssIdent val, ApplContext ac) throws InvalidParamException {
	int hash = val.hashCode();
	for (int i = 0; i < TEXTDECORATION.length; i++) {
	    if (hash_values[i] == hash) {
		return i;
	    }
	}
	throw new InvalidParamException("value", val.toString(),
					getPropertyName(), ac);
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == inherit;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (value != null) {
	    return value.toString();
	} else {
	    String ret = "";
	    for (int i = 0; i < TEXTDECORATION.length; i++) {
		if (values[i] == true) {
		    ret += " " + TEXTDECORATION[i];
		}
	    }
	    return ret.substring(1);
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	Css3Style style0 = (Css3Style) style;
	if (style0.cssTextDecoration != null) {
	    style0.addRedefinitionWarning(ac, this);
	}
	style0.cssTextDecoration = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getTextDecoration();
	} else {
	    return ((Css3Style) style).cssTextDecoration;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	// @@ FIXME
	return false;
    }

    static {
	hash_values = new int[TEXTDECORATION.length];
	for (int i=0; i<TEXTDECORATION.length; i++) {
	    hash_values[i] = TEXTDECORATION[i].hashCode();
	}
    }
}
