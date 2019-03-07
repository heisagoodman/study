package com.law.niuke;

import java.util.ArrayList;
import java.util.List;
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

        ListNode(int val) {
            this.val = val;
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
        if (listNode == null) {
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
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> ret = new ArrayList<Integer>();
        while (!stack.empty()) {
            ret.add(stack.pop());
        }
        return ret;
    }

    public void testPrintListFromTailToHead() {
        ListNode head = new ListNode(1, null);
        ListNode p = head;
        for (int i = 1; i <= 3; i++) {
            ListNode node = new ListNode(i, null);
            p.next = node;
            p = node;
        }
        ArrayList<Integer> list = printListFromTailToHead(head);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    /**
     * 两数之和.
     * 能力点：bugfree
     * from  leetcode
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public ListNode s1(ListNode l1, ListNode l2) {
        //思路：个位数相加，和超过9则存储十位参与下一次计算，取个位数存放在新链表
        ListNode nullHead = new ListNode(0);
        ListNode p = nullHead;

        int tenNum = 0;
        while (l1 != null && l2 != null) {
            int tmp = l1.val + l2.val + tenNum;
            tenNum = tmp / 10;
            p.next = new ListNode(tmp % 10);
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int tmp = l1.val + tenNum;
            tenNum = tmp / 10;
            p.next = new ListNode(tmp % 10);
            p = p.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int tmp = l2.val + tenNum;
            tenNum = tmp / 10;
            p.next = new ListNode(tmp % 10);
            p = p.next;
            l2 = l2.next;
        }

        if (tenNum > 0) {
            p.next = new ListNode(tenNum);
        }

        return nullHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, null);
        ListNode p = head;
        for (int i = 2; i <= 3; i++) {
            ListNode node = new ListNode(i, null);
            p.next = node;
            p = node;
        }
        ArrayList<Integer> list = printListFromTailToHeadWithStack(head);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
