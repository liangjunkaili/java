package study20200914;

import java.util.ArrayList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode left = new TreeNode(3);
        root.right = right;
        right.left = left;
        Solution solution = new Solution();
        List<Integer> list = solution.inorderTraversal(root);
        list.forEach(System.out::println);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
//        inorder(root,list);
//        preorder(root,list);
        postorder(root,list);
        return list;
    }
    private void inorder(TreeNode root,List<Integer> list){
        if (root==null)return;
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }
    private void preorder(TreeNode root,List<Integer> list){
        if (root==null)return;
        list.add(root.val);
        inorder(root.left,list);
        inorder(root.right,list);
    }
    private void postorder(TreeNode root,List<Integer> list){
        if (root==null)return;
        inorder(root.left,list);
        inorder(root.right,list);
        list.add(root.val);
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val){
        this.val =val;
    }
}