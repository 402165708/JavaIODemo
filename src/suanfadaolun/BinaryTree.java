package suanfadaolun;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的遍历
 *
 * Created by chenzhitao on 2018/9/7
 */
public class BinaryTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        root.setLeftChild(node1);
        root.setRightChild(node2);

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.setLeftChild(node3);
        node1.setRightChild(node4);

        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        node2.setLeftChild(node5);
        node3.setRightChild(node6);
        node3.setLeftChild(node7);
        node5.setRightChild(node8);

//        TreeNode root = new TreeNode(0);
//        TreeNode tmpNode = root;
//        for (int i = 1; i < Short.MAX_VALUE - 8; i++) {
//            TreeNode node = new TreeNode(i);
//
//            tmpNode.setLeftChild(node);
//            tmpNode = node;
//        }

        System.out.println("preOrderList:=========================");
        List<Integer> preOrderList = new LinkedList<>();
        preOrderTraversal1(root, preOrderList);
        for (Integer value : preOrderList) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("inOrderList:=========================");
        List<Integer> inOrderList = new LinkedList<>();
        inOrderTravalsal1(root, inOrderList);
        for (Integer value : inOrderList) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("postOrderList:=========================");
        List<Integer> postOrderList = new LinkedList<>();
        postOrderTraversal1(root, postOrderList);
        for (Integer value : postOrderList) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("levelOrderList:=========================");
        List<Integer> levelOrderList = new LinkedList<>();
        levelOrderTraversal(root, levelOrderList);
        for (Integer value : levelOrderList) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("mirror before:=========================");
        printNodeByLevel(root);
        mirrorByPreOrder(root);
        System.out.println("mirror after:=========================");
        printNodeByLevel(root);
        System.out.println();

        System.out.println("mirror Copy before:=========================");
        printNodeByLevel(root);
        TreeNode newRoot = mirrorCopyByPreOrder(root);
        System.out.println("mirror Copy after:=========================");
        printNodeByLevel(newRoot);
        System.out.println();

        System.out.println("mirror Copy by stack before:=========================");
        printNodeByLevel(root);
        TreeNode newRoot1 = mirrorCopyByStack(root);
        System.out.println("mirror Copy by stack after:=========================");
        printNodeByLevel(newRoot1);
        System.out.println();


        System.out.println("tree depth:=========================");
        System.out.println(getDepthRecByLevel(root));

        System.out.println("tree k level:=========================");
        System.out.println(getNodeNumKthLevelRecByLevel(root, 5));

    }

    /**
     * 层次遍历。实现原理，使用队列实现
     *
     * @param root
     */
    public static void levelOrderTraversal(TreeNode root, List<Integer> resultList) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // 入队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode pNode = queue.poll();
            //出队列
            resultList.add(pNode.value);

            //孩子节点入队列
            if (pNode.leftChild != null) {
                queue.offer(pNode.leftChild);
            }
            if (pNode.rightChild != null) {
                queue.offer(pNode.rightChild);
            }
        }
    }

    /**
     * 先序遍历
     *
     * @param root
     * @param resultList
     */
    public static void preOrderTraversal(TreeNode root, LinkedList<Integer> resultList) {
        if (root != null) {
            resultList.add(root.value);
            preOrderTraversal(root.leftChild, resultList);
            preOrderTraversal(root.rightChild, resultList);
        }
    }


    /**
     * 先序遍历，非递归方法。使用栈实现，根 ->左 -> 右
     * 对于任一结点P：
     * <p>
     * 1)访问结点P，并将结点P入栈,记录值;
     * 2)判断结点P的左孩子是否为空，若不为空，则将P的左孩子置为当前的结点P;
     * 若为空，则取栈顶结点并进行出栈操作，并将栈顶结点的右孩子置为当前的结点P，循环至1);
     * 3)直到P为NULL并且栈为空，则遍历结束。
     * <p>
     * 入栈前记录元素。
     *
     * @param root
     * @param resultList
     */
    public static void preOrderTraversal1(TreeNode root, List<Integer> resultList) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            //入栈; 记录; 左孩子，直到为空null
            while (pNode != null) {
                resultList.add(pNode.value);
                stack.push(pNode);
                pNode = pNode.leftChild;
            }
            //出栈; 右孩子
            if (!stack.isEmpty()) {
                TreeNode tmpNode = stack.pop();
                pNode = tmpNode.rightChild;
            }
        }
    }

    /**
     * 先序遍历，非递归方法。使用栈实现，根 ->左 -> 右
     * <p>
     * 1)root节点入栈;
     * 2)出栈，记录元素;
     * 3)若右孩子不为空，则入栈操作；若左孩子不为空，则入栈操作，循环至2);
     *
     * @param root
     * @return
     */
    public static void preOrderTraversal2(TreeNode root, List<Integer> resultList) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 出栈，记录
            TreeNode cur = stack.pop();
            resultList.add(cur.value);

            if (cur.rightChild != null) {
                //右孩子入栈
                stack.push(cur.rightChild);
            }
            if (cur.leftChild != null) {
                // 左孩子入栈
                stack.push(cur.leftChild);
            }
        }
    }

    /**
     * 中序遍历，递归方法： 左 -> 根 -> 右
     *
     * @param root
     * @param resultList
     */
    public static void inOrderTraversal(TreeNode root, List<Integer> resultList) {
        if (root != null) {
            inOrderTraversal(root.leftChild, resultList);
            resultList.add(root.value);
            inOrderTraversal(root.rightChild, resultList);
        }
    }

    /**
     * 中序遍历，非递归方法： 左 -> 根 -> 右
     * 1)将结点P入栈;
     * 2)判断结点P的左孩子是否为空，若不为空，则将P的左孩子置为当前的结点P;
     * 若为空，则取栈顶结点并进行出栈操作，并将栈顶结点的右孩子置为当前的结点P，循环至1);
     * 3)直到P为NULL并且栈为空，则遍历结束。
     *
     * @param root
     * @param resultList
     */
    public static void inOrderTravalsal1(TreeNode root, List<Integer> resultList) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            // 左孩子; 直到为null
            while (pNode != null) {
                stack.push(pNode);
                pNode = pNode.leftChild;
            }
            // 出栈; 记录; 右孩子
            if (!stack.isEmpty()) {
                TreeNode tmpNode = stack.pop();
                resultList.add(tmpNode.value);
                pNode = tmpNode.rightChild;
            }
        }

    }

    /**
     * 后序遍历，递归方法。左 -> 右 -> 根
     *
     * @param root
     * @param resultList
     */
    public static void postOrderTravalsal(TreeNode root, List<Integer> resultList) {
        if (root != null) {
            postOrderTravalsal(root.leftChild, resultList);
            postOrderTravalsal(root.rightChild, resultList);
            resultList.add(root.value);
        }
    }

    /**
     * 后序遍历，非递归方法.比较特殊，不能直接按照左 -> 右 -> 根的方式 入栈出栈
     * <p>
     * 对于任一结点P，先将其入栈。如果P不存在左孩子和右孩子，则可以直接访问它；
     * 或者P存在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
     * <p>
     * 若非上述两种情况，则将P的右孩子和左孩子依次入栈，这样就保证了每次取栈顶元素的时候，
     * 左孩子在右孩子前面被访问，左孩子和右孩子都在根结点前面被访问。
     *
     * @param root
     * @param resultList
     */
    public static void postOrderTraversal1(TreeNode root, List<Integer> resultList) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur, pre = null;

        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ((cur.leftChild == null && cur.rightChild == null)
                    || (pre != null) && (cur.leftChild == pre || cur.rightChild == pre)) {
                // 出栈，记录
                TreeNode temp = stack.pop();
                resultList.add(temp.value);
                pre = temp;
            } else {
                //  先右孩子入栈，在左孩子入栈；特殊点
                if (cur.rightChild != null) {
                    stack.push(cur.rightChild);
                }
                if (cur.leftChild != null) {
                    stack.push(cur.leftChild);
                }
            }

        }
    }

    /**
     * 获得树节点个数，递归方法
     */
    public static int getNodeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getNodeCount(node.leftChild) + getNodeCount(node.rightChild) + 1;
    }

    /**
     * 获得树节点个数，非递归方法
     * 使用层次遍历获得
     *
     * @param node
     * @return
     */
    public static int getNodeCountByLevel(TreeNode node) {
        if (node == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        int count = 1;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.leftChild != null) {
                count++;
                queue.add(temp.leftChild);
            }
            if (temp.rightChild != null) {
                count++;
                queue.add(temp.rightChild);
            }
        }
        return count;
    }

    /**
     * 节点数，先序遍历，非递归
     *
     * @param node
     * @return
     */
    public static int getNodeCountByPreOrder(TreeNode node) {
        if (node == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = node;
        int count = 0;
        while (pNode != null || !stack.isEmpty()) {
            while (pNode != null) {
                stack.push(pNode);
                count++;
                pNode = pNode.leftChild;
            }
            if (!stack.isEmpty()) {
                pNode = stack.pop();
                pNode = pNode.rightChild;
            }
        }
        return count;
    }

    /**
     * 节点数，中序遍历，非递归
     *
     * @param node
     * @return
     */
    public static int getNodeCountByInOrder(TreeNode node) {
        if (node == null) {
            return 0;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = node;
        int count = 0;
        while (pNode != null || !stack.isEmpty()) {
            //
            while (pNode != null) {
                stack.push(pNode);
                pNode = pNode.leftChild;
            }
            if (!stack.isEmpty()) {
                pNode = stack.pop();
                count++;
                pNode = pNode.rightChild;
            }
        }
        return count;
    }

    /**
     * 节点数  后续遍历。非递归
     *
     * @param node
     * @return
     */
    public static int getNodeCountByPostOrder(TreeNode node) {
        if (node == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        TreeNode cur, pre = null;
        int count = 0;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ((cur.rightChild == null && cur.leftChild == null)
                    || ((pre != null) && (cur.leftChild == pre || cur.rightChild == pre))) {
                TreeNode temp = stack.pop();
                count++;
                pre = temp;
            } else {
                if (cur.rightChild != null) {
                    stack.push(cur.rightChild);
                }
                if (cur.leftChild != null) {
                    stack.push(cur.leftChild);
                }
            }

        }
        return count;
    }

    /**
     * 获得树的深度，递归
     *
     * @param root
     * @return
     */
    public static int getDepthRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepthRec(root.leftChild), getDepthRec(root.rightChild)) + 1;
    }

    /**
     * 获得树的高度，层次遍历
     *
     * @param root
     * @return
     */
    public static int getDepthRecByLevel(TreeNode root) {
        if (null == root) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curLevelCount = 1, nextLevelCount = 0, depth = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            --curLevelCount;
            if (null != node.leftChild) {
                queue.offer(node.leftChild);
                ++nextLevelCount;
            }
            if (null != node.rightChild) {
                queue.offer(node.rightChild);
                ++nextLevelCount;
            }
            //判断当前层是否已经遍历完
            if (0 == curLevelCount) {
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
                ++depth;
            }
        }
        return depth;
    }

    /**
     * 打印节点，层次遍历打印
     *
     * @param root
     */
    public static void printNodeByLevel(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curLevelCount = 1, nextLevelCount = 0;
        // 记录实际的元素个数
        int elementCount = 1;
        while (elementCount != 0) {
            TreeNode node = queue.poll();
            curLevelCount--;
            if (node == null) {
                System.out.print("NIL" + " ");
            } else {
                elementCount--;
                System.out.print(node.value + " ");
            }

            if (node != null) {
                if (node.leftChild != null) {
                    elementCount++;
                }
                queue.offer(node.leftChild);

                if (node.rightChild != null) {
                    elementCount++;
                }
                queue.offer(node.rightChild);
            } else {
                queue.offer(null);
                queue.offer(null);
            }
            nextLevelCount += 2;

            //判断当前层是否已经遍历完
            if (0 == curLevelCount) {
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
                System.out.println("");
            }
        }
        System.out.println();
    }

    /**
     * 求二叉树第k层的节点个数
     * 递归
     * <p>
     * 求以root为根的k层节点数目，
     * 等价于求以root左孩子为根的k-1层（因为少了root）节点数目 加上以root右孩子为根的k-1层（因为 少了root）节点数目。即：
     * 如果二叉树为空或者k<1，返回0
     * 如果二叉树不为空并且k==1，返回1
     * 如果二叉树不为空且k>1，返回root左子树中k-1层的节点个数与root右子树k-1层节点个数之和
     *
     * @param root 根节点
     * @param k    第k个节点
     * @return 第k层节点数
     */
    public static int getNodeNumKthLevelRec(TreeNode root, int k) {
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return getNodeNumKthLevelRec(root.leftChild, k - 1) + getNodeNumKthLevelRec(root.rightChild, k - 1);
    }

    /**
     * 求二叉树第k层的节点个数,层次遍历
     *
     * @param root
     * @param k
     * @return
     */
    public static int getNodeNumKthLevelRecByLevel(TreeNode root, final int k) {
        if (root == null) {
            return 0;
        }
        if (k < 1) {
            return 0;
        } else if (k == 1) {
            return 1;
        } else {
            // 层次遍历，只需要遍历完k-1层
            int level = k - 1;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            // 记录当前层的节点数和下层数
            int curCount = 1, nextCount = 0;
            while (level > 0 && !queue.isEmpty()) {
                TreeNode node = queue.poll();
                curCount--;
                if (node.leftChild != null) {
                    queue.offer(node.leftChild);
                    nextCount++;
                }
                if (node.rightChild != null) {
                    queue.offer(node.rightChild);
                    nextCount++;
                }
                if (curCount == 0) {
                    curCount = nextCount;
                    nextCount = 0;
                    level--;
                }
            }
            if (level > 0) {
                //树的高度小于k
                return 0;
            } else {
                return curCount;
            }
        }
    }

    /**
     * 求二叉树中叶子节点的个数
     * 递归
     * <p>
     * 如果二叉树为空，返回0
     * 如果二叉树是叶子节点，返回1
     * 如果二叉树不是叶子节点，二叉树的叶子节点数 = 左子树叶子节点数 + 右子树叶子节点数
     *
     * @param root 根节点
     * @return 叶子节点个数
     */
    public static int getNodeNumLeafRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.leftChild == null && root.rightChild == null) {
            return 1;
        }
        return getNodeNumLeafRec(root.leftChild) + getNodeNumLeafRec(root.rightChild);
    }

    /**
     * 求二叉树中叶子节点的个数
     *
     * @param root
     * @return
     */
    public static int getNodeNumLeafRecByLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int leafCount = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.leftChild == null && node.rightChild == null) {
                //左右孩子为空，为叶子节点
                leafCount++;
            }
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
        return leafCount;
    }


    public static int getNodeNumLeafRecByPreOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        int leafCount = 0;

        while (pNode != null || !stack.isEmpty()) {
            //入栈; 记录; 左孩子，直到为空null
            while (pNode != null) {
                stack.push(pNode);
                if (pNode.leftChild == null && pNode.rightChild == null) {
                    leafCount++;
                }
                pNode = pNode.leftChild;
            }
            //出栈; 右孩子
            if (!stack.isEmpty()) {
                TreeNode tmpNode = stack.pop();
                pNode = tmpNode.rightChild;
            }
        }
        return leafCount;
    }

    /**
     * 5. 判断两棵二叉树是否相同的树。
     * 递归
     * 如果两棵二叉树都为空，返回真
     * 如果两棵二叉树一棵为空，另外一棵不为空，返回假
     * 如果两棵二叉树都不为空，如果对应的左子树和右子树都同构返回真，其他返回假
     *
     * @param r1 二叉树1
     * @param r2 二叉树2
     * @return 是否相同
     */
    public static boolean isSameRec(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            // 都是空
            return true;
        } else if (r1 == null || r2 == null) {
            // 有一个为空，一个不为空
            return false;
        }
        if (r1.value != r2.value) {
            // 两个不为空，但是值不相同
            return false;
        }
        // 递归遍历左右子节点
        return isSameRec(r1.leftChild, r2.leftChild) && isSameRec(r1.rightChild, r2.rightChild);
    }

    /**
     * 判断两个二叉树是否相同
     *
     * @param r1
     * @param r2
     * @return
     */
    public static boolean isSameRecByStack(TreeNode r1, TreeNode r2) {

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(r1);
        stack2.push(r2);
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode treeNode1 = stack1.pop();
            TreeNode treeNode2 = stack1.pop();
            if ((treeNode1 == null && treeNode2 == null)) {
                // 节点都为空
                continue;
            } else if (treeNode1 != null && treeNode2 != null && treeNode1.value == treeNode2.value) {
                // 节点不为空，且节点值相同，把左右孩子节点存入栈中
                stack1.push(treeNode1.leftChild);
                stack1.push(treeNode1.rightChild);
                stack2.push(treeNode2.leftChild);
                stack2.push(treeNode2.rightChild);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 6. 判断二叉树是不是平衡二叉树
     * 递归
     *
     * @param root 根节点
     * @return 是否二叉平衡树（AVL树）
     */
    public static boolean isAVLTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(getDepthRecByLevel(root.leftChild) - getDepthRecByLevel(root.rightChild)) > 1) {
            // 左右子树高度差大于1
            return false;
        }
        // 递归判断左右子树
        return isAVLTree(root.leftChild) && isAVLTree(root.rightChild);
    }

    /**
     * 7. 求二叉树的镜像。破坏原来的树，把原来的树改成其镜像。
     * 递归
     *
     * @param root 根节点
     * @return 镜像二叉树的根节点
     */
    public static TreeNode mirrorRec(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 递归镜像左右子树,
        TreeNode left = mirrorRec(root.rightChild);
        TreeNode right = mirrorRec(root.leftChild);
        // 更新根节点的左右子树为镜像后的树
        root.leftChild = left;
        root.rightChild = right;
        return root;
    }

    /**
     * 求二叉树的镜像。破坏原来的树，把原来的树改成其镜像。使用栈方式，先序遍历
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorByPreOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            while (pNode != null) {
                stack.push(pNode);
                //交换左右孩子指针
                TreeNode temp = pNode.leftChild;
                pNode.leftChild = pNode.rightChild;
                pNode.rightChild = temp;

                //更新p节点, 左孩子
                pNode = pNode.leftChild;
            }
            // 右孩子
            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                pNode = node.rightChild;
            }
        }
        return root;
    }

    /**
     * 求二叉树的镜像。不破坏原来的树，把原来的树改成其镜像。
     * 递归
     *
     * @param root 根节点
     * @return 镜像二叉树的根节点
     */
    public static TreeNode mirrorCopy(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode newNode = new TreeNode(root.value);
        // 递归镜像左右子树,并更新根节点的左右子树为镜像后的树
        newNode.leftChild = mirrorCopy(root.rightChild);
        newNode.rightChild = mirrorCopy(root.leftChild);
        return newNode;
    }

    /**
     * 求二叉树的镜像。使用栈方式，先序遍历。不破坏原来的树，把原来的树改成其镜像
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorCopyByPreOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode newRoot = null;
        boolean isFirst = true;
        TreeNode pNode = root;
        TreeNode pNewNode;
        while (pNode != null || !stack.isEmpty()) {
            while (pNode != null) {
                //新建节点，交换左右孩子指针
                TreeNode newNode = new TreeNode(pNode.value);
                if (isFirst) {
                    // 第一个节点设置成root
                    newRoot = newNode;
                    isFirst = false;
                }
                newNode.rightChild = pNode.leftChild;
                newNode.leftChild = pNode.rightChild;
                //入栈
                stack.push(newNode);
                //更新p节点, 左孩子
                pNode = newNode.leftChild;
            }
            // 右孩子
            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                pNode = node.rightChild;
            }
        }
        return newRoot;
    }

    /**
     * 求二叉树的镜像。使用栈方式，先序遍历。不破坏原来的树，把原来的树改成其镜像
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorCopyByStack(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode> newStack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode newRoot = new TreeNode(root.value);
        newStack.push(newRoot);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            TreeNode newCur = newStack.pop();

            if (cur.rightChild != null) {
                stack.push(cur.rightChild);
                newCur.leftChild = new TreeNode(cur.rightChild.value);
                newStack.push(newCur.leftChild);
            }
            if (cur.leftChild != null) {
                stack.push(cur.leftChild);
                newCur.rightChild = new TreeNode(cur.leftChild.value);
                newStack.push(newCur.rightChild);
            }
        }
        return newRoot;
    }


    /**
     * 求二叉数的节点 的最大值和最小值
     * 使用层次遍历
     *
     * @param bTree
     */
    public void levelTraverse(TreeNode bTree, int max, int min) {
        max = bTree.value;
        min = bTree.value;
        if (bTree == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(bTree);
        while (!queue.isEmpty()) {
            TreeNode pNode = queue.poll();
            getMaxAndMinValue(bTree, max, min);

            if (pNode.leftChild != null) {
                queue.offer(pNode.leftChild);
            }
            if (pNode.rightChild != null) {
                queue.offer(pNode.rightChild);
            }
        }
        return;
    }

    /**
     * 获得最大最小值
     *
     * @param pNode
     * @param max
     * @param min
     */
    public void getMaxAndMinValue(final TreeNode pNode, int max, int min) {
        if (pNode != null) {
            if (pNode.value > max) {
                max = pNode.value;
            }
            if (pNode.value < min) {
                min = pNode.value;
            }
        }
    }

    public




    static class TreeNode {
        int value;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode(int value, TreeNode leftChild, TreeNode rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode rightChild) {
            this.rightChild = rightChild;
        }
    }
}
