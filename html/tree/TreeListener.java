/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: TreeListener.java,v 1.2 2002-04-08 21:22:41 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tree;

public interface TreeListener {
    public void notifySetParent(Tree parent);
    public void notifyAttach(Tree child, int rank);
    public void notifyDetach(Tree child, int rank);
    public void notifyReplace(Tree child, int rank);
    public void notifyModified();

    public void notifyEnter();
    public void notifyExit();

    //    public void notifyOpen(boolean o);
}
