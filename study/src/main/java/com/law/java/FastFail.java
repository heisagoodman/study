package com.law.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 描述：快速失败机制，即在迭代集合时，不允许对集合的元素增加/删除
 *
 * @author law
 * @create 2019-03-02 下午6:08
 */
public class FastFail {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(3));
        //抛出ConcurrentModificationException
//        for (Integer i : list){
//            list.add(new Integer(4));
//            System.out.println(i);
//    }
    //抛出ConcurrentModificationException
//        for (Iterator iterator=list.iterator();iterator.hasNext();){
//            list.add(new Integer(4));
//            System.out.println(iterator.next());
//
//        }
    }
}
