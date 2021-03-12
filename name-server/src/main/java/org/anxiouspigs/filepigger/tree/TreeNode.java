package org.anxiouspigs.filepigger.tree;

public abstract class TreeNode {

    private long nodeId;
    private String nodeName;
    private int hash;
    private String dir;
    private TreeNode nextNode;

    public TreeNode(String nodeName, String dir) {
        this.nodeName = nodeName;
        this.hash = nodeName.hashCode();
        this.dir = dir;
    }

    public int getHash() {
        return hash;
    }

    public String getNodeName() {
        return nodeName;
    }

    public TreeNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(TreeNode nextNode) {
        this.nextNode = nextNode;
    }

    public String getDir() {
        return dir;
    }
}
