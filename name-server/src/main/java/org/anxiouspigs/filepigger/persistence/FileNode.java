package org.anxiouspigs.filepigger.persistence;

public class FileNode extends TreeNode{
    private String dir;

    public FileNode(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }
}
