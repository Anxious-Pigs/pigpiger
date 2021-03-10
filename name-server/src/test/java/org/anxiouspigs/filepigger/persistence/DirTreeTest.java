package org.anxiouspigs.filepigger.persistence;

import junit.framework.TestCase;
import org.junit.BeforeClass;

public class DirTreeTest extends TestCase {

    private DirTree dirTree;

    @BeforeClass
    public void setUp() {
        dirTree = new DirTree();
    }

    public void testAdd() {
        dirTree.add(new FileNode("/image/red"));
    }

    public void testRemove() {
        dirTree.remove();
    }
}