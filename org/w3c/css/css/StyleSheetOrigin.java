//
// $Id: StyleSheetOrigin.java,v 1.1 2002-03-13 19:55:01 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: StyleSheetOrigin.java,v $
 * Revision 1.1  2002-03-13 19:55:01  plehegar
 * New
 *
 */
package org.w3c.css.css;

/**
 * @version $Revision: 1.1 $
 * @author  Philippe Le H�garet
 */
public interface StyleSheetOrigin {

    /**
     * This property comes from the UA's default values.
     */  
    public static final int BROWSER = 1;
    
    /**
     * This property comes from the reader's style sheet.
     */  
    public static final int READER = 2;
    
    /**
     * This property comes from the author's style sheet.
     */  
    public static final int AUTHOR = 3;
    
}
