package com.pcy.binarytree.avl;

import com.pcy.util.PrintTree;

/**
 * @description:
 * @author: 彭椿悦
 * @data: 2021/5/20 19:54
 */
public class Test {
    public static void main(String[] args) {
        int[] a = {2,1, 0, 3, 4, 5, 6, 7, 8, 9};
        Node root = null;
        for (int i = 0; i < a.length; i++) {
            root = BalanceTree.insert(root, a[i]);
            PrintTree.print(root);
            System.out.println("===================================================================");
        }
        System.out.println("end");
    }
}
