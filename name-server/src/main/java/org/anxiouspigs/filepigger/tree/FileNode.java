package org.anxiouspigs.filepigger.tree;

/**
 * FileNode
 */
public class FileNode extends TreeNode{

    public FileNode(String dir) {
        super(dir.substring(dir.lastIndexOf("/") + 1), dir);
    }


}
