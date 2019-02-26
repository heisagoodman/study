package com.law.niuke;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 描述：
 *
 * @author law
 * @create 2019-02-24 下午1:23
 */
public class LinkedListAl {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     *
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList ret = new ArrayList();
        if(listNode == null){
            return ret;
        }
        ArrayList list = printListFromTailToHead(listNode.next);
        ret.addAll(list);
        ret.add(listNode.val);
        return ret;
    }

    /**
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     * 用栈的方式实现
     *
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHeadWithStack(ListNode listNode) {
        Stack<Integer> stack = new Stack<Integer>();
        while (listNode!=null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> ret = new ArrayList<Integer>();
        while (!stack.empty()){
            ret.add(stack.pop());
        }
        return ret;
    }

    public void testPrintListFromTailToHead(){
        ListNode head = new ListNode(1, null);
        ListNode p = head;
        for (int i=1;i<=3;i++){
            ListNode node = new ListNode(i,null);
            p.next = node;
            p = node;
        }
        ArrayList<Integer> list = printListFromTailToHead(head);
        for (Integer integer : list){
            System.out.println(integer);
        }
    }

    public static void main(String[] args){
        ListNode head = new ListNode(1, null);
        ListNode p = head;
        for (int i=2;i<=3;i++){
            ListNode node = new ListNode(i,null);
            p.next = node;
            p = node;
        }
        ArrayList<Integer> list = printListFromTailToHeadWithStack(head);
        for (Integer integer : list){
            System.out.println(integer);
        }
    }
}
