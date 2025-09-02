package leetcode.trees.dfs;

import leetcode.helpers.TreeNode;
import leetcode.helpers.TreePrinter;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteNodeBST {

    @Test
    public void testDeleteNodeBST() {
        TreeNode tree = new TreeNode(new Integer[]{5,3,6,2,4,null,7});
        TreeNode result = new TreeNode(new Integer[]{5,2,6,null,4,null,7});
        TreeNode verify = deleteNode(tree, 3);

        System.out.println("...............................SHOULD LOOK LIKE............................... ");
        TreePrinter.printTree(result);
        System.out.println("...............................                ............................... ");
        System.out.println("...............................LOOKS LIKE       ............................... ");
        TreePrinter.printTree(verify);
        System.out.println("...............................                ............................... ");
        assertEquals(result, verify);
    }

    /* ATTEMPT
        - dfs to find node
        - keep parent pointer
        - child of del node.val becomes curr.val
        - child.val becomes key and we repeat till leaf
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode();
        dummy.left = root;
        dfs(root, dummy, key);
        return dummy.left;
    }

    private TreeNode dfs(TreeNode root, TreeNode dummy, int key) {

        if(root == null){
            return dummy;
        }

        if(root.val == key){
            // has 2 kids
            // do we ever need to go right???
            if(root.left != null && root.right != null){
                // copy left val and search for left.val until we hit a leaf
                root.val = root.left.val;
                return dfs(root.left, root, root.left.val);
            }

            // one kid or no kids
            if(root.left == null && root.right == null){
                if(key < dummy.val){
                    // means we came from dummy.left
                    dummy.left = null;
                }
                else {
                    dummy.right = null;
                }
            }
            if(root.left == null){
                if(key < dummy.val){
                    // means we came from dummy.left
                    dummy.left = root.right;
                }
                else {
                    dummy.right = root.right;
                }
            }
            if(root.right == null){
                if(key < dummy.val){
                    // means we came from dummy.left
                    dummy.left = root.left;
                }
                else {
                    dummy.right = root.left;
                }
            }

        }

        if(dummy.val < key){
            return dfs(root.left, root, key);
        }
        else {
            return dfs(root.right, root, key);
        }
    }
}
