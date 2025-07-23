package trees.dfs;


import helpers.*;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import static org.junit.Assert.*;


public class PathSum3 {
    /*
    * https://leetcode.com/problems/path-sum-iii/description/?envType=problem-list-v2&envId=ajc6bx5i
    */

    @Test
    public void testPathSum3(){
        TreeNode node = new TreeNode(new Integer[]{10,5,-3,3,2, null,11,3,-2,null,1});

        TreePrinter.printTree(node);
        assertEquals(3, pathSum(node, 8));
    }

    @Test
    public void testPathSum3_1(){
        TreeNode node = new TreeNode(new Integer[]{1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000});
        TreePrinter.printTree(node);
        assertEquals(0, pathSum(node, 0));

    }

    Map<Long, Integer> counts = new HashMap<>();
    int k;
    int count;
    public int pathSum(TreeNode root, int targetSum) {
        k = targetSum;
        counts.put(0L, 1);

        dfs(root, 0);

        return count;
    }

    public void dfs(TreeNode root, long curr){
        if(root == null){
            return;
        }

        // update curr
        curr += root.val;

        // add freq of curr - k ..... if it has a freq... if it does we've seen this prefix before
        count += counts.getOrDefault(curr - k, 0);

        // add to counts
        counts.put(curr, counts.getOrDefault(curr, 0) + 1);

        dfs(root.left, curr);
        dfs(root.right, curr);

        // remove node prefix freq from map as we are transversing back up
        counts.put(curr, counts.getOrDefault(curr, 0) - 1);
    }

}
