/* Remade by Sijtsche de Jong */

package org.w3c.css.css;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Enumeration;
import org.w3c.css.util.Date;
import java.util.Vector;

import org.w3c.css.parser.analyzer.ParseException;
import org.w3c.css.parser.CssParseException;
import org.w3c.css.parser.AtRule;
import org.w3c.css.parser.AtRulePage;
import org.w3c.css.parser.AtRuleMedia;
import org.w3c.css.parser.AtRuleFontFace;
import org.w3c.css.parser.Errors;
import org.w3c.css.parser.CssError;
import org.w3c.css.parser.CssErrorToken;
import org.w3c.css.parser.CssFouffa;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectorsConstant;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.SortedHashtable;
import org.w3c.css.util.Warnings;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Util;

/**
 * @version $Revision: 1.2 $
 */
public final class StyleSheetGenerator2 {
    
    //    SortedHashtable items;
    Hashtable items;
    Warnings warnings;
    Errors errors;
    
    private CssSelectors selector;
    private CssProperty property;
    private PrintWriter out;
    private int warningLevel;
    private Properties general;
    
    private static Properties availableFormat;
    private static Hashtable formats = new Hashtable();

    /**
     * Create a new StyleSheetGenerator2
     *
     * @param title        The title for the generated document
     * @param style        The style sheet
     * @param document     The name of the source file
     * @param warningLevel If you want to reduce warning output.
     *                     (-1 means no warnings)
     */
    public StyleSheetGenerator2(String title, 
			       StyleSheet style, 
			       String document,
			       int warningLevel) {
	
	//this small part prints the stylesheet to the screen
	if (StyleSheetCom.showCSS == true) {

	    if (style.charset != null) {
		System.out.println("@charset " + style.charset + ";\n");
	    }
	    Vector atRules = style.newGetRules();
	    for (int i = 0; i < atRules.size(); i++) {
		System.out.println(((CssRuleList)atRules.elementAt(i)).toString());
	    }
	}

	general = new Properties(setDocumentBase(getDocumentName(document)));
	general.put("file-title", title);
	general.put("today", new Date().toString());
	
	warnings = style.getWarnings();
	errors = style.getErrors();
	items = style.getRules();
	this.warningLevel = warningLevel;
	
	general.put("errors-count", 
		    Integer.toString(errors.getErrorCount()));
	general.put("warnings-count", 
		    Integer.toString(warnings.getWarningCount()));
	general.put("rules-count", 
		    Integer.toString(items.size()));
	
	if (errors.getErrorCount() == 0) {
	    desactivateError();
	}
	if (warnings.getWarningCount() == 0 || warningLevel == -1) {
	    general.put("go-warnings", ""); // remove go-warnings
	    general.put("warnings", ""); // remove warnings
	}
	if ((items.size() == 0) || (StyleSheetCom.showCSS == false)) {
	    general.put("go-rules", ""); // remove go-rules
	    general.put("rules", ""); // remove rules
	} else {
	    general.put("no-rules", ""); // remove no-rules
	}
	
	if (errors.getErrorCount() != 0 || warnings.getWarningCount() != 0) {
	    // remove no-error-or-warning
	    general.put("no-error-or-warning", ""); 
	}
	
	if (Util.onDebug) general.list(System.err);

    }
    
    public void desactivateError() {
	general.put("go-errors", ""); // remove go-errors
	general.put("errors", ""); // remove errors
    }

    /**
     * Returns a string representation of the object.
     */
    public void print(PrintWriter out) {
	this.out = out; // must be in first !
	String output = processSimple("document");
	if (output != null) {
	    out.println(output);
	} else {
	    out.println("An error occurred during the output "
			+ "of your style sheet.");
	    out.println("Please correct your request ");
	    out.println(" or send a mail to "
			+ " www-validator-css@w3.org");
	}
	
	out.flush();
    }

