package suanfadaolun;

/**
 * @Description : 二叉搜索树
 * @Author : chenzhitao
 * @Date : Created in 15:09 2018/11/26
 */
public class BinarySearchTree {


    /**
     * 查询，使用的是递归方法实现
     *
     * @param root
     * @param key
     * @return
     */
    public boolean treeSearch(TreeNode root, String key) {
        if (root == null) {
            return false;
        }
        if (key == null || key == "") {
            throw new NullPointerException("key is null");
        }

        TreeNode x = root;
        if (x.key.equals(key)) {
            return true;
        } else if (x.key.compareTo(key) > 0) {
            //左孩子
            treeSearch(x.left, key);
        } else {
            //右孩子
            treeSearch(x.right, key);
        }
        return false;
    }


    /**
     * 查询，使用迭代方法实现
     *
     * @param root
     * @param key
     * @return
     */
    public boolean treeSearchIterate(TreeNode root, String key) {
        if (root == null) {
            return false;
        }
        if (key == null || key == "") {
            throw new NullPointerException("key is null");
        }

        //遍历
        TreeNode x = root;
        while (x != null) {
            if (x.key.equals(key)) {
                return true;
            } else if (x.key.compareTo(key) > 0) {
                //左孩子
                x = x.left;
            } else {
                //右孩子
                x = x.right;
            }
        }
        return false;
    }

    /**
     * 获得BST的最小节点。一直遍历其左孩子，直到其左孩子为null，返回
     *
     * @param root
     * @return
     */
    public TreeNode minimum(TreeNode root) {
        return nearestLeftNode(root);

    }

    /**
     * 求当前节的最左孩子节点
     *
     * @param currentNode
     * @return
     */
    private TreeNode nearestLeftNode(TreeNode currentNode) {
        if (currentNode == null) {
            return null;
        }
        //遍历左孩子
        TreeNode x = currentNode;
        while (x.left != null) {
            x = x.left;
        }
        return x;

    }

    /**
     * 获得BST的最大节点。一直遍历其右孩子，直到其右孩子为null，返回
     *
     * @param root
     * @return
     */
    public TreeNode maximum(TreeNode root) {
        return nearestRightNode(root);
    }

