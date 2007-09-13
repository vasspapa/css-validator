//
// $Id: CssSelectors.java,v 1.21 2007-09-13 10:04:54 julien Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.parser;

import java.util.ArrayList;
import java.util.Enumeration;

import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.selectors.AdjacentSelector;
import org.w3c.css.selectors.AttributeSelector;
import org.w3c.css.selectors.ChildSelector;
import org.w3c.css.selectors.DescendantSelector;
import org.w3c.css.selectors.PseudoClassSelector;
import org.w3c.css.selectors.PseudoElementSelector;
import org.w3c.css.selectors.PseudoFactory;
import org.w3c.css.selectors.Selector;
import org.w3c.css.selectors.SelectorsList;
import org.w3c.css.selectors.TypeSelector;
import org.w3c.css.selectors.attributes.AttributeExact;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionContains;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionLang;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNot;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNthChild;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNthLastChild;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNthLastOfType;
import org.w3c.css.selectors.pseudofunctions.PseudoFunctionNthOfType;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Util;
import org.w3c.css.util.Warnings;

/**
 * This class manages all contextual selector.
 *
 * <p>
 * Note:<BR>
 * Invoke a <code>set</code> function to change the selector clears all
 * properties !
 *
 * @version $Revision: 1.21 $
 */
public final class CssSelectors extends SelectorsList implements CssSelectorsConstant {

    ApplContext ac;

    /**
     * At rule statement
     */
    AtRule atRule;

    /**
     * The element.
     */
    String element;

    char connector = DESCENDANT;

    /**
     * The next context.
     */
    protected CssSelectors next;

    // true if the element is a block-level element
    private  boolean isBlock;

    CssStyle properties;

    // all hashCode (for performance)
    private int hashElement;

    //private int hashGeneral;

    // The CssStyle to use
    private static Class style;

    // see isEmpty and addProperty
    private boolean Init;

