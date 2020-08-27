package study20200827;

public class Tree {
    //求树的高度，他的左右子树的最大高度+1
    public int getHeight(TreeNode root){
       return root==null?0:Math.max(getHeight(root.left),getHeight(root.right))+1;
    }
    //左子树和右子树的差值小于等于1
    public boolean isBalance(TreeNode root){
        if (root==null)
            return true;
        return Math.abs(getHeight(root.left)-getHeight(root.right))<=1 && isBalance(root.right) && isBalance(root.left)
                ?true:false;
    }

    public static void main(String[] args) {

    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}