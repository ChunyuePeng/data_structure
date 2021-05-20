package com.pcy.binarytree.avl;


/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/4/28 18:59
 */
public class BalanceTree {


    /**
     * 获取该节点的高度
     *
     * @param node
     * @return
     */
    private static int getDepth(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * @param node
     * @param value
     * @return 根节点
     */
    public static Node insert(Node node, int value) {
        if (node == null) {
            return newNode(value);
        }
        if (value < node.value)
        //往左子树添加
        {
            node.left = insert(node.left, value);
        } else if (value > node.value)
        //往右子树添加
        {
            node.right = insert(node.right, value);
        } else
        //该节点已存在，直接返回
        {
            return node;
        }

        //更新该node的高度
        node.height = 1 + Math.max(getDepth(node.left), getDepth(node.right));

        int balance = getBalance(node);


        if (balance > 1 && value < node.left.value)
        //LL
        {
            //直接右旋就能再次达到平衡
            return rightRotate(node);
        }
        if (balance < -1 && value > node.right.value)
        //RR
        {
            //直接左旋就能再次达到平衡
            return leftRotate(node);
        }
        if (balance > 1 && value > node.left.value)
        //LR
        {
            //先对失衡结点的左子树左旋，再对失衡结点右旋
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && value < node.right.value)
        //RL
        {
            //先对失衡结点的右子树左旋，再对失衡结点左旋
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        //该节点是平衡状态不需要调整
        return node;
    }

    public static Node deleteNode(Node root, int value) {
        if (root == null) {
            return root;
        }
        if (value < root.value)
        //在左子树上查找
        {
            root.left = deleteNode(root.left, value);
        } else if (value > root.value)
        //在右子树上查找
        {
            root.right = deleteNode(root.right, value);
        } else {
            if (root.left == null || root.right == null)
            //待删除结点的左子树或右子树为空
            {
                if (root.left == null)
                //无左子树的情形，直接将删除结点的父节点指向删除结点
                //的右子树
                {
                    return root.right;
                } else
                //无右子树的情形，直接将删除节点的父节点指向删除节点的左子树
                {
                    return root.left;
                }
            } else {
                //这里有两种方案可以解决：1.让删除节点左子树的最右侧节点代替当前节点。
                //2.让删除节点右子树的最左侧节点代替当前节点。这里选择第一种方式来实现。
                
            }
        }

    }


    /**
     * 对结点node做右旋，处理之后node的父结点指向T的左节点
     *
     * @param node
     */
    private static Node rightRotate(Node node) {
        //旋转完成之后子树的根节点
        Node childrenTreeRoot = node.left;

        //将左孩子的右子树变为T的左子树
        node.left = childrenTreeRoot.right;

        //将T作为左孩子的右子树
        childrenTreeRoot.right = node;


        //旋转完成更新子树高度
        updateHeight(node, childrenTreeRoot);

        return childrenTreeRoot;


    }

    /**
     * 对结点node做左旋，处理之后node的父结点指向node的右节点
     *
     * @param node
     * @return
     */
    private static Node leftRotate(Node node) {
        //旋转完成之后子树的根节点
        Node childrenTreeRoot = node.right;

        //R的左子树设置为T的右子树
        node.right = childrenTreeRoot.left;

        //将T作为子树的左孩子
        childrenTreeRoot.left = node;


        //旋转完成更新子树高度
        updateHeight(node, childrenTreeRoot);

        return childrenTreeRoot;
    }

    /**
     * 在旋转完成之后，只有失衡结点和新父节点的高度会发生变化，所以只需要更新这两个节点的高度
     *
     * @param node
     * @param childrenTreeRoot
     */
    private static void updateHeight(Node node, Node childrenTreeRoot) {
        node.height = Math.max(getDepth(node.left), getDepth(node.right)) + 1;
        childrenTreeRoot.height = Math.max(getDepth(childrenTreeRoot.left), getDepth(childrenTreeRoot.right)) + 1;
    }

    /**
     * @param node
     * @return 返回左右子树的高度差，当高度差的绝对值大于1时代表该node不平衡
     */
    private static int getBalance(Node node) {
        return getDepth(node.left) - getDepth(node.right);
    }

    private static Node newNode(int value) {
        return new Node(value, 1, null, null);
    }
}
