package org.anxiouspigs.filepigger.persistence;

public class DirTree implements TreeHandler {

    private TreeNode treeNode;

    public DirTree() {
        this.treeNode = new DirNode("/");
    }

    @Override
    public void add(FileNode fileNode) {
        String path = fileNode.getDir();
        String[] paths = path.split("/");
        System.out.println(paths.length);
    }

    @Override
    public void remove() {

    }
}
