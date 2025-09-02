package leetcode.trees.dfs;

import leetcode.helpers.TreeNode;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SymetricalTree {

    // https://leetcode.com/problems/symmetric-tree/description/?envType=problem-list-v2&envId=ajc6bx5i

    @Test
    public void testSymetricalTree() {
        TreeNode root = new TreeNode(new Integer[]{1,2,2,3,4,4,3});

        assertTrue(isSymmetric(root));
    }

    // use dfs recursive
    // transverse both subtrees from root at same time???
    // ATTEMPTED ... correct
    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode x, TreeNode y){
        if(x == null || y== null){
            if (x == null && y == null) {
                return true;
            }
            // not null togeter.... diff
            else return false;
        }

        if(x.val == y.val){
            return dfs(x.left, y.right) && dfs(x.right, y.left);
        }
        else {
            return false;
        }

    }
}
