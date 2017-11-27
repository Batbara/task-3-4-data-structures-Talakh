package by.tr.web.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyArrayListTest {
    private MyList<Integer> listImpl = null;
    @Before
    public void initList() {
        Integer[] values = {42, 0, 73, -1, 13};
        listImpl = new MyArrayList<>(values);

    }

    @Test
    public void addTest() throws Exception {
        Object[] expected = {42, 0, 73, -1, 13, 7};
        listImpl.add(7);
        Object[] actual = listImpl.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void sizeTest() throws Exception {
        int expected = 5;
        int actual = listImpl.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isEmptyTest() throws Exception {
        boolean expected = false;
        boolean actual = listImpl.isEmpty();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeAtIndexTest() throws Exception {
        Object[] expected = {42, 0, 73, 13};
        listImpl.remove(3);
        Object[] actual = listImpl.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getTest() throws Exception {
        Integer expected = 42;
        Integer actual = listImpl.get(0);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void iteratorTest() throws Exception {
        int expected = 73;

        Iterator<Integer> iterator = listImpl.iterator();
        iterator.next();
        iterator.next();
        int actual = iterator.next();

        Assert.assertEquals(expected,actual);
    }
}