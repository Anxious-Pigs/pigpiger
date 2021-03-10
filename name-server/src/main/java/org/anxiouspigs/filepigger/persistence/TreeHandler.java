package org.anxiouspigs.filepigger.persistence;

public interface TreeHandler {
    void addFileNode(FileNode fileNode);
    void removeFileNode();
    FileNode getFileNode(String path);
}
