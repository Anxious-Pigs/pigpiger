package org.anxiouspigs.filepigger.persistence;

public class FileNode extends TreeNode{
    private String dir;

    public FileNode(String dir) {
        this.dir = dir;
        this.nodeName = dir.substring(dir.lastIndexOf("/") + 1);
        this.hash = this.nodeName.hashCode();
    }

    public String getDir() {
        return dir;
    }
}
