/*
 * (c) COPYRIGHT 1999 World Wide Web Consortium
 * (Massachusetts Institute of Technology, Institut National de Recherche
 *  en Informatique et en Automatique, Keio University).
 * All Rights Reserved. http://www.w3.org/Consortium/Legal/
 *
 * $Id: ApplContext.java,v 1.7 2005-09-08 16:07:06 ylafon Exp $
 */
package org.w3c.css.util;

//import java.nio.charset.Charset;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import org.w3c.css.parser.Frame;
import org.w3c.www.http.HttpAcceptCharset;
import org.w3c.www.http.HttpAcceptCharsetList;
import org.w3c.www.http.HttpFactory;

/**
 * @version $Revision: 1.7 $
 * @author  Philippe Le Hegaret
 */
public class ApplContext {

    private static Method m = null;

    String credential = null;

    String lang;
    
    Messages msgs;
    
    Frame  frame;
    
    String cssversion;
    
    String profile;
    
    String input;
    
    Class cssselectorstyle;

    int origin = -1;
    
    String medium;

    static {
	try {
	    Class c = Class.forName("java.nio.charset.Charset");
	    Class cp[] = { java.lang.String.class };
	    m = c.getDeclaredMethod("isSupported", cp);
	} catch (ClassNotFoundException ex) {
	    m = null;
	} catch (NoSuchMethodException ex) {
	    m = null;
	}
    }

    /**
     * Creates a new ApplContext
     */
    public ApplContext(String lang) {
	this.lang = lang;
        msgs = new Messages(lang);
    }

    // as ugly as everything else
    public String getCredential() {
	return credential;
    }

    public void setCredential(String credential) {
	this.credential = credential;
    }

    public void setFrame(Frame frame) {
	this.frame = frame;
	frame.ac = this;
    }

    public Frame getFrame() {
	return frame;
    }

    public Class getCssSelectorsStyle() {
	return cssselectorstyle;
    }

    public void setCssSelectorsStyle(Class s) {
	cssselectorstyle = s;
    }

    public Messages getMsg() {
	return msgs;
    }

    public String getContentType() {
	return (msgs != null) ? msgs.getString("content-type") : null;
    }

    public String getContentLanguage() {
	return (msgs != null) ? msgs.getString("content-language") : null;
    }

    /**
     * Searches the properties list for a content-encoding one.
     * If it does not exist, searches for output-encoding-name.
     * If it still does not exists, the method returns the default utf-8 value
     * @return the output encoding of this ApplContext
     */
    public String getContentEncoding() {
	//return (msgs != null) ? msgs.getString("content-encoding") : null;
	String res = null;
	if(msgs != null) {
	    res = msgs.getString("content-encoding");
	    if(res == null) {
		res = msgs.getString("output-encoding-name");
	    }		
	    if(res != null) {
		// if an encoding has been found, return it
		return res;
	    }
	}
	// default encoding
	return Utf8Properties.ENCODING;
    }

    public String getLang() {
	return lang;
    }

    public void setCssVersion(String cssversion) {
        this.cssversion = cssversion;
    }

    public String getCssVersion() {
	if (cssversion == null) {
	    cssversion = "css2";
	}
	return cssversion;
    }
    
    public void setProfile(String profile) {
	this.profile = profile;
    }

    public String getProfile() {
	if (profile == null) {
	    return "";
	}
	return profile;
    }

    public void setOrigin(int origin) {
	this.origin = origin;
    }

    public int getOrigin() {
	return origin;
    }

    public void setMedium(String medium) {
	this.medium = medium;
    }

    public String getMedium() {
	return medium;
    }

    public String getInput() {
	return input;
    }

    public void setInput(String input) {
	this.input = input;
    }
    
    /**
     * Sets the content encoding to the first charset that appears in
     * <i>acceptCharset</i>.
     * If the charset is not supported, the content encoding will be utf-8
     * @param acceptCharset a String representing the Accept-Charset 
     *                      request parameter
     */
    public void setContentEncoding(String acceptCharset) {	
	if(acceptCharset != null) {		    
	    // uses some Jigsaw classes to parse the Accept-Charset
	    // these classes need to load a lot of stuff, so it may be quite
	    // long the first time
	    HttpAcceptCharsetList charsetList;
	    HttpAcceptCharset[] charsets;

	    charsetList = HttpFactory.parseAcceptCharsetList(acceptCharset);
	    charsets = (HttpAcceptCharset[])charsetList.getValue();
	    
	    String encoding = null;
	    double quality = 0.0;
	    
	    String biasedcharset = getMsg().getString("output-encoding-name");

	    for(int i = 0; i < charsets.length && quality < 1.0 ; i++) {
		HttpAcceptCharset charset = charsets[i];
		
		String currentCharset = charset.getCharset();
		
		// checks that the charset is supported by Java
		
		
		if(isCharsetSupported(currentCharset)) {
		    double currentQuality = charset.getQuality();
		    
		    // we prefer utf-8 
		    // FIXME (the bias value and the biased charset
		    //        should be dependant on the language)
		    if ((biasedcharset != null) && 
			!biasedcharset.equalsIgnoreCase(currentCharset)) {
			currentQuality = currentQuality * 0.5;
		    }
		    if(currentQuality > quality) {
			quality = currentQuality;
			encoding = charset.getCharset();
		    }
		}
	    }		
	    if(encoding != null) {
		getMsg().properties.setProperty("content-encoding", encoding);
	    }
	    else {
		// no valid charset
		getMsg().properties.remove("content-encoding");
	    }
	}
	else {
	    // no Accept-Charset given 
	    getMsg().properties.remove("content-encoding");
	}
    }
    
    private boolean isCharsetSupported(String charset) {
	// if we can't check, assume it's ok, and fail later.
	if (m == null) {
	    return true;
	}
	try {
	    String p[] = new String[1];
	    p[0] = charset;
	    Boolean b = (Boolean) m.invoke(null, p);
	    return b.booleanValue();
	}
	catch(Exception e) {
	    return false;
	}	
    }
}
