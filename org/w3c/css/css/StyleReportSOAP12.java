// $Id: StyleReportSOAP12.java,v 1.5 2003-12-04 19:12:05 plehegar Exp $
// Author: Yves Lafon <ylafon@w3.org>
// (c) COPYRIGHT MIT, ERCIM and Keio, 2003.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.css;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Date;
import java.util.TimeZone;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.w3c.css.parser.analyzer.ParseException;
import org.w3c.css.parser.CssParseException;
import org.w3c.css.parser.Errors;
import org.w3c.css.parser.CssError;
import org.w3c.css.parser.CssErrorToken;
import org.w3c.css.parser.CssFouffa;
import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssSelectorsConstant;
import org.w3c.css.parser.AtRule;
import org.w3c.css.parser.AtRulePage;
import org.w3c.css.parser.AtRuleMedia;
import org.w3c.css.parser.AtRuleFontFace;
import org.w3c.css.properties.CssProperty;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.SortedHashtable;
import org.w3c.css.util.Warnings;
import org.w3c.css.util.Warning;
import org.w3c.css.util.Util;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision: 1.5 $
 */
public final class StyleReportSOAP12 extends StyleReport
    implements CssPrinterStyle {

    StyleSheet style;
    Vector items;
    Warnings warnings;
    Errors errors;
    ApplContext ac;
    
    private CssSelectors selector;
    private CssProperty property;
    private PrintWriter out;
    private int warningLevel;
    private Properties general;
    
    private static Properties availableFormat;    
    private static Properties availablePropertiesURL;
    private static Hashtable formats = new Hashtable();
    int counter = 0;

    /**
     * Create a new StyleSheetGenerator
     *
     * @param title        The title for the generated document
     * @param style        The style sheet
     * @param document     The name of the source file
     * @param warningLevel If you want to reduce warning output.
     *                     (-1 means no warnings)
     */
    public StyleReportSOAP12(ApplContext ac, 
			     String title, 
			     StyleSheet style,
			     String document,
			     int warningLevel) {


	if (document == null) {
	    document = "soap12.en";
	}
	if (Util.onDebug) {
	    System.err.println( "document format is " + document );
	}
	this.ac = ac;
	this.style = style;
	general = new Properties(setDocumentBase(getDocumentName(ac,
								 document)));
	general.put("file-title", title);
	warnings = style.getWarnings();
	errors = style.getErrors();
	items =  style.newGetRules();

	this.warningLevel = warningLevel;
	
	general.put("cssversion", ac.getCssVersion());
	general.put("errors-count", 
		    Integer.toString(errors.getErrorCount()));
	general.put("warnings-count", 
		    Integer.toString(warnings.getWarningCount()));
	general.put("rules-count", 
		    Integer.toString(items.size()));
	general.put("isvalid",(errors.getErrorCount() == 0) ? "true":"false");
	
	if (ac.getContentEncoding() != null) {
	    general.put("encoding",
			" encoding=\""+ac.getContentEncoding()+"\" ");
	} else {
	    general.put("encoding"," ");
	}
	if (errors.getErrorCount() == 0) {
	    desactivateError();
	}
	if ((errors.getErrorCount() != 0) 
	        || (!title.startsWith("http://"))) {
	    general.put("no-errors", "");
	}
	if (style.charset == null) {
	    general.put("charset-rule", "");
	}
	if (warnings.getWarningCount() == 0 || warningLevel == -1) {
	    general.put("go-warnings", ""); // remove go-warnings
	    general.put("warnings", ""); // remove warnings
	}
	if (items.size() == 0) {
	    general.put("go-rules", ""); // remove go-rules
	    general.put("rules", ""); // remove rules
	    general.put("no-errors", "");
	} else {
	    general.put("no-rules", ""); // remove no-rules
	}
	
	if (errors.getErrorCount() != 0 || warnings.getWarningCount() != 0) {
	    // remove no-error-or-warning
	    general.put("no-error-or-warning", ""); 
	}

	if (Util.onDebug) general.list(System.err);

	DateFormat df = null;

	if (ac.getLang() != null) {
	    try {
		df = DateFormat
		    .getDateTimeInstance(DateFormat.FULL, 
					 DateFormat.FULL,
				       new Locale(ac.getLang().substring(0, 2),
						    "US"));
	    } catch (Exception e) {
		df = DateFormat.getDateTimeInstance(DateFormat.FULL, 
						    DateFormat.FULL,
						    Locale.US);
	    }
	}
	if (df != null) {
	    general.put("today", df.format(new Date()));
	} else {
	    general.put("today", new Date().toString());
	}
	SimpleDateFormat formatter
	    = new SimpleDateFormat ("yyyy.MM.dd'T'hh:mm:ss'Z'");
	formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	general.put("currentdate", formatter.format(new Date()));
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
	    out.println(ac.getMsg().getGeneratorString("request"));
	}
	
	out.flush();
    }
    
    // prints the stylesheet at the screen
    public void produceStyleSheet() {

    }
    
    public void print(CssProperty property) {
	Properties prop = new Properties(general); 
	prop.put("property-name", property.getPropertyName().toString());
	prop.put("property-value", property.toString());
	
	if (!property.getImportant()) {
	    prop.put("important-style", "");
	}
	out.print(processStyle(prop.getProperty("declaration"), prop));
    }
    
    public void produceParseException(CssParseException error) {
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
		out.print("parse-error</m:errortype>\n<m:context>");
		out.print(buf);
		out.print("</m:context>\n");
	    }
	}
	String name = error.getProperty();
	if ((name != null) && (getURLProperty(name) != null)) {
	    out.print("<m:property>");
	    out.print(name);
	    out.print("</m:property>\n");
	}
	if ((error.getException() != null) && (error.getMessage() != null)) {
	    if (error.isParseException()) {
		out.print("<m:message>");
		out.print(queryReplace(error.getMessage()));
		out.print("</m:message>\n");
	    } else {
		Exception ex = error.getException();
		if (ex instanceof NumberFormatException) {
		    out.print("<m:errorsubtype>invalid-number"+
			      "</m:error-subtype>\n");
		} else {
		    out.print("<m:message>");
		    out.print(queryReplace(ex.getMessage()));
		    out.print("</m:message>\n");
		}
	    }
	    if (error.getSkippedString() != null) {
		out.print("<m:skippedstring>");
		out.print(queryReplace(error.getSkippedString()));
		out.print("</m:skippedstring>\n");
	    } else if (error.getExp() != null) {
		out.print("<m:expression>\n<m:start>");
		out.print(queryReplace(error.getExp().toStringFromStart()));
		out.print("</m:start>\n<m:end>");
		out.print(queryReplace(error.getExp().toString()));
		out.print("</m:end>\n</m:expression>\n");		
	    }
	} else {
	    out.print("<m:errorsubtype>unrecognized</m:errorsubtype>\n");
	    out.print("<m:skippedstring>");
	    out.print(queryReplace(error.getSkippedString()));
	    out.print("</m:skippedstring>\n");
	}
    }

    public void produceError() {
	String oldSourceFile = null;
	boolean open = false;

	try {
	    if (errors.getErrorCount() != 0) {
		int i = 0;
		for (CssError[] error = errors.getErrors(); 
		          i < error.length; i++) {
		    Exception ex = error[i].getException();
		    String file = error[i].getSourceFile();
		    if (!file.equals(oldSourceFile)) {
			oldSourceFile = file;
			if (open) {
			    out.print("</m:errorlist>\n");
			}
			out.print("<m:errorlist>\n");
			open = true;
		    }
		    out.print("<m:error>\n<m:line>\n");
		    out.print(error[i].getLine());
		    out.print("</m:line>\n<m:errortype>");
		    if (ex instanceof FileNotFoundException) {
			out.print("not-found");
			out.print("</m:errortype>\n<m:message>");
			out.print(ex.getMessage());
			out.print("</m:message>\n");			
		    } else if (ex instanceof CssParseException) {
			produceParseException((CssParseException) ex);
		    } else if (ex instanceof InvalidParamException) {
			out.print("invalid-parameter");
			out.print("</m:errortype>\n<m:message>");
			out.print(queryReplace(ex.getMessage()));
			out.print("</m:message>\n");
		    } else if (ex instanceof IOException) {
			out.print("IOException</m:errortype>\n");
			out.print("<m:message>");
			out.print(queryReplace(ex.getMessage()));
			out.print("</m:message>\n");
		    } else if (error[i] instanceof CssErrorToken) {
			out.print("csserror</m:errortype>");
			CssErrorToken terror = (CssErrorToken) error[i];
			out.print("\n<m:description>");
			out.print(terror.getErrorDescription());
			out.print("</m:description>\n");
			out.print("<m:skippedstring>");
			out.print(terror.getSkippedString());
			out.print("</m:skippedstring>\n");
		    } else {
			out.print("uncaught");
			out.print("</m:errortype>\n<m:message>");
			out.print(queryReplace(ex.getMessage()));
			out.print("</m:message>\n");
			if (ex instanceof NullPointerException) {
			    // ohoh, a bug
			    out.print("nullpointer");
			    out.print("</m:errortype>\n<m:message>");
			    ex.printStackTrace(out);
			    out.print("</m:message>\n");
			}
		    }
		    out.print("</m:error>\n");
		}
		out.print("</m:errorlist>");
	    }
	} catch (Exception e) {
	    out.print("<m:processingerror>");
	    out.println(ac.getMsg().getGeneratorString("request"));
	    e.printStackTrace(out);
	    out.print("</m:processingerror>\n");
	}
    }
    
    public void produceWarning() {
	boolean open = false;
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
			    if (open) {
				out.print("</m:warninglist>\n");
			    }
			    oldSourceFile = warn.getSourceFile();
			    out.print("<m:warninglist>\n");
			    open = true;
			}
			if (warn.getLine() != oldLine || 
			    !warn.getWarningMessage().equals(oldMessage)) {
			    oldLine = warn.getLine();
			    oldMessage = warn.getWarningMessage();
			    out.print("<m:warning>\n<m:line>");
			    out.print(oldLine);
			    out.print("</m:line>\n");
			    
			    //    if (warn.getLevel() != 0) {
			    //	ret.append(" Level : ");
			    //	ret.append(warn.getLevel());
			    //}
			    out.print("<m:level>");
			    out.print(warn.getLevel());
			    out.print("</m:level>\n");
//			    out.print("<message>");
//			    out.print(oldMessage);
//			    out.print("</message>\n");
			    if (warn.getContext() != null) {
				out.print("<m:context>");
				out.print(warn.getContext());
				out.print("</m:context>\n");
			    }
			    out.print("</m:warning>\n");
			}
		    }
		}
		out.print("</m:warninglist>");
	    }
	} catch (Exception e) {
	    out.print("<m:processingerror>");
	    out.println(ac.getMsg().getGeneratorString("request"));
	    e.printStackTrace(out);
	    out.print("</m:processingerror>\n");
	}
    }
    
    private String queryReplace(String s) {
	if (s != null) {
	    int len = s.length();
	    StringBuffer ret = new StringBuffer(len);	    
	    
	    for (int i = 0; i < len; i++) {
		char c = s.charAt(i);
		if (c == '<') {
		    ret.append("&lt;");
		} else if (c == '>') {
		    ret.append("&gt;");
		} else {
		    ret.append(c);
		}
	    }
	    return ret.toString();
	} else {
	    return "[empty string]";
	}
    }
    
    private final String processSimple(String s) {
	return processStyle(general.getProperty(s), general);
    }
    
    private String processStyle(String str, Properties prop) {
	try {
	    int i = 0;
	    while ((i = str.indexOf("<!-- #", i)) >= 0) {
		int lastIndexOfEntity = str.indexOf("-->", i);
		String entity = 
		    str.substring(i+6, 
				  lastIndexOfEntity - 1).toLowerCase();

		if (entity.equals("rule")) {
                    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		    produceStyleSheet();
		} else if (entity.equals("selectors")) {
		    if (selector.getNext() != null) {
			// contextuals selectors
			String value = prop.getProperty(entity);
			if (value != null) {
			    str = str.substring(0, i) + value + 
				str.substring(lastIndexOfEntity+3);
			} else {
			    i += 6; // skip this unknown entity
			}
		    } else {
			str = str.substring(lastIndexOfEntity+3);
			i = 0;
		    }
		} else if (entity.equals("selector")) {
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		} else if (entity.equals("charset")) {
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		    out.print(style.charset);
		} else if (entity.equals("declaration")) {
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		} else if (entity.equals("warning")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		    produceWarning();
		} else if (entity.equals("error")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		    produceError();
		} else if (entity.equals("hook-html-validator")) {
		    out.print(str.substring(0, i));
		    str = str.substring(lastIndexOfEntity+3);
		    i = 0;
		    if (style.getType().equals("text/html")) { 
			out.println(ac.getMsg().getGeneratorString("doc-html",
                                    general.get("file-title").toString()));
		    } else {
			out.println(ac.getMsg().getGeneratorString("doc"));
		    }
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
	    out.println( "   File : " + getDocumentName(null, key) );
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
		properties.put("author","www-validator-css");
		properties.put("author-email", "Email.html");
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
    
    private final static String getDocumentName(ApplContext ac, 
						String documentName) {
	documentName = documentName.toLowerCase();
	String document = null;
	if (ac != null && ac.getLang() != null) {
	    StringTokenizer tokens = new StringTokenizer(ac.getLang(), ",");
	    
	    while (tokens.hasMoreTokens()) {
		String l = tokens.nextToken().trim().toLowerCase();
		document = availableFormat.getProperty(documentName + "." + l);
		if (document != null) {
		    break;
		}
		int minusIndex = l.indexOf('-');
		if (minusIndex != -1) {
		    // suppressed -cn in zh-cn (example)
		    l = l.substring(0, minusIndex);
		    document = availableFormat.getProperty(documentName 
							   + "." + l);
		}
		if (document != null) {
		    break;
		}		
	    }
	}
	if (document == null) {
	    document = availableFormat.getProperty(documentName);
	}
	if (document == null) {
	    System.err.println( "Unable to find " + 
				documentName + " output format" );
	    return documentName;
	} else {
	    return document;
	}
    }
    
    private final static String getURLProperty(String name) {
	return availablePropertiesURL.getProperty(name);
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

	availablePropertiesURL = new Properties();
	try {
	    url = StyleSheetGenerator.class.getResource("urls.properties");
	    java.io.InputStream f = url.openStream();
	    availablePropertiesURL.load(f);
	    f.close();
	} catch (Exception e) {
	    System.err.println("org.w3c.css.css.StyleSheetGenerator: "
			       + "couldn't load URLs properties ");
	    System.err.println("  " + e.toString() );
	}
    }
}
