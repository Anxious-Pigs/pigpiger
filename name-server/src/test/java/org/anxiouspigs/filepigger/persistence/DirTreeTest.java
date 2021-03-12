package org.anxiouspigs.filepigger.persistence;

import junit.framework.TestCase;
import org.anxiouspigs.filepigger.tree.DirNode;
import org.anxiouspigs.filepigger.tree.DirTree;
import org.anxiouspigs.filepigger.tree.FileNode;
import org.junit.BeforeClass;

public class DirTreeTest extends TestCase {

    private DirTree dirTree;

    @BeforeClass
    public void setUp() {
        dirTree = new DirTree();
    }

    public void testAddAndGetFileNode() {
        dirTree.createFileOrDirNode(new FileNode("/image/red/red.jpg"));
        dirTree.createFileOrDirNode(new DirNode("/image/red/red.jpg"));
    }

    public void testRemoveFileNode() {
        dirTree.removeFileNode();
    }
}