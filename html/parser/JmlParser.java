/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id: JmlParser.java,v 1.2 2002-04-08 21:22:41 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */
/* Modified: Vincent Mallet (Vincent.Mallet@sophia.inria.fr) */

package html.parser;

import java.util.*;
import java.io.*;
import java.net.URL;


/**
 * A Simple wrapper class to keep the original code clean and be able to access
 * package-private variables and methods. 
 */

public class JmlParser extends Parser {
    static final String defaultDTD = "html2-net";
    String dtdname;
    
    public JmlParser() throws ParserException {
	this(defaultDTD);
    }
    
    public JmlParser(String dtdn) throws ParserException {
	
	dtdname=dtdn;

	// Load properties
	URL url = null;
	java.io.InputStream f = null;
	DTD.props = new Properties();
	
	try {
	    // url = getClass().getResource("../parser/Parser.properties"); //vm980826
	    url = JmlParser.class.getResource("Parser.properties"); //plh050597
	    f = url.openStream();
	    DTD.props.load(f);
	} catch (Exception e) {
	    String msg = 
		"Failed to load properties (url=" + url +")"
		+ "(msg=" + e.getMessage() + ")"; //vm970808 //vm980826
	    throw new ParserException(msg);    
            
	    //vm970808       System.out.println("Failed to load properties...");
	    //vm970808       e.printStackTrace();
	    //vm970808       System.exit(1);
	} finally {
	    try {
		f.close();
	    } catch (Exception e) {}
	}
	
	// load the right DTD
	DTD.props = new Properties(DTD.props);
	try {
	    dtd = DTD.getDTD(dtdname);
	}
	catch(IOException x){
	    throw new ParserException("Failed to load dtd...");
	    //vm970808 System.out.println("Failed to load dtd...");
	    //vm970808 x.printStackTrace();
	    //vm970808 System.exit(1);
	}
    }
    
    //     public void parse(InputStream in) {
    // 	parse(in, dtd);
    //     }
    
    public void parseFile(String filename) throws ParserException {
	InputStream in;
	try {
	    if (filename.indexOf(':') > 0) {
		in = new URL(null, filename).openStream();
	    } else {
		in = new java.io.BufferedInputStream(new java.io.FileInputStream(filename));
	    }
	    try {
		long tm = System.currentTimeMillis();
		parse(in, dtd);
		
		tm = System.currentTimeMillis() - tm;
		System.out.println("[Parsed " +filename+ " in " + tm + "ms]");
	    }
	    catch(Exception e) {
		throw new ParserException("Uncaught error while parsing");
		
		//vm970808 System.out.println("uncaught error while parsing");
		//vm970808 e.printStackTrace();
		//vm970808 System.exit(1);
	    }
	    
	} catch (Exception e) {
	    throw new ParserException("Failed to open: " + filename); //vm970808
	    
	    //vm970808 e.printStackTrace();
	    //vm970808 System.out.println("failed to open: " + filename);
	    //vm970808 System.exit(1);
	}
    }
}


