package org.anxiouspigs.filepigger.tree;

import org.anxiouspigs.filepigger.errors.FilepiggerException;
import org.anxiouspigs.filepigger.errors.NodeAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

/**
 * DirTree for NameNode in memory.
 */
public class DirTree implements TreeHandler {

    private TreeNode rootDirNode;

    /**
     * Result type of dichotomy.
     */
    private enum DichotomyResultEnum {
        FIND_HASH_NAME,
        FIND_HASH,
        FIND_NOTHING
    }

    /**
     * Create a instance.
     */
    public DirTree() {
        this.rootDirNode = new DirNode("/", "/");
    }

    /**
     * @param treeNode {@link FileNode} or {@link DirNode}.
     */
    @Override
    public void createFileOrDirNode(TreeNode treeNode) throws FilepiggerException {
        String path = treeNode.getDir();
        String[] paths = path.split("/");
        TreeNode presentNode = rootDirNode;
        Boolean isFile = treeNode instanceof FileNode ? true : false;
        boolean isLast = false;
        StringBuilder dirBuilder = new StringBuilder();
        for (int i = 1; i < paths.length; i++) {
            dirBuilder.append("/").append(paths[i]);
            if (i == paths.length - 1) {
                isLast = true;
            }
            String nodeName = paths[i];
            List<TreeNode> childNodes = ((DirNode) presentNode).getChildNodeList();
            if (childNodes == null) {
                childNodes = new ArrayList<>();
                ((DirNode) presentNode).setChildNodeList(childNodes);
                if (isLast) {
                    childNodes.add(treeNode);
                    break;
                }
                DirNode dirNode = new DirNode(nodeName, dirBuilder.toString());
                childNodes.add(dirNode);
                presentNode = dirNode;
            } else {
                DichotomyResult dichotomyResult =
                        dichotomy(isLast & isFile, nodeName, nodeName.hashCode(), childNodes, 0, childNodes.size() - 1);
                if (dichotomyResult.getResult() == DichotomyResultEnum.FIND_HASH_NAME) {
                    if (isLast) {
                        throw new NodeAlreadyExistsException("the node to be created already exists.");
                    }
                    presentNode = dichotomyResult.getTreeNode();
                } else if (dichotomyResult.getResult() == DichotomyResultEnum.FIND_HASH) {
                    if (isLast) {
                        dichotomyResult.getTreeNode().setNextNode(treeNode);
                        break;
                    }
                    presentNode = new DirNode(nodeName, dirBuilder.toString());
                    dichotomyResult.getTreeNode().setNextNode(presentNode);
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
    public FileNode getFileOrDirNode(String path) {
        return null;
    }

    /**
     *
     */
    @Override
    public void removeFileNode() {

    }

    /**
     * Hash dichotomization to find node.
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
     * Result of  dichotomy.
     */
    private class DichotomyResult {
        private Enum<DichotomyResultEnum> result;
        private TreeNode treeNode;
        private int index;

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
