//
// $Id: CssMedia.java,v 1.1 2002-07-19 20:30:12 sijtsche Exp $
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties3;

import java.util.Vector;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssOperator;


/**
 * This property sets the preferred media for this stylesheet
 */

public class CssMedia extends CssProperty implements CssOperator {

    CssValue value;
    Vector values = new Vector();

    CssIdent all = new CssIdent("all");

    private String[] media = { 
	"all", "aural", "braille", "embossed", "handheld", "print",
	"projection", "screen", "presentation", "tty", "tv" };

    /**
     * Create a new CssMedia
     */
    public CssMedia() {
	value = all;
    }

    /** 
     * Create a new CssMedia
     */
    public CssMedia(ApplContext ac, CssExpression expression) throws InvalidParamException {

	//setByUser();
	char op = expression.getOperator();
	CssValue val = expression.getValue();
	int counter = 0;
		

	while ((op == COMMA || op == SPACE)
	       && (counter < expression.getCount())) {
	    
	    int i = 0;
	    for (;i < media.length; i++) {
		if (val.toString().equals(media[i])) {
		    break;
		}
	    }
	    
	    if (i == media.length) {
		throw new InvalidParamException("media", expression.getValue(),
						getPropertyName(), ac);
	    }

	    values.addElement(val);
	    expression.next();
	    counter++;
	    val = expression.getValue();
	    op = expression.getOperator();
	}
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssMedia != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssMedia = this;

    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getMedia();
	} else {
	    return ((Css3Style) style).cssMedia;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {
	return false;
    }
    
    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "media";
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	if (value != null) {
	    return value;
	}
	else {
	    return values;
	}
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * This property can't be inherited, it's only for @preference
     */
    public boolean isSoftlyInherited() {
	return false; 
    }
    
    /**
     * Returns a string representation of the object
     */
    public String toString() {
	if (value != null) {
	    return value.toString();
	}
	else {
	    String ret = new String("");
	    for (int i = 0; i < values.size(); i++ ) {
		ret += " " + values.elementAt(i).toString();
	    }
	    return ret;
	}
    }
    
    /**
     * Is the value of this property a default value
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {	
	return value == all;
    }
    
}


		   



