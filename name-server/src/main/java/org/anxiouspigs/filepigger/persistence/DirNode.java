package org.anxiouspigs.filepigger.persistence;

import java.util.List;

public class DirNode extends TreeNode{

    protected List<TreeNode> childNodeList;

    public DirNode(String nodeName, int hash) {
        this.nodeName = nodeName;
        this.hash = hash;
    }

    public List<TreeNode> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<TreeNode> childNodeList) {
        this.childNodeList = childNodeList;
    }
}
