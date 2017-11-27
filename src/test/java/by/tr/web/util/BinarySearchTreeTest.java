package by.tr.web.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class BinarySearchTreeTest {
    private Tree<Integer> tree = null;

    @Before
    public void initTree() {
            /* Binary Search Tree:
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree = new BinarySearchTree<>(50);
        tree.add(30);
        tree.add(20);
        tree.add(40);
        tree.add(70);
        tree.add(60);
        tree.add(80);
    }

    @Test
    public void containsTest() throws Exception {
        boolean expected = true;
        boolean actual = tree.contains(40);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void levelOrderTraversalTest() throws Exception {
        Object[] expected = {50, 30, 70, 20, 40, 60, 80};
        MyList<Integer> actualList = new MyArrayList<>(7);
        Iterator<Integer> iterator = tree.levelOrderIterator();
        while (iterator.hasNext()) {
            actualList.add(iterator.next());
        }
        Object[] actual = actualList.toArray();
        Assert.assertArrayEquals(expected, actual);

    }

    @Test
    public void rightLeftTraversalTest() throws Exception {
        Object[] expected = {80, 70, 60, 50, 40, 30, 20};
        MyList<Integer> actualList = tree.toRightLeftList();
        Object[] actual = actualList.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void leftRightTraversalTest() throws Exception {
        Object[] expected = {20, 30, 40, 50, 60, 70, 80};
        MyList<Integer> actualList = new MyArrayList<>(7);
        Iterator<Integer> iterator = tree.leftRightIterator();
        while (iterator.hasNext()) {
            actualList.add(iterator.next());
        }
        Object[] actual = actualList.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void deleteNodeTest() throws Exception {
        /*
             50                              50
           /     \         remove(30)      /   \
          30      70       --------->    40     70
         /  \    /  \                    /     /  \
       20   40  60   80                20     60   80
         */

        Object[] expected = {50, 40, 70, 20, 60, 80};
        tree.remove(30);

        MyList<Integer> actualList = tree.toLevelOrderList();
        Object[] actual = actualList.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void addNodeTest() throws Exception {
        /*
             50                              50
           /     \         add(75)         /   \
          30      70       --------->    30     70
         /  \    /  \                   / \    /  \
       20   40  60   80                20  40  60   75
                                                     \
                                                      80
         */

        Object[] expected = {80, 75, 70, 60, 50, 40, 30, 20};
        tree.add(75);

        MyList<Integer> actualList = tree.toRightLeftList();
        Object[] actual = actualList.toArray();
        Assert.assertArrayEquals(expected, actual);
    }
}