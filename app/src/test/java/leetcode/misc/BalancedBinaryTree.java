package leetcode.misc;


import leetcode.helpers.TreeNode;
import leetcode.helpers.TreePrinter;

public class BalancedBinaryTree {

    public static void main(String[] args){
        Integer[] test =
                //{2,null,3,null,4,null,5,null,6};
                {1,2,3,4,null,null,5};


        TreeNode testTree = new TreeNode(test);

        TreePrinter.printTree(testTree);
    }

    boolean isBalanced(TreeNode root) {
        return true;
    }
}
