//
// $Id: SortAlgorithm.java,v 1.1 2002-03-13 19:56:54 plehegar Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 * $Log: SortAlgorithm.java,v $
 * Revision 1.1  2002-03-13 19:56:54  plehegar
 * New
 *
 */
package org.w3c.css.util;

/**
 * This class is only to sort an array with an abstract algorithm.
 *
 * @version $Revision: 1.1 $
 * @author  Philippe Le H�garet
 */
public abstract class SortAlgorithm {

    /**
     * The sort function.
     *
     * @param objs the array with all objects
     * @param start the start offset in the array
     * @param end the end offset in the array
     * @param comp The comparaison function between objects
     */    
    public abstract void sort(Object[] objs,
			      int start, int end, 
			      CompareFunction comp);
}
