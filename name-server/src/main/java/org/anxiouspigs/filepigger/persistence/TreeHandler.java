package org.anxiouspigs.filepigger.persistence;

public interface TreeHandler {
    void add(FileNode fileNode);
    void remove();
}
