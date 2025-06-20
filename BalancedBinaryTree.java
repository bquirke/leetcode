import helpers.TreeNode;
import helpers.TreePrinter;

public class BalancedBinaryTree {

    public static void main(String[] args){
        Integer[] test = {1,2,2,3,3,null,null,4,4};

        TreeNode testTree = new TreeNode(test);

        TreePrinter.printTree(testTree);
    }

    boolean isBalanced(TreeNode root) {
        return true;
    }
}
