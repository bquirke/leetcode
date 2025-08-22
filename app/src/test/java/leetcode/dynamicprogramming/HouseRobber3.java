package leetcode.dynamicprogramming;

import leetcode.graphs.bfs.helpers.TreeNode;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HouseRobber3 {

    @Test
    public void testHouseRobber3() {
        TreeNode toTest = new TreeNode(new Integer[]{3,2,3,null,3,null,1});
        assertEquals(7, rob(toTest));
    }

    /* ATTEMPT
        - dp(i) => returns max money till ith node

        - dp(i) => max(skip, rob)
            skip => max(i.left, i.right)
            rob max(i.left.children, i.right.children)

        - base case => dp(null) = 0
    */
    Map<TreeNode, Integer> mem = new HashMap<>();
    public int rob(TreeNode root) {
        // base case
        if(root == null){
            return 0;
        }

        // mem
        if(mem.containsKey(root)){
            return mem.get(root);
        }

        // skip by default
        int ans = Math.max(rob(root.left), rob(root.right));

        // rob left child
        ans = Math.max(ans, Math.max(rob(root.left.left) , rob(root.left.right)));

        // rob left child
        ans = Math.max(ans, Math.max(rob(root.right.left) , rob(root.right.right)));

        mem.put(root, ans);

        return mem.get(root);

    }
}