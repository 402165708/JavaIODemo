package suanfadaolun;

/**
 * @Description : 红黑树也是一个节点加入颜色状态的二叉搜索树
 * <p>
 * 红黑树性质：
 * 1、每个节点是红色或者黑色
 * 2、根节点是黑色的
 * 3、叶子节点是null，是黑色的
 * 4、如果一个节点是红色的，则子节点都是黑色的
 * 5、对于任意节点来说，从节点出发到所有后代叶子节点的简单路径上，包含了相同数量的黑节点
 * @Author : chenzhitao
 * @Date : Created in 20:33 2018/12/6
 */
public class RedBlackTree {


    /**
     * 左旋X节点
     * <p>
     * X		 左旋x节点	   Y
     * /  \     --------->    /  \
     * a   Y    <---------   X    c
     * /   / \   右旋y节点   / \
     * NIL b  c           a   b
     *
     * @param root
     * @param x
     */
    private void leftRotation(RBTreeNode root, final RBTreeNode x) {
        if (root == null || x == null) {
            throw new NullPointerException("param.root or param.x is null!");
        }

        // 设置y节点
        RBTreeNode y = x.right;
        if (y == null) {
            throw new NullPointerException("param.x.right is null!");
        }

        // 把y节点的左孩子树交换到x节点上
        x.right = y.left;
        y.left.parent = x;

        //y节点替换x节点在其父节点的位置
        y.parent = x.parent;
        if (x.parent == null) {
            // 设置y为根节点
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        //设置x为y的左孩子节点
        y.left = x;
        x.parent = y;
    }

    /**
     * 右旋节点
     * <p>
     * X		 左旋x节点	   Y
     * /  \     --------->    /  \
     * a   Y    <---------   X    c
     * /   / \   右旋y节点   / \
     * NIL b  c           a   b
     *
     * @param root
     * @param y
     */
    private void rightRotation(RBTreeNode root, final RBTreeNode y) {
        if (root == null || y == null) {
            throw new NullPointerException("param.root or param.y is null!");
        }

        // 设置x节点
        RBTreeNode x = y.left;
        if (y == null) {
            throw new NullPointerException("param.y.right is null!");
        }

        // 把y节点的右孩子树交换到x节点上
        y.left = x.right;
        x.right.parent = y;

        //x节点替换y节点在其父节点的位置
        x.parent = y.parent;
        if (y.parent == null) {
            // 设置y为根节点
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        //设置y为x的右孩子节点
        x.right = y;
        y.parent = x;
    }

    public void insert(RBTreeNode root, RBTreeNode newNode) {
        if (root == null || newNode == null) {
            throw new NullPointerException("param.root or param.x is null!");
        }
        RBTreeNode x = root;
        RBTreeNode y = null;
        while (x != null) {
            y = x;
            if (x.key.compareTo(newNode.key) > 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        if (y == null) {
            root = newNode;
        } else {
            if (y.key.compareTo(newNode.key) > 0) {
                y.left = newNode;
            } else {
                y.right = newNode;
            }
            newNode.parent = y;
        }

        newNode.left = newNode.right = null;
        newNode.red = true;
        // 调整颜色
        insertFixUp(root, newNode);

    }

    /**
     * 维护红黑树的稳定性，
     *
     * @param root
     * @param newNode
     */
    private void insertFixUp(RBTreeNode root, RBTreeNode newNode) {
        if (root == newNode) {
            root.red = false;
            return;
        }

        RBTreeNode z = newNode;
        while (z.parent.red) {
            if (z.parent == z.parent.parent.left) {
                //父节点是其祖父节点的左孩子
                RBTreeNode y = z.parent.parent.right;
                if (y != null && y.red) {
                    //叔叔节点非空且是红色
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    //z是父节点的右孩子
                    z = z.parent;
                    //左旋父节点
                    leftRotation(root, z);
                }
                //继续
                z.parent.red = false;
                z.parent.parent.red = true;
                // 右旋祖父节点
                rightRotation(root, z.parent.parent);
            } else {
                //父节点是其祖父节点的右孩子
                RBTreeNode y = z.parent.parent.left;
                if (y != null && y.red) {
                    //叔叔节点非空且是红色
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                } else if (z == z.parent.left) {
                    //z是父节点的左孩子
                    z = z.parent;
                    //右旋父节点
                    rightRotation(root, z);
                }
                //继续
                z.parent.red = false;
                z.parent.parent.red = true;
                //左旋祖父节点
                leftRotation(root, z.parent.parent);
            }
        }
    }

    public void delete(RBTreeNode root, RBTreeNode delNode) {
        if (root == null || delNode == null) {
            throw new NullPointerException("param.root or param.delNode is null!");
        }
        //删除根节点
        if (root == delNode) {
            root = null;
            return;
        }

        RBTreeNode x = null;
        RBTreeNode y = delNode;
        boolean yOriginalColor = y.red;
        if (delNode.left == null) {
            x = delNode.right;
            transplant(root, delNode, delNode.right);
        } else if (delNode.right == null) {
            x = delNode.left;
            transplant(root, delNode, delNode.left);
        } else {
            y = minimum(delNode.right);
            yOriginalColor = y.red;
            x = y.right;

            if (y.parent == delNode){
                // 直接右孩子
                x.parent = y;
            }else {
                // y非直接右孩子, 使用y的右孩子替换y的位置
                transplant(root,y,y.right);
                // 更新y的右孩子成原delNode的右孩子
                y.right = delNode.right;
                y.right.parent = y;
            }
            // y替换delNode的位置
            transplant(root,delNode,y);
            y.left = delNode.left;
            y.left.parent = y;
            y.red = delNode.red;
        }
        if (!yOriginalColor){
            // 如果去掉的节点是黑色的
            deleteFixUp(root,x);
        }


    }

    private void deleteFixUp(RBTreeNode root, RBTreeNode x) {


    }

    /**
     * 执行使用var1节点替换var0节点
     *
     * @param root
     * @param var0 不能为空
     * @param var1 可以为空
     */
    private void transplant(RBTreeNode root, RBTreeNode var0, RBTreeNode var1) {
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
        if (var1 != null) {
            var1.parent = var0.parent;
        }
    }

    /**
     * 获得BST的最小节点。一直遍历其左孩子，直到其左孩子为null，返回
     *
     * @param root
     * @return
     */
    public RBTreeNode minimum(RBTreeNode root) {
        return nearestLeftNode(root);

    }


    /**
     * 求当前节的最左孩子节点
     *
     * @param currentNode
     * @return
     */
    private RBTreeNode nearestLeftNode(RBTreeNode currentNode) {
        if (currentNode == null) {
            return null;
        }
        //遍历左孩子
        RBTreeNode x = currentNode;
        while (x.left != null) {
            x = x.left;
        }
        return x;

    }


    /**
     * 节点类
     *
     * @param <T>
     */
    private class RBTreeNode<T> {
        private String key;
        private T value;
        private RBTreeNode parent;
        private RBTreeNode left;
        private RBTreeNode right;
        private boolean red = true;


        public RBTreeNode() {
        }

        public RBTreeNode(String key, T value) {
            this.key = key;
            this.value = value;
        }

        public RBTreeNode getParent() {
            return parent;
        }

        public void setParent(RBTreeNode parent) {
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

        public RBTreeNode getLeft() {
            return left;
        }

        public void setLeft(RBTreeNode left) {
            this.left = left;
        }

        public RBTreeNode getRight() {
            return right;
        }

        public void setRight(RBTreeNode right) {
            this.right = right;
        }
    }
}


