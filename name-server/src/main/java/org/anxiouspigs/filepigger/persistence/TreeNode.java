package org.anxiouspigs.filepigger.persistence;

import java.util.List;

public abstract class TreeNode {

    protected long nodeId;
    protected String nodeName;
    protected List<TreeNode> nodeList;
}
