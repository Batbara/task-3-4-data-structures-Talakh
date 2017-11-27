package by.tr.web.util;

import by.tr.web.exception.NoSuchElement;
import by.tr.web.exception.WrongParameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyLinkedListTest{
    private  MyList<Integer> listImpl = null;
    @Before
    public void initList() {
        Integer[] values = {1, 2, 100, 0, 18, -666};
        listImpl = new MyLinkedList<>(values);
    }

    @Test
    public void containsTest() throws Exception {
        boolean expected = true;
        boolean actual = listImpl.contains(-666);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = WrongParameters.class)
    public void exceptionAtRemove() throws Exception {
        Integer expected = 2;
        Integer actual = listImpl.remove(-5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void indexOfTest() throws Exception {
        int expected = 3;
        int actual = listImpl.indexOf(0);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addAtIndexTest() throws Exception {
        Object[] expected = {1, 2, 15, 100, 0, 18, -666};
        listImpl.add(2, 15);
        Assert.assertArrayEquals(expected, listImpl.toArray());
    }

    @Test
    public void removeAllTest() throws Exception {
        Integer[] valuesToRemove = {1, 0, 45};
        MyList<Integer> listToRemove = new MyArrayList<>(valuesToRemove);

        Object[] expected = {2, 100, 18, -666};
        listImpl.removeAll(listToRemove);
        Object[] actual = listImpl.toArray();

        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = NoSuchElement.class)
    public void iteratorTest() throws Exception {
        Iterator<Integer> iterator = listImpl.iterator();
        int expected = -666;
        int actual = 0;

        while (iterator.hasNext()){
            actual = iterator.next();
        }
        iterator.next();
        Assert.assertEquals(expected,actual);
    }
}