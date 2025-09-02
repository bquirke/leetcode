package leetcode.helpers;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Integer[] treeArray) {
        if (treeArray == null || treeArray.length == 0 || treeArray[0] == null) return;

        this.val = treeArray[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);

        int i = 1;
        while (i < treeArray.length) {
            TreeNode current = queue.poll();

            // Left child
            if (i < treeArray.length && treeArray[i] != null) {
                current.left = new TreeNode(treeArray[i]);
                queue.offer(current.left);
            }
            i++;

            // Right child
            if (i < treeArray.length && treeArray[i] != null) {
                current.right = new TreeNode(treeArray[i]);
                queue.offer(current.right);
            }
            i++;
        }
    }
}
