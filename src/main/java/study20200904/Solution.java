package study20200904;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明:叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.right = node5;
        System.out.println(new Solution().binaryTreePaths(root));
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        print(root,list,"");
        return list;
    }
    public void print(TreeNode root,List<String> list,String path){
        if (root!=null){
            StringBuilder sb = new StringBuilder(path);
            sb.append(root.val);
            if (root.left==null&&root.right==null){
                list.add(sb.toString());
            }else {
                print(root.left,list,sb.toString());
                print(root.right,list,sb.toString());
            }
        }
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
