package org.anxiouspigs.filepigger.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 * DirTree for NameNode in memory
 */
public class DirTree implements TreeHandler {

    private TreeNode rootDirNode;

    /**
     * Result type of dichotomy
     */
    private enum DichotomyResultEnum {
        FIND_HASH_NAME,
        FIND_HASH,
        FIND_NOTHING
    }

    /**
     *
     */
    public DirTree() {
        this.rootDirNode = new DirNode("/", 0);
    }

    /**
     * @param fileNode
     */
    @Override
    public void createFileNode(FileNode fileNode) {
        String path = fileNode.getDir();
        String[] paths = path.split("/");
        TreeNode presentNode = rootDirNode;
        boolean isFile = false;
        for (int i = 1; i < paths.length; i++) {
            if (i == paths.length - 1) {
                isFile = true;
            }
            String nodeName = paths[i];
            int hash = nodeName.hashCode();
            List<TreeNode> childNodes = ((DirNode) presentNode).getChildNodeList();
            if (childNodes == null) {
                childNodes = new ArrayList<>();
                ((DirNode) presentNode).setChildNodeList(childNodes);
                if (isFile) {
                    childNodes.add(fileNode);
                    break;
                }
                DirNode dirNode = new DirNode(nodeName, hash);
                childNodes.add(dirNode);
                presentNode = dirNode;
            } else {
                DichotomyResult dichotomyResult =
                        dichotomy(isFile, nodeName, nodeName.hashCode(), childNodes, 0, childNodes.size() - 1);
                if (dichotomyResult.getResult() == DichotomyResultEnum.FIND_HASH_NAME) {
                    if (isFile) {
                        System.out.println("文件已存在");
                        break;
                    }
                    presentNode = dichotomyResult.getTreeNode();
                } else if (dichotomyResult.getResult() == DichotomyResultEnum.FIND_HASH) {

                } else {

                }
            }

        }
    }

    @Override
    public void appendFileNode(FileNode fileNode) {

    }

    /**
     * @param path
     * @return
     */
    @Override
    public FileNode getFileNode(String path) {
        return null;
    }

    /**
     *
     */
    @Override
    public void removeFileNode() {

    }

    /**
     * Hash dichotomization to find node
     *
     * @param nodeName
     * @param hash
     * @param childNodes
     * @return
     */
    public DichotomyResult dichotomy(boolean isFile, String nodeName, int hash, List<TreeNode> childNodes,
                                     int startIndex, int endIndex) {
        if (startIndex > endIndex)
            return new DichotomyResult(DichotomyResultEnum.FIND_NOTHING, startIndex);
        int mid = (startIndex + endIndex) / 2;
        TreeNode childNode = childNodes.get(mid);
        if (childNode.getHash() < hash)
            return dichotomy(isFile, nodeName, hash, childNodes, mid + 1, endIndex);
        else if (childNode.getHash() > hash)
            return dichotomy(isFile, nodeName, hash, childNodes, startIndex, mid - 1);
        else {
            for (; ; childNode = childNode.getNextNode()) {
                if (childNode.getNodeName().equals(nodeName)) {
                    if (isFile) {
                        if (childNode instanceof FileNode) {
                            return new DichotomyResult(DichotomyResultEnum.FIND_HASH_NAME, childNode);
                        }
                    } else {
                        if (childNode instanceof DirNode) {
                            return new DichotomyResult(DichotomyResultEnum.FIND_HASH_NAME, childNode);
                        }
                    }
                }
                if (childNode.getNextNode() == null) {
                    return new DichotomyResult(DichotomyResultEnum.FIND_HASH, childNode);
                }
            }
        }
    }

    /**
     * Result of  dichotomy
     */
    private class DichotomyResult {
        public Enum<DichotomyResultEnum> result;
        public TreeNode treeNode;
        public int index;

        public DichotomyResult(Enum result, TreeNode treeNode) {
            this.result = result;
            this.treeNode = treeNode;
        }

        public DichotomyResult(Enum result, int index) {
            this.result = result;
            this.index = index;
        }

        public Enum getResult() {
            return result;
        }

        public TreeNode getTreeNode() {
            return treeNode;
        }

        public int getIndex() {
            return index;
        }
    }


}
