//
// $Id: ACssProperties.java,v 1.2 2002-04-08 21:16:56 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.aural;

import java.util.Properties;
import java.net.URL;

import org.w3c.css.properties.CssProperty;

/**
 * @version $Revision: 1.2 $
 */
public class ACssProperties {
  public static Properties properties;
  public static Properties UAproperties;

  public static Float getValue(CssProperty property, String prop) {
    String value = UAproperties.getProperty(property.getPropertyName()+"."+prop);
    if (value == null) {
      System.err.println( "Can't find value for " + property.getPropertyName()+"."+prop );
      return null;
    } else {
      try {
	return Float.valueOf(value);
      } catch (NumberFormatException e) {
	System.err.println( e );
	System.err.println( "Only float value are valid in properties" );
	return null;
      }
    }
  }
  
  public static String getString(CssProperty property, String prop) {
    return properties.getProperty(property.getPropertyName()+"."+prop);
  }

  public static boolean getInheritance(CssProperty property) {
    return getString(property, "inherited").equals("true");
  }
  
  static {
    UAproperties = new Properties();
    try {
      URL url = ACssProperties.class.getResource("AuralDefault.properties");
      java.io.InputStream f = url.openStream();
      UAproperties.load(f);
      f.close();
    } catch (Exception e) {
      System.err.println("CSS.ACSSProperties.ACssProperties: couldn't load UA properties ");
      System.err.println("  " + e.toString() );
    }

    properties = new Properties();
    try {
      URL url = ACssProperties.class.getResource("ACSSDefault.properties");
      java.io.InputStream f = url.openStream();
      properties.load(f);
      f.close();
    } catch (Exception e) {
      System.err.println("CSS.ACSSProperties.ACssProperties: couldn't load properties ");
      System.err.println("  " + e.toString() );
    }
  }
}
