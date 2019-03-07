package com.law.niuke;


import java.util.*;

/**
 * 描述：
 * 1、前/中/后序遍历二叉树，递归和非递归
 * 2、层序遍历二叉树。使用队列
 * 3、打印二叉树的所有路径。递归或
 *
 * @author law
 * @create 2019-02-24 下午4:23
 */
public class BinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static TreeNode rebuildTree(int[] preArr, int[] midArr, int i, int j, int m, int n) {
        if (i > j) {
            return null;
        }

        //根节点在中序数组的下标
        int midIdx = m;
        for (; midIdx <= n; midIdx++) {
            if (midArr[midIdx] == preArr[i]) {
                break;
            }
        }

        //左子树节点个数
        int nLeft = midIdx - m;

        TreeNode left = rebuildTree(preArr, midArr, i + 1, i + nLeft, m, midIdx - 1);
        TreeNode right = rebuildTree(preArr, midArr, i + nLeft + 1, j, midIdx + 1, n);
        TreeNode root = new TreeNode(preArr[i], left, right);
        return root;
    }

    /**
     * 先序遍历
     *
     * @param node
     */
    public static void preVisit(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preVisit(node.left);
        preVisit(node.right);
    }

    /**
     * 先序遍历 非递归实现
     *
     * @param node
     */
    public static void preVisitNoRecursion(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(node);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            if (tmp == null) {
                continue;
            }
            System.out.println(tmp.val);
            stack.add(tmp.right);
            stack.add(tmp.left);
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public static void midVisit(TreeNode node) {
        if (node == null) {
            return;
        }
        midVisit(node.left);
        System.out.println(node.val);
        midVisit(node.right);
    }


    /**
     * 中序遍历 非递归实现.
     * 思路：先把左子树入栈，再处理根节点，再把右子树入栈。如果当前指针是Null，则pop根节点进行处理，然后push右节点。
     * 注意：需要一个指针用来保存当前访问的节点，用栈来存储所有的根节点。
     * 内核：算法里，若一个节点先获取到，但是后被处理，一定是用栈结构
     *
     * @param node
     */
    public static void midVisionNoRecursion(TreeNode node) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = node;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.println(cur.val);
                cur = cur.right;
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public static void lastVisit(TreeNode node) {
        if (node == null) {
            return;
        }
        lastVisit(node.left);
        lastVisit(node.right);
        System.out.println(node.val);
    }

    /**
     * 后序遍历二叉树。非递归实现
     * 思路：双栈法
     *
     * @param node
     */
    public static void lastVisitNoRecursion(TreeNode node) {
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(node);
        while (!stack1.isEmpty()) {
            TreeNode cur = stack1.pop();
            stack2.push(cur);
            if (cur.left != null) {
                stack1.push(cur.left);
            }
            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val);
        }
    }

    /**
     * 层序遍历
     *
     * @param node
     */
    public static void layerVisit(TreeNode node) {
        QueueAl<TreeNode> queue = new QueueAl<TreeNode>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            if (cur == null) {
                continue;
            }
            System.out.println(cur.val);
            queue.add(cur.left);
            queue.add(cur.right);
        }
    }

    /**
     * 递归的方式获取二叉树的所有路径
     *
     * @param treeNode
     * @return
     */
    public static List<LinkedList<TreeNode>> getAllPath(TreeNode treeNode) {
        List<LinkedList<TreeNode>> ret = new ArrayList<LinkedList<TreeNode>>();
        if (treeNode == null) {
            return ret;
        }
        List<LinkedList<TreeNode>> lefts = getAllPath(treeNode.left);
        List<LinkedList<TreeNode>> rights = getAllPath(treeNode.right);
        ret.addAll(lefts);
        ret.addAll(rights);
        if (ret.isEmpty()) {
            LinkedList path = new LinkedList();
            path.add(treeNode);
            List all = new ArrayList();
            all.add(path);
            return all;
        } else {
            for (LinkedList<TreeNode> path : ret) {
                path.addFirst(treeNode);
            }
        }
        return ret;
    }

    public static void printBinaryTree(TreeNode treeNode){
        LinkedList<TreeNode> path = new LinkedList<TreeNode>();
        path.add(treeNode);
        TreeNode cur = treeNode;
        TreeNode last = treeNode;
        while (cur != null){
            if(cur.left!=null){
            }
        }
    }



    private static void printPath(List<TreeNode> path) {
        String s = "";
        for (TreeNode treeNode : path) {
            s += ","+treeNode.val;
        }
        System.out.println(s.substring(1));
    }

    public static void main(String[] args) {
        int[] preArr = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] midArr = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = rebuildTree(preArr, midArr, 0, preArr.length - 1, 0, midArr.length - 1);
        List<LinkedList<TreeNode>> list = getAllPath(root);
        for (LinkedList<TreeNode> p : list){
            printPath(p);
        }
        HashMap map = new HashMap();
        map.get(1);
//        list.forEach(path ->{printPath(path);});
    }
}
