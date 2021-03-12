package org.anxiouspigs.filepigger.tree;

import java.util.List;

public class DirNode extends TreeNode{

    protected List<TreeNode> childNodeList;

    public DirNode(String nodeName, String dir) {
        super(nodeName, dir);
    }

    public DirNode(String dir) {
        super(dir.substring(dir.lastIndexOf("/") + 1), dir);
    }

    public List<TreeNode> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<TreeNode> childNodeList) {
        this.childNodeList = childNodeList;
    }
}
