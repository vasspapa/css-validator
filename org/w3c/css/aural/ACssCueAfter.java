//
// $Id: ACssCueAfter.java,v 1.1 2002-03-13 19:54:48 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: ACssCueAfter.java,v $
 * Revision 1.1  2002-03-13 19:54:48  plehegar
 * New
 *
 * Revision 2.1  1997/08/29 13:11:50  plehegar
 * Updated
 *
 * Revision 1.2  1997/08/26 14:26:55  plehegar
 * Bug in getValue()
 *
 * Revision 1.1  1997/08/25 13:03:54  plehegar
 * Initial revision
 *
 * Revision 1.6  1997/08/22 15:00:09  plehegar
 * Bugs
 *
 * Revision 1.5  1997/08/22 14:57:32  plehegar
 * Added getPropertyInStyle()
 *
 * Revision 1.4  1997/08/21 21:13:25  plehegar
 * Added time
 *
 * Revision 1.3  1997/08/21 14:34:56  vmallet
 * Minor modifications so we could compile it.
 *
 * Revision 1.2  1997/08/14 13:19:05  plehegar
 * Added ACssPauseAfter(ACssPauseBefore)
 *
 * Revision 1.1  1997/08/14 12:58:44  plehegar
 * Initial revision
 *
 */

package org.w3c.css.aural;

import java.io.IOException;
import java.net.URL;

import org.w3c.css.values.CssValue;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssURL;
import org.w3c.css.values.CssIdent;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.CssProperty;

/**
 * &nbsp;&nbsp;  'cue-after'
 *
 * <P>
 * <EM>Value: </EM> &lt;url&gt; | none<BR>
 * <EM>Initial:</EM> none<BR>
 * <EM>Applies to:</EM> all elements<BR>
 * <EM>Inherited:</EM> no<BR>
 * <EM>Percentage values:</EM> NA
 *
 * <P>Auditory icons are another way to distinguish semantic
 * elements. Sounds may be played before, and/or after the element to
 * delimit it. The same sound can be used both before and after, using the
 * shorthand 'cue' property.
 *
 * <p> Examples:
 * <PRE>
 *   A {cue-before: url(bell.aiff); cue-after: url(dong.wav) }
 *   H1 {cue-before: url(pop.au); cue-after: url(pop.au) }
 *   H1 {cue: url(pop.au) }  / * same as previous * /
 * </pre>
 *
 * <p class=comment>The <tt>:before</tt> and <tt>:after</tt>
 * pseudo-elements (see frostings document) could be used to generate
 * this content, rather than using two special-purpose properties. This
 * would be more general.</p>
 *
 * @version $Revision: 1.1 $
 */
public class ACssCueAfter extends ACssProperty {
    
    CssValue value;
    
    private URL url;
    private static CssIdent none = new CssIdent("none");

    /**
     * Create a new ACssCueAfter
     */  
    public ACssCueAfter() {
	value = none;
    }
    
    /**
     * Create a new ACssCueAfter
     */  
    public ACssCueAfter(ACssCueBefore cueBefore) {
	value = cueBefore.value;
    }
    
    /**
     * Creates a new ACssCueAfter
     * @param value the value of the size
     * @exception InvalidParamException The value is incorrect
     */  
    public ACssCueAfter(ApplContext ac, CssExpression value) throws InvalidParamException {
	CssValue val = value.getValue();
	
	if (val instanceof CssURL) {
	    this.value = val;
	    value.next();
	    return;
	} else if (val.equals(inherit)) {
	    this.value = inherit;
	    value.next();
	    return;
	} else if (val.equals(none)) {
	    this.value = none;
	    value.next();
	    return;
	}
	
	throw new InvalidParamException("value", val.toString(), 
					getPropertyName(), ac);
    }
    
    /**
     * Returns the current value
     */  
    public Object get() {
	if (value == none)
	    return null;
	else
	    return value;
    }
    
    
    /**
     * Returns some usable value of this property...
     */
    public URL getValue() throws IOException { // vm
	if (value == none)
	    return null;
	else {
	    if (url == null) {
		url = HTTPURL.getURL(new URL(sourceFile), (String) value.get());
	    }
	    return url;
	}
    }
    
    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value is equals to inherit
     */
    public boolean isSoftlyInherited() {
	return value.equals(inherit);
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
	return "cue-after";
    }
    
    public void addToStyle(ApplContext ac, CssStyle style) {
	ACssCue acssCue = ((ACssStyle) style).acssCue;
	if (acssCue.cueAfter != null)
	    style.addRedefinitionWarning(ac, this);
	acssCue.cueAfter = this;
    }
    
    public boolean equals(CssProperty property) {
	return (property instanceof ACssCueAfter && value.equals(((ACssCueAfter) property).value));
    }
    
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((ACssStyle) style).getCueAfter();
	} else {
	    return ((ACssStyle) style).acssCue.cueAfter;
	}
    }
    
}