    /**
     * 求当前节的最右孩子节点
     *
     * @param currentNode
     * @return
     */
    private TreeNode nearestRightNode(TreeNode currentNode) {
        if (currentNode == null) {
            return null;
        }
        TreeNode x = currentNode;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    /**
     * 获得BST的x节点的后继节点。
     * 情况：
     * 1、root为空，直接返回空指针异常
     * 2、x节点存在右孩子，直接查找其右孩子的最左孩子节点
     * <p>
     * 3、x节点无右孩子。找到其父节点。
     * 3.1、如果x节点是其父节点的左孩子，直接返回其父节点；
     * 3.2、如果是父节点的右孩子，直接向上遍历其祖父节点，直到为左孩子节点，返回其祖父节点。
     * 如果祖父节点为空（root），返回null;
     *
     * @param root
     * @param x
     * @return
     */
    public TreeNode successor(TreeNode root, TreeNode x) {
        if (root == null) {
            throw new NullPointerException("param.root is null");
        }
        if (x == null) {
            throw new NullPointerException("param.x is null");
        }
        // 存在右孩子，直接返回其右孩子的最左节点
        if (x.right != null) {
            return nearestLeftNode(x.right);
        }

        //无右孩子，查找其祖父节
        TreeNode p = x.parent;
        TreeNode q = x;
        while (p != null && q == p.right) {
            //如果 父节点不空，且当前节点是其父节点的右孩子，继续迭代
            q = p;
            p = p.parent;
        }
        return p;
    }

    /**
     * 获得BST的x节点的前接节点。
     *
     * @param root
     * @param x
     * @return
     */
    public TreeNode predecessor(TreeNode root, TreeNode x) {
        if (root == null) {
            throw new NullPointerException("param.root is null");
        }
        if (x == null) {
            throw new NullPointerException("param.x is null");
        }
        // 存在左孩子，直接返回其最左孩子的最右节点
        if (x.left != null) {
            return nearestRightNode(x.left);
        }

        //无左孩子，查找其祖父节
        TreeNode p = x.parent;
        TreeNode q = x;
        while (p != null && q == p.left) {
            //如果 父节点不空，且当前节点是其父节点的左孩子，继续迭代
            q = p;
            p = p.parent;
        }
        return p;
    }

    /**
     * 插入元素
     *
     * @param root
     * @param node
     */
    public void insert(TreeNode root, TreeNode node) {
        if (node == null) {
            throw new NullPointerException("param.node is null");
        }

        TreeNode x = root;
        //记录前一个节点
        TreeNode y = null;
        // 查找到合适的叶子节点
        while (x != null) {
            y = x;
            if (x.key.compareTo(node.key) > 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        if (y == null) {
            root = node;
        } else {
            if (y.key.compareTo(node.key) > 0) {
                //设置成左孩子
                y.left = node;
            } else {
                //右孩子
                y.right = node;

            }
            node.parent = y;
        }
    }

    /**
     * 删除一个节点，原理是使用删除节点的后续节点顶替
     * <p>
     * 情况：
     * 1、节点无孩子节点，直接删除，设置节点的父节点为null
     * 2、节点有一个孩子节点，无竞争，直接使用孩子节点顶替
     * 3、节点存在两个孩子节点，找出节点的后续节点；
     * 3.1 如果后续节点不是直接右孩子，先执行删除这个后继节点；
     * 后继节点只存在两个情况1和2，情况2是后继节点只能是有一个右孩子；
     * 更新后续节点右孩子为删除节点的右孩子；
     * 3.2 执行后续节点替换删除节点；更新后续节点左孩子为删除节点的左孩子；
     *
     * @param root
     * @param delNode
     */
    public void delete(TreeNode root, TreeNode delNode) {
        if (delNode == null || root == null) {
            return;
        }
        if (delNode.left == null) {
            // 单孩子节点或者无孩子节点情况
            // 左孩子为空, 执行删除，使用右孩子替换
            transplant(root, delNode, delNode.right);
        } else if (delNode.right == null) {
            // 右孩子为空，左孩子不为空
            // 左孩子替换
            transplant(root, delNode, delNode.left);
        } else {
            // 存在左右孩子，找后续节点
            TreeNode y = minimum(delNode.right);
            if (y != delNode.right) {
                // 非删除节点的右孩子, 选择y节点的右孩子替换自身位置
                transplant(root, y, y.right);
                // 更新y的右孩子信息,及右孩子的父节点信息
                y.right = delNode.right;
                delNode.right.parent = y;
            }
            transplant(root, delNode, y);
            // 更新y的左孩子信息,及左孩子的父节点信息
            y.left = delNode.left;
            delNode.left.parent = y;
        }


    }

    /**
     * 执行使用var1节点替换var0节点
     *
     * @param root
     * @param var0 不能为空
     * @param var1 可以为空
     */
    private void transplant(TreeNode root, TreeNode var0, TreeNode var1) {
        if (root == null || var0 == null) {
            return;
        }

        //替换根节点
        if (var0 == root) {
            root = var1;
        } else if (var0 == var0.parent.left) {
            //父节点信息更新
            var0.parent.left = var1;
        } else {
            var0.parent.right = var1;
        }
        //如果不为null 替换var1记录的父节点信息
        if (var1 != null) {
            var1.parent = var0.parent;
        }

    }


    /**
     * 节点类
     *
     * @param <T>
     */
    private class TreeNode<T> {
        private String key;
        private T value;
        private TreeNode parent;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {
        }

        public TreeNode(String key, T value) {
            this.key = key;
            this.value = value;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }
}
