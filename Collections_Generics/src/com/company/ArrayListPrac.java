package com.company;

import java.util.ArrayList;
import java.util.ListIterator;

public class ArrayListPrac<T> {
    private ArrayList<T> list;

    public ArrayListPrac(ArrayList<T> list) {
        this.list = list;
    }
    public void displayList()
    {
        ListIterator it = list.listIterator();
        while(it.hasNext())
        {
            System.out.println(it.nextIndex() + " "+ it.next());
        }
    }
    public void addAt(int ind,T val)
    {
        list.add(ind,val);
    }
    public int getSize()
    {
        return list.size();
    }
    public void setNew(ArrayList<? extends T> newList)
    {
        list.addAll(newList);
        list.retainAll(newList);
    }
}
