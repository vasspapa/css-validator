/*
 * Copyright (c) 2001 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 *
 * $Id: RGB.java,v 1.2 2002-04-08 21:19:46 plehegar Exp $
 */
package org.w3c.css.values;

public class RGB {

    String output = null;
    Object r;
    Object g;
    Object b;
    
    /**
     * Create a new RGB
     */
    public RGB() {
    }
    
    /**
     * Create a new RGB with default values
     */
    public RGB(Object r, Object g, Object b) {
	this.r = r;
	this.g = g;
	this.b = b;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	if (output == null) {
	    return "rgb("+r+","+g+","+b+")";
	} else {
	    return output;
	}
    }
}
