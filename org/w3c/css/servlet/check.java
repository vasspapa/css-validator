//
// $Id: check.java,v 1.8 2005-09-14 15:15:32 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.servlet;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.css.css.CssParser;
import org.w3c.css.css.HTMLStyleSheetParser;
import org.w3c.css.css.StyleSheet;
import org.w3c.css.css.StyleSheetGeneratorHTML2;
import org.w3c.css.css.StyleSheetParser;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.Util;
import org.xml.sax.SAXParseException;

/**
 * This class is a servlet to use the validator.
 *
 * @version $Revision: 1.8 $
 */
public final class check extends HttpServlet {

    private static String validatorURI = null;


    /**
     * Performs the HTTP GET operation.
     * It redirects to the value contained in "Referer"
     *
     * @param req encapsulates the request to the servlet.
     * @param resp encapsulates the response from the servlet.
     * @exception ServletException if the request could not be handled.
     * @exception IOException if detected when handling the request.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
    {

	String uri = req.getHeader("Referer");

	if (uri == null) {
	    res.setContentType("text/plain");
	    res.sendError(400, "Referer field empty");
	    return;
	}
	res.setHeader("Cache-Control", "max-age=86400");
	res.setHeader("Vary", "Referer");

	Enumeration e = req.getParameterNames();
	StringBuffer sb = new StringBuffer(validatorURI);
	boolean first = true;
	boolean uridone = false;

	if (e != null) {
	    while (e.hasMoreElements()) {
		String paramname = (String) e.nextElement();
		String paramvalue = req.getParameter(paramname);
		if ("uri".equals(paramname)) {
		    uridone = true;
		    paramvalue = uri;
		}
		if (first) {
		    sb.append('?');
		    first = false;
		} else {
		    sb.append('&');
		}
		sb.append(paramname);
		if (paramvalue != null) {
		    sb.append('=');
		    sb.append(paramvalue);
		}
	    }
	}
	if (!uridone) {
	    if (first) {
		sb.append("?uri=");
	    } else {
		sb.append("&uri=");
	    }
	    sb.append(uri);
	}
	res.sendRedirect(sb.toString());
    }

    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	if (config.getInitParameter("validatorURI") == null) {
	    validatorURI = "validator";
	} else {
	    validatorURI = config.getInitParameter("validatorURI");
	}
    }
}
