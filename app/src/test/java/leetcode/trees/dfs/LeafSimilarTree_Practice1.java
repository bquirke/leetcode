package leetcode.trees.dfs;

import leetcode.helpers.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeafSimilarTree_Practice1 {

    @Test
    public void testLeafSimilar1() {
        TreeNode root1 = new TreeNode(new Integer[]{3, 5, 1, 6, 2, 9, 8, null, null, 7, 4});
        TreeNode root2 = new TreeNode(new Integer[]{3, 5, 1, 6, 7, 4, 2, null, null, null, null, null, null, 9, 8});

        assertTrue(leafSimilar(root1, root2));
    }

    @Test
    public void testLeafSimilar2() {
        TreeNode root1 = new TreeNode(new Integer[]{1, 2, 3});
        TreeNode root2 = new TreeNode(new Integer[]{1, 3, 2});

        assertFalse(leafSimilar(root1, root2));
    }

    // ATTEMPT

    // tree transversal and store in arrays at leaf. then assert equal?
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        dfs(root1, leaves1);
        dfs(root2, leaves2);

        return leaves1.equals(leaves2);
    }

    public void dfs(TreeNode root, List<Integer> leaves) {
        // base case
        if(root == null){
            return;
        }

        if(root.left == null && root.right == null){
            leaves.add(root.val);
        }

        dfs(root.left, leaves);
        dfs(root.right, leaves);
    }
}



