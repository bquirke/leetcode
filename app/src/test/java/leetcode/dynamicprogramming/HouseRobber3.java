package leetcode.dynamicprogramming;

import leetcode.helpers.TreeNode;
import leetcode.helpers.TreePrinter;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HouseRobber3 {

    @Test
    public void testHouseRobber3() {
        TreeNode toTest = new TreeNode(new Integer[]{3,2,3,null,3,null,1});
        TreePrinter.printTree(toTest);
        assertEquals(7, rob(toTest));
    }

    /* ATTEMPT
        - dp(i) => returns max money till ith node

        - dp(i) => max(skip, rob)
            skip => max(i.left, i.right)
            rob => rob(i.left.children) + rob(i.right.children) + root.val

        - base case => dp(null) = 0
    */
    Map<TreeNode, Integer> mem = new HashMap<>();

    public int rob(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }

        // mem
        if (mem.containsKey(root)) {
            return mem.get(root);
        }

        // skip by default
        int skip =  rob(root.left) + rob(root.right);

        // start robbing
        int rob = 0;
        if (root.left != null) {
            // rob left childs children ... skip a house
            rob += rob(root.left.left) + rob(root.left.right);
        }

        if (root.right != null) {
            // rob right childs children .... skip a house
            rob += rob(root.right.left) + rob(root.right.right);
        }

        rob += root.val;

        int ans = Math.max(skip, rob);
        mem.put(root, ans);

        return mem.get(root);

    }
}