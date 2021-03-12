package org.anxiouspigs.filepigger.tree;

public interface TreeHandler {
    void createFileOrDirNode(TreeNode treeNode);
    void appendFileNode(FileNode fileNode);
    void removeFileNode();
    FileNode getFileOrDirNode(String path);
}
