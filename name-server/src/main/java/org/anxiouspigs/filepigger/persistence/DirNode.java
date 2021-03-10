package org.anxiouspigs.filepigger.persistence;

import java.util.List;

public class DirNode extends TreeNode{

    protected List<TreeNode> childNodeList;

    public DirNode(String dirName) {
        nodeName = dirName;
    }


}