    void produceParseException(CssParseException error, StringBuffer ret) {
	if (error.getContexts() != null && error.getContexts().size() != 0) {
	    StringBuffer buf = new StringBuffer();
	    for (Enumeration e = error.getContexts().elements(); 
		 e.hasMoreElements();) {
		Object t = e.nextElement();
		if (t != null) {
		    buf.append(t);
		    if (e.hasMoreElements())
			buf.append(", ");
		}
	    }
	    if (buf.length() != 0) {
		ret.append("Context : ").append(buf).append(' ');
	    }
	}
	if (error.getProperty() != null) {
	    ret.append("in property : ").append(error.getProperty());
	    ret.append('\n');
	}

	if (error.getException() != null && (error.getMessage() != null)) {
	    ret.append('\t');
	    if (error.isParseException()) {
		ret.append(error.getMessage());
	    } else {
		Exception ex = error.getException();
		if (ex instanceof NumberFormatException) {
		    ret.append("Invalid number");
		} else {
		    ret.append(ex.getMessage());
		}
	    }
	    if (error.getSkippedString() != null) {
		ret.append(" : ");
		ret.append(error.getSkippedString());
	    } else if (error.getExp() != null) {
		ret.append(" : ");
		error.getExp().starts();
		ret.append(queryReplace(error.getExp().toString()));
	    }
	    ret.append('\n');
	} else {
	    ret.append("\t");
	    ret.append("Unrecognized ");
	    ret.append(" - ");
	    ret.append(queryReplace(error.getSkippedString()));
	    ret.append("\n");
	}
    }

    public void produceError() {
	StringBuffer ret = new StringBuffer(1024);
	String oldSourceFile = null;
	
	try {
	    if (errors.getErrorCount() != 0) {
		int i = 0;
		for (CssError[] error = errors.getErrors(); 
		          i < error.length; i++) {
		    Exception ex = error[i].getException();
		    if (!error[i].getSourceFile().equals(oldSourceFile)) {
			oldSourceFile = error[i].getSourceFile();
			ret.append("\nURI : ").append(oldSourceFile);
			ret.append('\n');
		    }
		    ret.append(" Line : ").append(error[i].getLine());
		    ret.append(" ");
		    
		    if (ex instanceof FileNotFoundException) {
			ret.append("File not found ");
			ret.append(ex.getMessage());
			ret.append('\n');
			
		    } else if (ex instanceof CssParseException) {
			produceParseException((CssParseException) ex, ret);
		    } else if (ex instanceof InvalidParamException) {
			ret.append("\n\t");
			ret.append(queryReplace(ex.getMessage())).append('\n');
		    } else if (ex instanceof IOException) {
			String stringError = ex.toString();
			int index = stringError.indexOf(':');
			ret.append(stringError.substring(0, index));
			ret.append(" : ");
			ret.append(ex.getMessage()).append('\n');
			
		    } else if (error[i] instanceof CssErrorToken) {
			CssErrorToken terror = (CssErrorToken) error[i];
			ret.append("   ");
			ret.append(terror.getErrorDescription()).append(" : ");
			ret.append(terror.getSkippedString()).append('\n');
			
		    } else {
			ret.append(ex).append(" \n");
			
			if (ex instanceof NullPointerException) {
			    // ohoh, a bug
			    ex.printStackTrace();
			}
		    }
		}
	    }
	    out.println(ret.toString());
	} catch (Exception e) {
	    out.println("An error appears during error's ouput, sorry.");
	    e.printStackTrace();
	}
    }
    
