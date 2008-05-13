// $Id: AttributeExact.java,v 1.6 2008-05-13 09:38:01 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.attributes;

import org.w3c.css.selectors.AttributeSelector;
import org.w3c.css.selectors.Selector;
import org.w3c.css.util.ApplContext;

/**
 * AttributeExact<br />
 * Created: Sep 1, 2005 4:22:42 PM<br />
 */
public class AttributeExact extends AttributeSelector {

    private String value;

    public AttributeExact(String name, String value) {        
	setName(name);
	this.value = value;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean canApply(Selector other) {
	if (other instanceof AttributeAny) {
	    // [lang=fr][lang]
	    return true;
	} else if (other instanceof AttributeExact ||
		other instanceof AttributeOneOf ||
		other instanceof AttributeBegin) {
	    if (!value.equals(((AttributeExact) other).getValue())) {
		// [lang=fr][lang=en]
		return false;
	    } else {
		// [lang=en][lang=en]
		return true;
	    }
	}
	return false;
    }

    public void applyAttribute(ApplContext ac, AttributeSelector attr) {
	String name = getName();
	// if same name... check if they are incompatible or not
	if (name.equals(attr.getName())) {
	    if (attr instanceof AttributeExact) {
		// and not the same value, raise a warning
		if (!value.equals(((AttributeExact) attr).getValue())) {
		    ac.getFrame().addWarning("incompatible", new String[] { toString(), attr.toString() });
		}
	    } else if(attr instanceof AttributeOneOf) {
		// FIXME check that the parsed one of value are matching before doing the conclict check
		// requires breaking down the OneOf
		if (!value.equals(((AttributeOneOf) attr).getValue())) {
		    ac.getFrame().addWarning("incompatible", new String[] { toString(), attr.toString() });
		}
	    } else if(attr instanceof AttributeBegin) {
		String othervalue = ((AttributeBegin) attr).getValue();
		// check if [lang|=en][lang=fr-FR] are incompatible 
		// form CSS3 selectors about AttributeBegin
		// its value either being exactly "val" or beginning with "val" immediately followed by "-" (U+002D). 
		if (!value.equals(othervalue) && !value.startsWith(othervalue+"-")) {
		    ac.getFrame().addWarning("incompatible", new String[] { toString(), attr.toString() });
		}
	    }
	}
    }

    public String toString() {
	return "[" + getName() + "=\"" + value + "\"]";
    }

}
