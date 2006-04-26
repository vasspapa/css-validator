//
// $Id: HTMLStyleSheetParser.java,v 1.18 2006-04-26 12:44:14 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import html.tags.HtmlParser;
import html.tags.HtmlParserListener;
import html.tags.HtmlTag;
import html.tags.HtmlTree;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.HTTPURL;
import org.w3c.css.util.Util;

import org.w3c.www.mime.MimeType;
import org.w3c.www.mime.MimeTypeFormatException;

/**
 * @version $Revision: 1.18 $
 */
public final class HTMLStyleSheetParser implements HtmlParserListener {

    private StyleSheet style;
    private URL htmlURL;
    private Exception exception;
    private ApplContext ac;

    /**
     * Create a new HTMLStyleSheetParser
     *
     * @exception Exception An error
     */
    public HTMLStyleSheetParser(ApplContext ac, String urlString)
	throws Exception {
	this.htmlURL = HTTPURL.getURL(urlString);
	this.ac = ac;
	urlString = htmlURL.toString();
	String urlLower = urlString.toLowerCase();
	String media = ac.getMedium();

	if (!"http".equals(htmlURL.getProtocol())) {	    	    
	    if (urlLower.endsWith(".css")) {
		StyleSheetParser parser = new StyleSheetParser();
		parser.parseURL(ac, htmlURL, null, null, media,
				StyleSheetOrigin.AUTHOR);
		style = parser.getStyleSheet();
	    } else if (urlLower.endsWith(".html")
		       || urlLower.endsWith(".shtml")
		       || urlLower.endsWith("/")) {
		HtmlParser htmlParser = new HtmlParser(ac, "html4", urlString);
		try {
		    Util.fromHTMLFile = true;
		    htmlParser.addParserListener(this);
		    htmlParser.run();
		    if (exception != null) {
			throw (Exception) exception.fillInStackTrace();
		    }
		} catch (html.parser.XMLInputException e) {
		    XMLStyleSheetHandler handler;
		    handler = new XMLStyleSheetHandler(htmlURL, ac);
		    handler.parse(htmlURL);
		    style = handler.getStyleSheet();
		    if (style != null) {
			style.setType("text/xml");
		    }
		    if (Util.onDebug) {
			e.printStackTrace();
		    }
		} finally {
		    Util.fromHTMLFile = false;
		}
	    } else {
		throw new Exception("Unknown file");
	    }	    
	} else {
	    URLConnection connection = null;

	    try {
		boolean isXML = false;
		String cType;

		// @@ hum, maybe? (plh, yes probably :-) )
		String credential = ac.getCredential();

		connection = HTTPURL.getConnection(htmlURL, ac);
		htmlURL = connection.getURL();

		String httpCL = connection.getHeaderField("Content-Location");
		if (httpCL != null) {
		    htmlURL = HTTPURL.getURL(htmlURL, httpCL);
		}

		cType = connection.getContentType();
		if (cType == null) {
		    cType = "unknown/unknown";
		}
		MimeType contentType = null;
		try {
		    contentType = new MimeType(cType);
		} catch (MimeTypeFormatException ex) {
		}

		if (Util.onDebug) {
		    System.err.println( "[DEBUG] content type is [" +
					contentType + ']');
		}

		if (contentType.match(MimeType.TEXT_HTML) ==
		                           MimeType.MATCH_SPECIFIC_SUBTYPE) {
		    HtmlParser htmlParser;
		    htmlParser = new HtmlParser(ac, "html4", urlString,
						connection);
		    InputStream ucis = connection.getInputStream();
		    if (ucis.markSupported()) {
			ucis.mark(16384);
		    }
		    try {
			Util.fromHTMLFile = true;
			htmlParser.addParserListener(this);
			htmlParser.run();

			if (exception != null) {
			    throw (Exception) exception.fillInStackTrace();
			}
		    } catch (html.parser.XMLInputException e) {
			isXML = true;
			if (ucis.markSupported()) {
			    try {
				ucis.reset();
			    } catch (IOException ioex) {
				try {
				    ucis.close();
				} catch (Exception clex) {};
				connection = HTTPURL.getConnection(htmlURL,ac);
			    }
			} else {
			    connection = HTTPURL.getConnection(htmlURL, ac);
			    try {
				ucis.close();
			    } catch (Exception clex) {};
			}
		    } finally {
			Util.fromHTMLFile = false;
		    }
		} else if (contentType.match(MimeType.TEXT_CSS) ==
			                   MimeType.MATCH_SPECIFIC_SUBTYPE ) {
		    StyleSheetParser parser = new StyleSheetParser();
		    parser.parseURL(ac, htmlURL, null, null, media,
				    StyleSheetOrigin.AUTHOR);
		    style = parser.getStyleSheet();
		} else if ((contentType.match(MimeType.TEXT_XML) !=
			    MimeType.MATCH_SPECIFIC_SUBTYPE) &&
	       	         (contentType.match(MimeType.APPLICATION_XHTML_XML) !=
			  MimeType.MATCH_SPECIFIC_SUBTYPE)) {
		    throw new IOException("Unknown mime type : "+ contentType);
		}

		if (isXML ||
		    (contentType.match(MimeType.TEXT_XML) ==
		                            MimeType.MATCH_SPECIFIC_SUBTYPE) ||
		    (contentType.match(MimeType.APPLICATION_XHTML_XML) ==
		                            MimeType.MATCH_SPECIFIC_SUBTYPE)) {
		    XMLStyleSheetHandler handler;
		    handler = new XMLStyleSheetHandler(htmlURL, ac);
		    handler.parse(urlString, connection);
		    style = handler.getStyleSheet();		    
		    
		    if (style != null) {			
			style.setType("text/xml");
		    }
		}
	    } finally {
		try {
		    connection.getInputStream().close();
		} catch (Exception e) {}
	    }
	}
    }

    /**
     * Notifies root creation.

     *
     * Sent when the parser builds the root of the HTML tree.
     *
     * @param url the URL being parsed.
     * @param root the new root Tag for this parser.
     */
    public void notifyCreateRoot(URL url, HtmlTag root) {
    }

    public void notifyActivity(int lines, long bytes) {
    }

    public void notifyConnection(URLConnection cnx) {
    }

    /**
     * Notifies successful termination.
     *
     * @param root the root of the current Tree.
     */
    public void notifyEnd(HtmlTag root, String contentType) {
	if (root != null) {
	    style = ((HtmlTree) root).getStyleSheet();
	    if (style != null) {
		style.setType("text/html");
	    }
	}
	ac.setInput(contentType);
    }

    /**
     * Notifies a fatal error.
     *
     * This notification is sent when the parser need to stop during or before
     * parsing, due to an unexpected exception.
     *
     * @param root the root of the current Tree, if any.
     * @param x the exception that cause the Parser stop
     * @param msg an error message information
     */
    public void notifyFatalError(HtmlTag root, Exception x, String s) {
	exception = x;
    }

    /**
     * Returns the recognized style sheet.
     * @return A style sheet.
     */
    public StyleSheet getStyleSheet() {
	return style;
    }

} // HTMLStyleSheetParser
