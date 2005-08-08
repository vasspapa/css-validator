//
// $Id: TableProperties.java,v 1.4 2005-08-08 13:19:34 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.table;

import java.net.URL;

import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.Utf8Properties;

/**
 * @version $Revision: 1.4 $
 */
public class TableProperties {
    public static Utf8Properties properties;

    public static String getString(CssProperty property, String prop) {
      return properties.getProperty(property.getPropertyName() + "." + prop);
    }
    
    public static boolean getInheritance(CssProperty property) {
	return getString(property, "inherited").equals("true");
    }
  
    static {
	properties = new Utf8Properties();
	try {
	    URL url = TableProperties.class
	    .getResource("TableDefault.properties");
	    properties.load(url.openStream());
	} catch (Exception e) {
	    System.err
	    .println("org.w3c.css.table.TableProperties: couldn't load properties ");
	    System.err.println("  " + e.toString());
	}
    }
}
