package org.anxiouspigs.filepigger.persistence;

public class DirTree implements TreeHandler {

    private TreeNode treeNode;

    public DirTree() {
        this.treeNode = new DirNode("/");
    }

    @Override
    public void addFileNode(FileNode fileNode) {
        String path = fileNode.getDir();
        String[] paths = path.split("/");
        for (int i = 1; i < paths.length; i++) {
            System.out.println(paths[i]);
        }
    }

    @Override
    public FileNode getFileNode(String path) {
        return null;
    }

    @Override
    public void removeFileNode() {

    }
}
