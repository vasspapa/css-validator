//
// $Id: CssBackgroundCSS1.java,v 1.2 2005-08-26 14:09:49 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: CssBackgroundCSS1.java,v $
 * Revision 1.2  2005-08-26 14:09:49  ylafon
 * All changes made by Jean-Guilhem Rouel:
 *
 * Fix for bugs: 1269, 979, 791, 777, 776, 767, 765, 763, 576, 363
 *
 * Errors in font, the handling of 'transparent', CSS Parser reinits...
 *
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=1269
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=979
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=791
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=777
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=776
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=767
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=765
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=763
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=576
 * http://www.w3.org/Bugs/Public/show_bug.cgi?id=363
 *
 * Revision 1.1  2005/08/23 16:23:12  ylafon
 * Patch by Jean-Guilhem Rouel
 *
 * Better handling of media and properties files
 * Major reorganization of those properties files
 *
 * Revision 1.3  2005/08/08 13:18:12  ylafon
 * All those changed made by Jean-Guilhem Rouel:
 *
 * Huge patch, imports fixed (automatic)
 * Bug fixed: 372, 920, 778, 287, 696, 764, 233
 * Partial bug fix for 289
 *
 * Issue with "inherit" in CSS2.
 * The validator now checks the number of values (extraneous values were previously ignored)
 *
 * Revision 1.2  2002/04/08 21:17:42  plehegar
 * New
 *
 * Revision 3.2  1997/09/09 13:03:45  plehegar
 * Added getColor()
 *
 * Revision 3.1  1997/08/29 13:13:28  plehegar
 * Freeze
 *
 * Revision 2.3  1997/08/26 13:55:55  plehegar
 * Added setSelectors()
 *
 * Revision 2.2  1997/08/20 11:41:11  plehegar
 * Freeze
 *
 * Revision 2.1  1997/08/08 15:52:01  plehegar
 * Nothing
 *
 * Revision 1.3  1997/08/06 17:29:45  plehegar
 * Updated set, now it's a constructor
 *
 * Revision 1.2  1997/07/30 13:19:43  plehegar
 * Updated package
 *
 * Revision 1.1  1997/07/23 21:17:04  plehegar
 * Initial revision
 *
 */
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssPrinterStyle;
import org.w3c.css.parser.CssSelectors;
import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssOperator;
import org.w3c.css.values.CssValue;

/**
 *   <H4>
 *     <A NAME="background">5.3.7 &nbsp;&nbsp; 'background'</A>
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> &lt;background-color&gt; || &lt;background-image&gt; ||
 *   &lt;background-repeat&gt; || &lt;background-attachment&gt; ||
 *   &lt;background-position&gt;<BR>
 *   <EM>Initial:</EM> not defined for shorthand properties<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> no<BR>
 *   <EM>Percentage values:</EM> allowed on &lt;background-position&gt;<BR>
 *   <P>
 *   The 'background' property is a shorthand property for setting the individual
 *   background properties (i.e., 'background-color', 'background-image',
 *   'background-repeat', 'background-attachment' and 'background-position') at
 *   the same place in the style sheet.
 *   <P>
 *   Possible values on the 'background' properties are the set of all possible
 *   values on the individual properties.
 *   <PRE>
 *   BODY { background: red }
 *   P { background: url(chess.png) gray 50% repeat fixed }
 * </PRE>
 *   <P> The 'background' property always sets all the individual background
 *   properties.  In the first rule of the above example, only a value for
 *   'background-color' has been given and the other individual properties are
 *   set to their initial value. In the second rule, all individual properties
 *   have been specified.
 *
 * @version $Revision: 1.2 $
 * @see CssBackgroundColor
 * @see CssBackgroundImage
 * @see CssBackgroundRepeat
 * @see CssBackgroundAttachment
 * @see CssBackgroundPosition 
 */
