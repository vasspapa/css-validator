//
// $Id: Frame.java,v 1.3 2002-08-19 07:23:08 sijtsche Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: Frame.java,v $
 * Revision 1.3  2002-08-19 07:23:08  sijtsche
 * compile bug fixed: getLine erroneous?
 *
 * Revision 1.2  2002/04/08 21:24:12  plehegar
 * New
 *
 * Revision 2.1  1997/08/08 15:51:49  plehegar
 * Nothing
 *
 */

package org.w3c.css.parser;

import org.w3c.css.util.Warning;
import org.w3c.css.util.Warnings;
import org.w3c.css.util.ApplContext;

/**
 * @version $Revision: 1.3 $
 */
public class Frame {

    public  ApplContext ac;
    private Errors errors;
    private Warnings warnings;
    private CssFouffa cssFouffa;

    private String sourceFile;
    private int line;

    /**
     * Create a new Frame.
     *
     * @param cssFouffa  The current parser.
     * @param sourceFile The name of the source file.
     */
    public Frame(CssFouffa cssFouffa, String sourceFile) {
	this.sourceFile = sourceFile;
	this.cssFouffa = cssFouffa;
	errors = new Errors();
	warnings = new Warnings();
    }

    /**
     * Create a new Frame with a line number.
     *
     * @param cssFouffa  The current parser.
     * @param sourceFile The name of the source file.
     * @param beginLine  The begin line
     */
    public Frame(CssFouffa cssFouffa, String sourceFile, int beginLine) {
	this.sourceFile = sourceFile;
	this.cssFouffa = cssFouffa;
	line = beginLine;
	errors = new Errors();
	warnings = new Warnings();
    }

    /**
     * Adds an error to this frame.
     *
     * @param error The new error.
     */
    public void addError(CssError error) {
	error.sourceFile = getSourceFile();
	error.line = getLine();
	errors.addError(error);
    }

    /**
     * Returns all errors.
     */
    public Errors getErrors() {
	return errors;
    }

    /**
     * Adds a warning to this frame.
     *
     * @param warningMessage the warning message
     *                       (see org.w3c.css.util.Messages.properties).
     * @see                  org.w3c.css.util.Warning
     */
    public void addWarning(String warningMessage) {
	warnings.addWarning(new Warning(getSourceFile(), getLine(),
					warningMessage, 0, ac));
    }

    /**
     * Adds a warning to this frame with a message.
     *
     * @param warningMessage the warning message
     *                       (see org.w3c.css.util.Messages.properties).
     * @param message        An add-on message.
     * @see                  org.w3c.css.util.Warning
     */
    public void addWarning(String warningMessage, String message) {
	warnings.addWarning(new Warning(getSourceFile(), getLine(),
					warningMessage, 0, message, "", ac));
    }

    /**
     * Get all warnings.
     */
    public Warnings getWarnings() {
	return warnings;
    }

    /**
     * Get the name of the source file.
     */
    public String getSourceFile() {
	return sourceFile;
    }

    /**
     * Get the begin line.
     */
    public int getBeginLine() {
	return line;
    }

    /**
     * Get the current line.
     */
    public int getLine() {
	return line; //+ cssFouffa.token.beginLine;
    }

    /**
     * Merge two frames.
     *
     * @param frame The other frame for merging.
     */
    public void join(Frame frame) {
	errors.addErrors(frame.errors);
	warnings.addWarnings(frame.warnings);
    }
}

