package com.pcy.binarytree.avl;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/4/28 19:04
 */
public class Node {
    public int value;
    /**
     * 高度
     */
    public int height;
    /**
     * 左子树
     */
    public Node left;
    /**
     * 右子树
     */
    public Node right;


    public Node(int value, int height, Node left, Node right) {
        this.value = value;
        this.height = height;
        this.left = left;
        this.right = right;
    }


    @Override
    public String toString() {
        return "[" + value + "]";
    }
}
