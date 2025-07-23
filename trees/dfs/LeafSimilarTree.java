package trees.dfs;

import helpers.*;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class LeafSimilarTree {

    @Test
    public void testLeafSimilar1(){
        TreeNode root1 = new TreeNode(new Integer[]{3,5,1,6,2,9,8,null,null,7,4});
        TreeNode root2 = new TreeNode(new Integer[]{3,5,1,6,7,4,2,null,null,null,null,null,null,9,8});

        assertTrue(leafSimilar(root1, root2));
    }

    @Test
    public void testLeafSimilar2(){
        TreeNode root1 = new TreeNode(new Integer[]{1,2,3});
        TreeNode root2 = new TreeNode(new Integer[]{1,3,2});

        assertFalse(leafSimilar(root1, root2));
    }



    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        dfs(root1, leaves1);
        dfs(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    private void dfs(TreeNode root, List<Integer> leaves) {

        if(root == null){
            return;
        }

        if (root.left == null && root.right == null) {
            leaves.add(root.val);
        }
        dfs(root.left, leaves);
        dfs(root.right, leaves);

    }


}
