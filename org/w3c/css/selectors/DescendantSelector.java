// $Id: DescendantSelector.java,v 1.1 2005-09-08 12:24:01 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

/**
 * Descendant<br />
 * Created: Sep 1, 2005 3:57:40 PM<br />
 */
public class DescendantSelector implements Selector {

    /**
     * @see Selector#toString()
     */
    public String toString() {
	return " ";
    }
    
    /**
     * @see Selector#getName()
     */
    public String getName() {
	return " ";
    }
    
    /**
     * @see Selector#canApply(Selector)
     */
    public boolean canApply(Selector other) {
	return false;
    }
    
}
