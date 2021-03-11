package org.anxiouspigs.filepigger;

import org.anxiouspigs.filepigger.persistence.DirNode;
import org.anxiouspigs.filepigger.persistence.FileNode;
import org.anxiouspigs.filepigger.persistence.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String dir = "/image/red/red.jpg";
        System.out.println(dir.substring(dir.lastIndexOf("/") + 1));
    }
}
