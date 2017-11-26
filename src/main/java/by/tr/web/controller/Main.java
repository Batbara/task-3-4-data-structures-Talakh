package by.tr.web.controller;

import by.tr.web.util.BinarySearchTree;
import by.tr.web.util.Tree;

public class Main {
    public static void main(String... args) {
        Tree<Integer> tree = new BinarySearchTree<>(50);
        //tree.add();
        tree.add(30);
        tree.add(20);
        tree.add(40);
        tree.add(70);
        tree.add(60);
        tree.add(80);
        System.out.println(tree.toLevelOrderList());
        tree.clear();
    //    System.out.println(tree.toLevelOrderList());
  //      tree.remove(30);
        System.out.println(tree.toLevelOrderList());
//        tree.remove(50);
  //      System.out.println(tree.toLevelOrderList());
//        System.out.println(tree.toLeftRightList());
//        System.out.println(tree.toRightLeftList());

    }
}
