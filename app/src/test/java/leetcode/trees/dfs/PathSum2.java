package leetcode.trees.dfs;

import leetcode.graphs.bfs.helpers.TreeNode;
import leetcode.graphs.bfs.helpers.TreePrinter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PathSum2 {

    @Test
    public void testPathSum1(){
        TreeNode toTest = new TreeNode(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1});
        TreePrinter.printTree(toTest);

        List<List<Integer>> verify = new ArrayList<>();
        verify.add(Arrays.asList(5,8,4,5));
        verify.add(Arrays.asList(5,4,11,2));

        List<List<Integer>> result = pathSum(toTest, 22);

        assertEquals(verify, result);

    }

    List<List<Integer>> finalPaths = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathSumDfs(root, targetSum, new ArrayList<>());

        return finalPaths;
    }

    public void pathSumDfs(TreeNode root, int target, List<Integer> path){
        if(root == null){
            return;
        }
        path.add(root.val);
        target -= root.val;
        // at leaf
        if(root.left == null && root.right == null){
            // if target sum achieved
            if(target == 0){
                finalPaths.add(new ArrayList<>(path));
            }
        }

        pathSumDfs(root.left, target, path);
        pathSumDfs(root.right, target, path);

        path.remove(path.size() - 1);
    }
}