public class CssBackgroundCSS1 extends CssProperty 
        implements CssOperator, CssBackgroundConstants {
    
    CssBackgroundColorCSS1 color;
    CssBackgroundImageCSS1 image;
    CssBackgroundRepeatCSS1 repeat;
    CssBackgroundAttachmentCSS1 attachment;
    CssBackgroundPositionCSS1 position;

    boolean same;

    /**
     * Create a new CssBackgroundCSS1
     */
    public CssBackgroundCSS1() {
    }  
    
    /**
     * Set the value of the property
     *
     * @param expression The expression for this property
     * @exception InvalidParamException The expression is incorrect
     */  
    public CssBackgroundCSS1(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {
	
	CssValue val = expression.getValue();
	char op = SPACE;
	boolean find = true;
	
	// too many values
	if(check  && expression.getCount() > 6) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	setByUser();
	
	while (find) {
	    find = false;
	    val = expression.getValue();
	    op = expression.getOperator();

	    if (val == null) {
		break;
	    }
	    
	    if (color == null) {
		try {
		    color = new CssBackgroundColorCSS1(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		}
	    }
	    if (!find && image == null) {
		try {
		    image = new CssBackgroundImageCSS1(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, repeat will test this value
		}
	    }
	    if (!find && repeat == null) {
		try {
		    repeat = new CssBackgroundRepeatCSS1(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, attachment will test this value
		}
	    }
	    if (!find && attachment == null) {
		try {
		    attachment = new CssBackgroundAttachmentCSS1(ac, expression);
		    find = true;
		} catch (InvalidParamException e) {
		    // nothing to do, position will test this value
		}
	    }
	    if (!find && position == null) {
		position = new CssBackgroundPositionCSS1(ac, expression);
		find = true;
	    }
	    if (op != SPACE) {
		throw new InvalidParamException("operator", 
						((new Character(op)).toString()),
						ac);
	    }
	    if(check && !find && val != null) {
		throw new InvalidParamException("unrecognize", ac);
	    }
	}
	/*
	if (color == null)
	    color = new CssBackgroundColorCSS1();
	if (image == null)
	    image = new CssBackgroundImageCSS1();
	if (repeat == null)
	    repeat = new CssBackgroundRepeatCSS1();
	if (attachment == null)
	    attachment = new CssBackgroundAttachmentCSS1();
	if (position == null)
	    position = new CssBackgroundPositionCSS1();
	*/
    }
    
    public CssBackgroundCSS1(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }
    
    /**
     * Returns the value of this property
     */
    public Object get() {
	return color;
    }
    
    /**
     * Returns the color
     */
    public final CssValue getColor() {
	if (color == null) {
	    return null;
	} else {
	    return color.getColor();
	}
    }
    
    /**
     * Returns the name of this property
     */  
    public String getPropertyName() {
	return "background";
    }
    
    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	String ret = "";
	if(color != null) {
	    ret += color;
	}
	if(image != null) {
	    if(ret != null) {
		ret += " ";
	    }
	    ret += image;
	}
	if(repeat != null) {
	    if(ret != null) {
		ret += " ";
	    }
	    ret += repeat;
	}
	if(attachment != null) {
	    if(ret != null) {
		ret += " ";
	    }
	    ret += attachment;
	}
	if(position != null) {
	    if(ret != null) {
		ret += " ";
	    }
	    ret += position;
	}
	return ret;
	/*
	if (same) {
	    return inherit.toString();
	} else {
	    String ret = "";
	    if (color.byUser)
		ret += " " + color.toString();
	    if (image.byUser)
		ret += " " + image.toString();
	    if (image.byUser)
		ret += " " + repeat.toString();
	    if (attachment.byUser)
		ret += " " + attachment.toString();
	    if (position.byUser)
		ret += " " + position.toString();
	    return ret.trim();
	}*/
    }
    
    /**
     * Set this property to be important.
     * Overrides this method for a macro
     */  
    public void setImportant() {
	if(color != null) {
	    color.important = true;
	}
	if(image != null) {
	    image.important = true;
	}
	if(repeat != null) {
	    repeat.important = true;
	}
	if(attachment != null) {
	    attachment.important = true;
	}
	if(position != null) {
	    position.important = true;
	}
    }
    
    /**
     * Returns true if this property is important.
     * Overrides this method for a macro
     */
    public boolean getImportant() {
	return ((color == null || color.important) &&
		(image == null || image.important) &&
		(repeat == null || repeat.important) &&
		(attachment == null || attachment.important) &&
		(position == null || position.important));
    }
    
    /**
     * Print this property.
     *
     * @param printer The printer.
     * @see #toString()
     * @see #getPropertyName()
     */  
    public void print(CssPrinterStyle printer) {
	if ((color != null && image != null &&
	     repeat != null && attachment !=null &&
	     position != null) &&
	    (getImportant() ||
	     (!image.important &&
	      !color.important &&
	      !repeat.important &&
	      !attachment.important &&
	      !position.important))) {
	    if (color.byUser || image.byUser || repeat.byUser 
		|| attachment.byUser || position.byUser) {
		printer.print(this);
	    }
	} else {
	    if (color != null)
		color.print(printer);
	    if (image != null)
		image.print(printer);
	    if (repeat != null)
		repeat.print(printer);
	    if (attachment != null)
		attachment.print(printer);
	    if (position != null)
		position.print(printer);
	}	
    }
    
    /**
     * Set the context.
     * Overrides this method for a macro
     *
     * @see org.w3c.css.css.CssCascadingOrder#order
     * @see org.w3c.css.css.StyleSheetParser#handleRule
     */
    public void setSelectors(CssSelectors selector) {
	super.setSelectors(selector);
	if (color != null) {
	    color.setSelectors(selector);
	}
	if (image != null) {
	    image.setSelectors(selector);
	}
	if (repeat != null) {
	    repeat.setSelectors(selector);
	}
	if (attachment != null) {
	    attachment.setSelectors(selector);
	}
	if (position != null) {
	    position.setSelectors(selector);
	}
    }
    
    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	((Css1Style) style).cssBackgroundCSS1.same = same;
	((Css1Style) style).cssBackgroundCSS1.byUser = byUser;

	if(color != null) {
	    color.addToStyle(ac, style);
	}
	if(image != null) {
	    image.addToStyle(ac, style);
	}
	if(repeat != null) {
	    repeat.addToStyle(ac, style);
	}
	if(attachment != null) {
	    attachment.addToStyle(ac, style);
	}
	if(position != null) {
	    position.addToStyle(ac, style);
	}
    }
    
    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */  
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getBackgroundCSS1();
	} else {
	    return ((Css1Style) style).cssBackgroundCSS1;
	}
    }
    
    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */  
    public boolean equals(CssProperty property) {	
	return false; // FIXME
    }
    
    /**
     * Update the source file and the line.
     * Overrides this method for a macro
     *
     * @param line The line number where this property is defined
     * @param source The source file where this property is defined
     */  
    public void setInfo(int line, String source) {
	super.setInfo(line, source);
	if(color != null) {
	    color.setInfo(line, source);
	}
	if(image != null) {
	    image.setInfo(line, source);
	}
	if(repeat != null) {
	    repeat.setInfo(line, source);
	}
	if(attachment != null) {
	    attachment.setInfo(line, source);
	}
	if(position != null) {
	    position.setInfo(line, source);
	}
    }    
}
