// $Id: StyleReportFactory.java,v 1.3 2003-10-20 13:30:06 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.io.PrintWriter;

import org.w3c.css.util.ApplContext;

public class StyleReportFactory {
    
    /**
     * Give back a "StyleReport" object based on various
     * parameters, but mainly output"
     */
    public static StyleReport getStyleReport(ApplContext ac, 
					     String title, 
					     StyleSheet style,
					     String document,
					     int warningLevel) {
	if ((document == null) || (document.equals("html"))
	    || (document.equals("xhtml"))) {
	    return new StyleSheetGeneratorHTML2(ac, title, style,
						document,
						warningLevel);
	}
	if (document.equals("soap12")) {
	    return new StyleReportSOAP12(ac, title, style,
					 document,
					 warningLevel);
	}
	return new StyleSheetGeneratorHTML2(ac, title, style,
					    "html",
					    warningLevel);
    }
}
    
