package org.anxiouspigs.filepigger.persistence;

public interface TreeHandler {
    void createFileNode(FileNode fileNode);
    void appendFileNode(FileNode fileNode);
    void removeFileNode();
    FileNode getFileNode(String path);
}
