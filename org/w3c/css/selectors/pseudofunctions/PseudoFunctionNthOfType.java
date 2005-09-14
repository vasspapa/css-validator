// $Id: PseudoFunctionNthOfType.java,v 1.2 2005-09-14 15:15:32 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors.pseudofunctions;

import org.w3c.css.selectors.PseudoFunctionSelector;

/**
 * PseudoFunctionNthOfType<br />
 * Created: Sep 2, 2005 4:23:48 PM<br />
 */
public class PseudoFunctionNthOfType extends PseudoFunctionSelector {

    public PseudoFunctionNthOfType(String name, Integer n) {
	setName(name);
	setParam(n);
    }

    public PseudoFunctionNthOfType(String name, String value) {
	this(name, new Integer(value));
    }

}