    public void produceWarning() {
	StringBuffer ret = new StringBuffer(1024);
	String oldSourceFile = "";
	int oldLine = -1;
	String oldMessage = "";
	try {
	    if (warnings.getWarningCount() != 0) {
		int i = 0;
		warnings.sort();
		for (Warning[] warning = warnings.getWarnings(); 
		     i < warning.length; i++) {
		    
		    Warning warn = warning[i];
		    if (warn.getLevel() <= warningLevel) {
			if (!warn.getSourceFile().equals(oldSourceFile)) {
			    oldSourceFile = warn.getSourceFile();
			    ret.append("\n URI : ");
			    ret.append(oldSourceFile).append('\n');
			} 
			if (warn.getLine() != oldLine || 
			    !warn.getWarningMessage().equals(oldMessage)) {
			    oldLine = warn.getLine();
			    oldMessage = warn.getWarningMessage();
			    ret.append("Line : ").append(oldLine);
			    
			    if (warn.getLevel() != 0) {
				ret.append(" Level : ");
				ret.append(warn.getLevel());
			    }
			    ret.append(" ").append(oldMessage);
			    
			    if (warn.getContext() != null) {
				ret.append(" : ").append(warn.getContext());
			    }
			    
			    ret.append(" \n");
			}
		    }
		}
	    }
	    out.println(ret.toString());
	} catch (Exception e) {
	    out.println("An error appears during warning's ouput, sorry.");
	    e.printStackTrace();
	}
    }
    
    private String queryReplace(String s) {
	if (s == null) {
	    return "[empty string]";
	} else {
	    return s;
	}
    }

    private final String processSimple(String s) {
	return processStyle(general.getProperty(s), general);
    }
    
    private String processStyle(String str, Properties prop) {
	if (str == null) {
	    return "";
	}
	
	try {
	    int i = 0;
	    while ((i = str.indexOf("<!-- #", i)) >= 0) {
		int lastIndexOfEntity = str.indexOf("-->", i);
		String entity = 
		    str.substring(i+6, 
				  lastIndexOfEntity - 1).toLowerCase();
		
		if (entity.equals("warning")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		    produceWarning();
		} else if (entity.equals("error")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		    produceError();
		} else {
		    String value = prop.getProperty(entity);
		    if (value != null) {
			str = str.substring(0, i) + value + 
			    str.substring(lastIndexOfEntity+3);
		    } else {
			i += 6; // skip this unknown entity
		    }
		}
	    }
	    /*	    if (errors.getErrorCount() == 0 && 
		    warnings.getWarningCount() == 0) {
		    out.print("No errors or warnings found");
		    }*/
	    return str;
	} catch (Exception e) {
	    e.printStackTrace();
	    return str;
	}
    }
    
    public final static void printAvailableFormat(PrintWriter out) {
	Enumeration e = availableFormat.propertyNames();
	out.println( " -- listing available output format --" );
	while (e.hasMoreElements()) {
	    String key = ((String) e.nextElement()).toLowerCase();
	    out.println( "Format : " + key );
	    out.println( "   File : " + getDocumentName(key) );
	}
	out.flush();
    }
    
    private Properties setDocumentBase(String document) {
	Properties properties = (Properties) formats.get(document);
	if (properties == null) {
	    URL url;
	    properties = new Properties();
	    try {
		url = StyleSheetGenerator.class.getResource(document);
		java.io.InputStream f = url.openStream();
		properties.load(f);
		f.close();
		properties.put("author","Philippe Le Hegaret");
		properties.put("author-email",
			       "www-validator-css@w3.org");
	    } catch (Exception e) {
		System.err.println("org.w3c.css.css.StyleSheetGenerator: "
				   + "couldn't load properties " + document);
		System.err.println("  " + e.toString() );
		printAvailableFormat(new PrintWriter(System.err));
	    }
	    formats.put(document, properties);
	}

	return new Properties(properties);
    }
    
    private final static String getDocumentName(String documentName) {
	String document = 
	    availableFormat.getProperty(documentName.toLowerCase());
	if (document == null) {
	    System.err.println( "Unable to find " + 
				documentName.toLowerCase() + 
				" output format" );
	    return documentName;
	} else {
	    return document;
	}
    }
    
    static {
	URL url;
	availableFormat = new Properties();
	try {
	    url = StyleSheetGenerator.class.getResource("format.properties");
	    java.io.InputStream f = url.openStream();
	    availableFormat.load(f);
	    f.close();
	} catch (Exception e) {
	    System.err.println("org.w3c.css.css.StyleSheetGenerator: "
			       + "couldn't load format properties ");
	    System.err.println("  " + e.toString() );
	}
    }
}

