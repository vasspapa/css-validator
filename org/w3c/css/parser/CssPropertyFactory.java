//
// $Id: CssPropertyFactory.java,v 1.1 2002-03-13 19:55:33 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssPropertyFactory.java,v $
 * Revision 1.1  2002-03-13 19:55:33  plehegar
 * New
 *
 */
package org.w3c.css.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.values.CssExpression;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.ApplContext;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @version $Revision: 1.1 $
 * @author  Philippe Le H�garet
 */
public class CssPropertyFactory {

    // all recognized properties are here.
    private Properties properties;

    private String usermedium;
    
    /**
     * Create a new CssPropertyFactory
     */
    public CssPropertyFactory(URL url) {
	properties = new Properties();
	InputStream f = null;
	try {
	    f = url.openStream();
	    properties.load(f);
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (f != null) 
		    f.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    } // ignore
	}
    }

    public String getProperty(String name) {
	return properties.getProperty(name);
    }

    private Vector getVector(String media) {
	Vector list = new Vector();
	String medium = new String();
	StringTokenizer tok = new StringTokenizer(media, ",");

	while (tok.hasMoreTokens()) {
	    medium = tok.nextToken();
	    medium = medium.trim();
	    list.addElement(medium);
	}

	return list;
    }

    public void setUserMedium(String usermedium) {
	this.usermedium = usermedium;
    }

    public synchronized CssProperty createProperty(ApplContext ac, 
						   AtRule atRule,
						   String property, 
						   CssExpression expression) 
	    throws Exception {

	String result = "ok";
	
	String classname;
	String media = atRule.toString();
	int pos = media.indexOf(" ");
	media = media.substring(pos + 1, media.length());
	media = media.trim();

	Vector list = new Vector(getVector(media));

	if (atRule instanceof AtRuleMedia) {
	    if (media.equals("all")) {
		classname = properties.getProperty(property);
	    } else {
		for (int i = 0; i < list.size() - 1; i++) {
		    String medium = (String)list.elementAt(i);
		    String name = properties.getProperty(medium + 
							 "." + property);
		    if (name == null) {
			result = medium;
		    }
		}
	    
		if (result.equals("ok")) {
		    classname = properties.getProperty((String)list.firstElement() + "." + property);
		}
		else {
		    classname = null;
		}
	    }
	} else {
	    classname = properties.getProperty("@" + atRule.keyword()
					       + "." + property);
	}

	CssProperty prop = null;

	if (classname == null && usermedium != null) {
	    if (atRule instanceof AtRuleMedia) {
		// I don't know this property
		if (!media.equals("all"))
		    ac.getFrame().addWarning("notforusermedium", property); 
		classname = properties.getProperty(property);
	    } 
	    else {
	    // I don't know this property
	    throw new InvalidParamException("noexistence", property, 
					    media, ac);
	    }
	} else 
	if (classname == null ) { // && CssFouffa.usermedium == null) {
	    if (atRule instanceof AtRuleMedia && (!media.equals("all"))) {
		// I don't know this property
		throw new InvalidParamException("noexistence-media", 
						property,
						media, ac);
	    } 
	    else {
	    // I don't know this property
	    throw new InvalidParamException("noexistence", property, 
					    media, ac);
	    }
	}
	
	try {
	    // create an instance of your property class
	    Class[] parametersType = { ac.getClass(), expression.getClass() };
	    Constructor constructor = 
		Class.forName(classname).getConstructor(parametersType);
	    Object[] parameters = { ac, expression };
	    // invoke the constructor
	    return (CssProperty) constructor.newInstance(parameters);
	} catch (InvocationTargetException e) {
	    // catch InvalidParamException
	    InvocationTargetException iv = e;
	    Exception ex = (Exception) iv.getTargetException();
	    throw ex;
	}
    }
}
