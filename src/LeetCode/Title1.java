package LeetCode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description :Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest
 * path from the root node down to the nearest leaf node.
 * 求二叉树最低的高度，即根节点到最近叶子节点的距离
 * @Author : chenzhitao
 * @Date : Created in 17:33 2018/12/25
 */
public class Title1 {


    public int minimumDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int level = 1, curCount = 1, nextCount = 0;
        while (!nodeQueue.isEmpty()) {
            TreeNode tmpNode = nodeQueue.removeFirst();
            curCount--;
            if (tmpNode.left == null && tmpNode.right == null) {
                return level;
            }

            //孩子入队列
            if (tmpNode.left != null){
                nodeQueue.addLast(tmpNode.left);
                nextCount ++;
            }
            if (tmpNode.right != null){
                nodeQueue.addLast(tmpNode.right);
                nextCount ++;
            }

            if (curCount == 0){
                level ++;
                curCount = nextCount;
                nextCount =0;
            }

        }

        return level;
    }


    class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }


}
