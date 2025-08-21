package leetcode.trees.bfs;

import leetcode.graphs.bfs.helpers.TreeNode;
import org.junit.Test;

import java.util.*;

public class SymetricalTree {

    //https://leetcode.com/problems/symmetric-tree/description/?envType=problem-list-v2&envId=ajc6bx5i

    @Test
    public void testSymetricalTree() {
//         root = new TreeNode(new Integer[]{1,2,2,3,4,4,3});
//
//        assertTrue(isSymmetric(root));
    }

    // use bfs and check if each level is a mirror of themselves
    // ATTEMPTED ... naive and works but better below
    // add left and right to bfs queue in opposing order to get mirror func
    // public boolean isSymmetric(TreeNode root) {
    //     Queue<TreeNode> queue = new LinkedList<>();
    //     queue.add(root);

    //     boolean realNodes = true;
    //     while (!queue.isEmpty() && realNodes) {
    //         int breath = queue.size();

    //         List<Integer> lvl = new ArrayList<>();
    //         realNodes = false; /// assume no real nodes added
    //         for (int i = 0; i < breath; i++) {
    //             TreeNode node = queue.remove();

    //             lvl.add(node.val);

    //             if (node.left != null) {
    //                 queue.add(node.left);
    //                 realNodes = true;
    //             } else {
    //                 //preserve the empty nodes
    //                 queue.add(new TreeNode(101));
    //             }

    //             if (node.right != null) {
    //                 queue.add(node.right);
    //                 realNodes = true;
    //             } else {
    //                 queue.add(new TreeNode(101));
    //             }

    //         }

    //         if (breath > 1) {
    //             List rhs = lvl.subList(breath / 2, breath);
    //             lvl = lvl.subList(0, breath / 2);
    //             Collections.reverse(rhs);

    //             if (!lvl.equals(rhs))
    //                 return false;
    //         }

    //     }

    //     return true;
    // }

    // add left and right to bfs queue in opposing order to get mirror func
    public boolean isSymmetric(TreeNode root) {
         Queue<TreeNode> q = new LinkedList<>();
         q.add(root);
         q.add(root);
         while (!q.isEmpty()) {
             TreeNode t1 = q.poll();
             TreeNode t2 = q.poll();
             if (t1 == null && t2 == null) continue;
             if (t1 == null || t2 == null) return false;
             if (t1.val != t2.val) return false;
             q.add(t1.left);
             q.add(t2.right);
             q.add(t1.right);
             q.add(t2.left);
         }
         return true;
     }
}
