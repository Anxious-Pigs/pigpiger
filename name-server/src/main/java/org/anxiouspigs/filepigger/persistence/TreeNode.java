package org.anxiouspigs.filepigger.persistence;

import java.util.List;

public abstract class TreeNode {

    protected long nodeId;
    protected int hash;
    protected String nodeName;
    protected TreeNode nextNode;

    public int getHash() {
        return hash;
    }

    public String getNodeName() {
        return nodeName;
    }

    public TreeNode getNextNode() {
        return nextNode;
    }
}
