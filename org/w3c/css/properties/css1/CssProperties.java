//
// $Id: CssProperties.java,v 1.3 2009-02-24 21:45:14 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import java.net.URL;

import org.w3c.css.util.Utf8Properties;

/**
 * @version $Revision: 1.3 $
 */
public class CssProperties {
    public static Utf8Properties properties;

    public static String getString(CssProperty property, String prop) {
	StringBuilder st = new StringBuilder(property.getPropertyName());
	st.append('.').append(prop);
	return properties.getProperty(st.toString());
    }

    public static boolean getInheritance(CssProperty property) {
	return "true".equals(getString(property, "inherited"));
    }

    static {
	properties = new Utf8Properties();
	try {
	    URL url = CssProperties.class.getResource("CSS1Default.properties");
	    java.io.InputStream f = url.openStream();
	    properties.load(f);
	    f.close();
	} catch (Exception e) {
	    System.err.println("org.w3c.css.properties.CssProperties: "+
			       "couldn't load properties ");
	    System.err.println("  " + e.toString());
	}
    }
}
