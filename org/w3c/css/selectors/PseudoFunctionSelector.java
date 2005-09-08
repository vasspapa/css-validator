// $Id: PseudoFunctionSelector.java,v 1.1 2005-09-08 12:24:01 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

/**
 * PseudoFunction<br />
 * Created: Sep 2, 2005 4:04:45 PM<br />
 */
public class PseudoFunctionSelector implements Selector {

    private String name;
    private Object param;

    /**
     * Creates a new empty function selector     
     */
    public PseudoFunctionSelector() {
	
    }
    
    /**
     * @see Selector#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this pseudo-function
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the parameter of this pseudo-function.
     * @return the parameter of this pseudo-function.
     */
    public Object getParam() {
        return param;
    }

    /**
     * Sets the parameter of this pseudo-function
     * @param param The param to set.
     */
    public void setParam(Object param) {
        this.param = param;
    }

    /**
     * Returns the specifictiy of this pseudo-function
     * @return
     */
    public int getSpecificity() {
	return 0;
    }
    
    /**
     * @see Selector#canApply(Selector)
     */
    public boolean canApply(Selector other) {
	return false;
    }
    
    /**
     * @see Selector#toString()
     */
    public String toString() {
	return ":" + name + "(" + param + ")";
    }    
}
