package org.anxiouspigs.filepigger.persistence;

import junit.framework.TestCase;
import org.junit.BeforeClass;

public class DirTreeTest extends TestCase {

    private DirTree dirTree;

    @BeforeClass
    public void setUp() {
        dirTree = new DirTree();
    }

    public void testAddAndGetFileNode() {
        dirTree.createFileNode(new FileNode("/image/red/red.jpg"));
        dirTree.createFileNode(new FileNode("/image/red/red.jpg"));
    }

    public void testRemoveFileNode() {
        dirTree.removeFileNode();
    }
}