// $Id: StyleReport.java,v 1.1 2003-10-17 13:54:02 ylafon Exp $
// Author: Yves Lafon <ylafon@w3.org>
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.io.PrintWriter;

import org.w3c.css.util.ApplContext;

public abstract class StyleReport {
    
    abstract public void print(PrintWriter out);
    
    abstract public void desactivateError();

}
    