    /**
     * Create a new CssSelectors with no previous selector.
     */
    public CssSelectors(ApplContext ac) {
	super(ac);
	style = ac.getCssSelectorsStyle();
	try {
	    properties = (CssStyle) style.newInstance();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	this.ac = ac;
    }

    private CssSelectors(Class style) {
	super();
	CssSelectors.style = style;
	try {
	    properties = (CssStyle) style.newInstance();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	this.ac = null;
    }

    /**
     * Create a new CssSelectors with a previous selector.
     *
     * @param next
     *            the next selector
     */
    public CssSelectors(CssSelectors next) {
	this(CssSelectors.style);
	this.next = next;
    }

    /**
     * Create a new CssSelectors with a previous selector.
     *
     * @param next
     *            the next selector
     */
    public CssSelectors(ApplContext ac, CssSelectors next) {
	this(ac);
	this.next = next;
    }

    /**
     * Set the style for all contexts. Don't forget to invoke this method if you
     * want a style !
     *
     * @param style0
     *            the style
     */
    public void setStyle(Class style0) {
	Util.verbose("Style is : " + style0);
	style = style0;
    }

    /**
     * Set the attribute atRule
     *
     * @param atRule
     *            the new value for the attribute
     */
    public void setAtRule(AtRule atRule) {
	this.atRule = atRule;
    }

    /**
     * Returns the attribute atRule
     *
     * @return the value of the attribute
     */
    public AtRule getAtRule() {
	return atRule;
    }

    /**
     * Get the element.
     */
    public String getElement() {
	return element;
    }

    /**
     * Returns <code>true</code> if the element is a block level element (HTML
     * only)
     */
    public boolean isBlockLevelElement() {
	return isBlock;
    }

    public void addPseudo(String pseudo) throws InvalidParamException {
	if(pseudo == null) {
	    return;
	}

	String profile = ac.getProfile();
	if(profile == null || profile.equals("") || profile.equals("none")) {
	    profile = ac.getCssVersion();
	}

	// is it a pseudo-class?
	String[] ps = PseudoFactory.getPseudoClass(profile);
	if(ps != null) {
	    for(int i = 0; i < ps.length; i++) {
		if(pseudo.equals(ps[i])) {
		    addPseudoClass(new PseudoClassSelector(pseudo));
		    return;
		}
	    }
	}
	// it's not a pseudo-class

	// is it a pseudo-element?
	ps = PseudoFactory.getPseudoElement(profile);
	if(ps != null) {
	    for(int i = 0; i < ps.length; i++) {
		if(pseudo.equals(ps[i])) {
		    addPseudoElement(new PseudoElementSelector(pseudo));
		    return;
		}
	    }
	}
        
        // the ident isn't a valid pseudo-something
        throw new InvalidParamException("pseudo", ":" + pseudo, ac);
    }

    public void setPseudoFun(String pseudo, String param)
    throws InvalidParamException {
	String profile = ac.getProfile();
	if(profile == null || profile.equals("") || profile.equals("none")) {
	    profile = ac.getCssVersion();
	}

	String[] ps = PseudoFactory.getPseudoFunction(profile);
	if(ps != null) {
	    for(int i = 0; i < ps.length; i++) {
		if(pseudo.equals(ps[i])) {
		    if(pseudo.equals("contains")) {
			addPseudoFunction(
				new PseudoFunctionContains(pseudo, param));
			return;
		    }
		    if(pseudo.equals("lang")) {
			addPseudoFunction(
				new PseudoFunctionLang(pseudo, param));
			return;
		    }
		    if(pseudo.equals("not")) {
			addPseudoFunction(
				new PseudoFunctionNot(pseudo, param));
			return;
		    }
		    if(pseudo.equals("nth-child")) {
			try {
			    addPseudoFunction(
				    new PseudoFunctionNthChild(pseudo, param));
			}
			catch(NumberFormatException e) {
			    throw new InvalidParamException("pseudo", ":" + pseudo, ac);
			}
			return;
		    }
		    if(pseudo.equals("nth-last-child")) {
			try {
			    addPseudoFunction(
				    new PseudoFunctionNthLastChild(
					    pseudo, param));
			}
			catch(NumberFormatException e) {
			    throw new InvalidParamException("pseudo", ":" + pseudo, ac);
			}
			return;
		    }
		    if(pseudo.equals("nth-of-type")) {
			try {
			    addPseudoFunction(
				    new PseudoFunctionNthOfType(
					    pseudo, param));
			}
			catch(NumberFormatException e) {
			    throw new InvalidParamException("pseudo", ":" + pseudo, ac);
			}
			return;
		    }
		    if(pseudo.equals("nth-last-of-type")) {
			try {
			    addPseudoFunction(
				    new PseudoFunctionNthLastOfType(
					    pseudo, param));
			}
			catch(NumberFormatException e) {
			    throw new InvalidParamException("pseudo", ":" + pseudo, ac);
			}
			return;
		    }
		}
	    }
	    throw new InvalidParamException("pseudo", ":" + pseudo, ac);
	}
    }

    public void addType(TypeSelector type) throws InvalidParamException {
	super.addType(type);
	element = type.getName();
	hashElement = element.hashCode();
    }

    public void addDescendant(DescendantSelector descendant)
    throws InvalidParamException {
	super.addDescendant(descendant);
	connector = DESCENDANT;
    }

    public void addChild(ChildSelector child) throws InvalidParamException {
	super.addChild(child);
	connector = CHILD;
    }

    public void addAdjacent(AdjacentSelector adjacent)
    throws InvalidParamException {
	super.addAdjacent(adjacent);
	connector = ADJACENT;
    }

    public void addAttribute(AttributeSelector attribute)
    throws InvalidParamException {
	for(int i = 0; i < size(); i++) {
	    Selector s = (Selector) getSelector(i);
	    // add warnings if some selectors are incompatible
	    // e.g. [lang=en][lang=fr]
	    if(s instanceof AttributeSelector) {
		((AttributeSelector) s).applyAttribute(ac, attribute);
	    }
	}
	super.addAttribute(attribute);
    }

    /**
     * Adds a property to this selector.
     *
     * @param property
     *            The property.
     * @param warnings
     *            For warning report.
     */
    public void addProperty(CssProperty property, Warnings warnings) {
	Init = true;
	if (properties != null) {
	    properties.setProperty(ac, property, warnings);
	} else {
	    System.err.println("[ERROR] Invalid state in "
		    + "org.w3c.css.parser.CssSelectors#addProperty");
	    System.err.println("[ERROR] Please report BUG");
	}
    }

    public CssStyle getStyle() {
	return properties;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	// I'm in reverse order, so compute the next before the current
	StringBuffer sbrep = new StringBuffer();
	if (next != null) {
	    sbrep.append(next.toString());
	}
	sbrep.append(super.toString());
	return sbrep.toString();
    }

    /**
     * Get a hashCode.
     */
    /*public int hashCode() {
	if (hashGeneral == 0) {
	    if (atRule instanceof AtRuleFontFace) {
		hashGeneral = atRule.hashCode();
	    } else {
		String s = toString();
		hashGeneral = s.hashCode();
		for (int i = 0; i < s.length(); i++) {
		    hashGeneral += (int) s.charAt(i);
		}
	    }
	}
	return hashGeneral;
    }*/

    /**
     * Returns <code>true</code> if the selector is equals to an another.
     *
     * @param selector
     *            The selector to compare
     */
    public boolean equals(Object selector) {
	if ((selector == null) || !(selector instanceof CssSelectors)) {
	    return false;
	}
	CssSelectors s = (CssSelectors) selector;

	if ((atRule instanceof AtRulePage)
		|| (atRule instanceof AtRuleFontFace)) {
	    return atRule.equals(s.atRule);
	}
	if (hashCode() == s.hashCode()) {
	    if (atRule == null) {
		return (s.getAtRule() == null);
	    } else {
		return atRule.canApply(s.getAtRule());
	    }
	} else {
	    return false;
	}
    }

    /**
     * Set the previous selector.
     *
     * @param next
     *            the previous selector.
     */
    public void setNext(CssSelectors next) {
	this.next = next;
	Invalidate();
    }

    /**
     * Get the previous selector.
     */
    public CssSelectors getNext() {
	return next;
    }

    /**
     * Returns <code>true</code> if there is no property in this document.
     */
    public boolean isEmpty() {
	return !Init;
    }

    public void addAttribute(String attName, String value)
    throws InvalidParamException {
	if (ac.getProfile() != null && !"".equals(ac.getProfile())) {
	    if (ac.getProfile().equals("mobile")) {
		throw new InvalidParamException("notformobile", "attributes",
			ac);
	    }
	} else {
	    addAttribute(new AttributeExact(attName, value));
	    Invalidate();
	}
    }

    void Invalidate() {
	// invalidate all pre-computation in this selectors
	setSpecificity(0);
	//hashGeneral = 0;

	if (Init) {
	    // yes I invalidate all properties too !
	    try {
		properties = (CssStyle) style.newInstance();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    final boolean canApply(ArrayList attrs, ArrayList attrs2) {
	if(attrs.size() > 0) {
	    for(int i = 0; i < attrs.size(); i++) {
		Selector selector = (Selector) attrs.get(i);

		Selector other = null;
		int j = 0;
		for(; j < attrs2.size(); j++) {
		    other = (Selector) attrs2.get(j);
		    if(!other.equals(selector)) {
			other = null;
		    }
		    else {
			break;
		    }
		}
		if (other != null) {
		    if (!selector.canApply(other)) {
			return false;
		    }
		} else {
		    return false;
		}
	    }
	    return true;
	}
	return true;

    }

    /**
     * Returns <code>true</code> if the selector can matched this selector.
     *
     * <p>
     * Examples:<br>
     * <OL>
     * <LI><code>H1.canApply(HTML BODY H1)</code> returns <code>true</code>
     * <LI><code>H1.canApply(HTML BODY H1 EM)</code> returns
     * <code>false</code>
     * <LI><code>(H1 EM).canApply(HTML BODY H2 EM)</code> returns
     *     <code>false</code>
     * <LI><code>(HTML EM).canApply(HTML BODY H2 EM)</code> returns
     *     <code>true</code>
     * </OL>
     *
     * <p>
     * Note:<BR>
     * In principle, if you work with a HTML document, your selector should
     * start with HTML BODY. Because you are always in this context when you
     * parse the text in a HTML document.
     *
     * @param selector
     *            the selector to match
     * @see            org.w3c.css.css.CssCascadingOrder#order
     */
    public boolean canApply(CssSelectors selector) {
	if ((atRule instanceof AtRulePage)
		|| (atRule instanceof AtRuleFontFace)) {
	    return atRule.canApply(selector.atRule);
	}
	// current work - don't touch
	Util.verbose(getSpecificity() + " canApply this " + this
		+ " selector: " + selector);
	Util.verbose("connector " + connector);
	Util.verbose(getSelectors().toString());
	Util.verbose(selector.getSelectors().toString());

	if ((hashElement != selector.hashElement) && hashElement != 0) {
	    // here we are in this case :
	    // H1 and HTML BODY H1 EM
	    // don't do anything !
	    // the cascading order algorithm resolves this case like this :
	    //
	    // if (for all contexts) !canApply(selector)
	    //       go and see canApply(selector.getNext())
	    //
	    // for further informations,
	    //                     see org.w3c.css.css.CssCascadingOrder#order
	    Util.verbose("canApply RETURNS FALSE");
	    return false;
	} else {
	    if (next == null || selector.next == null) {
		boolean result = canApply(getSelectors(), selector.getSelectors());
		Util.verbose("canApply RETURNS " + result);
		return result;
	    } else {
		return next.canMatch(selector.next);
	    }
	}
    }

    /**
     * Returns true if the selector can matched another selector. called by
     * canApply
     *
     * @param selector
     *            The selector to compare
     */
    private boolean canMatch(CssSelectors selector) {
	boolean result = canApply(getSelectors(), selector.getSelectors());
	// current work
	Util.verbose("canMatched this " + this + " selector: " + selector);
	Util.verbose("connector " + connector);
	Util.verbose(getSelectors().toString());
	Util.verbose(selector.getSelectors().toString());
	Util.verbose("canMatched for attributes :" + result);

	if ((hashElement != selector.hashElement) && hashElement != 0) {
	    if ((connector == DESCENDANT) && (selector.next != null)) {
		// here we are in this case :
		// H1 and HTML BODY H1 EM
		// H1 can't matched EM but EM have next
		return canMatch(selector.next);
	    } else {
		// here we are in this case :
		// H1 and HTML
		// H1 can't matched HTML and HTML don't have next
		Util.verbose("canMatched RETURN FALSE");
		return false;
	    }
	}

	if (next == null || selector.next == null) {
	    // here we are in this case :
	    // H1 and BODY HTML H1
	    // or :
	    // HTML BODY and BODY (this case won't appear in principle)
	    Util.verbose("canMatched RETURN " + result);
	    return canApply(getSelectors(), selector.getSelectors());
	} else {
	    // here we are in this case :
	    // BODY H1 and HTML BODY H1
	    return next.canMatch(selector.next);
	}
    }
    
    public void findConflicts(ApplContext ac, Warnings warnings,
	    Enumeration allSelectors) {
	CssStyle style = getStyle();
	style.findConflicts(ac, warnings, this, allSelectors);
    }
}
