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
 * $Id: XMLInputException.java,v 1.2 2002-04-08 21:22:41 plehegar Exp $
 */
package html.parser;

/**
 * @version $Revision: 1.2 $
 * @author  Philippe Le Hegaret
 */
public class XMLInputException extends RuntimeException {

    String xmlns;

    /**
     * Creates a new XHTMLInputException
     */
    public XMLInputException(String xmlns) {
	this.xmlns = xmlns;
    }

    public String getNamespaceURI() {
	return xmlns;
    }
}
