package by.tr.web.controller;

import by.tr.web.util.MyArrayList;
import by.tr.web.util.MyIterator;
import by.tr.web.util.MyList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String... args) {
        MyList<Integer> myList = new MyArrayList<>();
        MyList<Integer> secondList = new MyArrayList<>();
        List<Integer> utilList = new ArrayList<>();
        List<Integer> newli = new ArrayList<>(utilList);

        secondList.add(3);
        secondList.add(5);
        utilList.remove(new Integer(15));
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);

        for (Object i :
                (Iterable)myList)
            System.out.println(i);

        System.out.println();
        System.out.println(myList);
    }
}
