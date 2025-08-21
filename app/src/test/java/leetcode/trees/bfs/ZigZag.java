package leetcode.trees.bfs;


import leetcode.graphs.bfs.helpers.PrettyPrintTree;
import leetcode.graphs.bfs.helpers.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigZag {

    public static void main(String[] args){
        Integer[] test =
                //{3,9,20,67,55,15,7};
                {1,2,3,4,null,null,5};
                //{3,9,20,null,16,15,7};


        TreeNode testTree = new TreeNode(test);

        PrettyPrintTree.print(testTree);
        List<List<Integer>> nas = zigzagLevelOrder(testTree);
        System.out.println(nas);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Deque<TreeNode> rDir = new ArrayDeque();
        rDir.add(root);

        Deque<TreeNode> lDir = new ArrayDeque();


        List<List<Integer>> ans = new ArrayList<>();
        boolean leftDir = false;
        while (!rDir.isEmpty() || !lDir.isEmpty()) {
            int breath = leftDir ? lDir.size() : rDir.size();
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < breath; i++) {
                TreeNode node;
                // the zigzag depends on order of visiting in bfs
                // so when depth is odd l-r
                // when even r-l
                if (leftDir) {
                    node = lDir.pollLast();
                } else {
                    node = rDir.pollLast();
                }

                // add curr val to list
                row.add(node.val);

                if(leftDir){
                    if(isNodeNull(node.right)){
                        rDir.add(node.right);
                    }
                    if (isNodeNull(node.left)){
                        rDir.add(node.left);
                    }
                }
                else {
                    if(isNodeNull(node.left)){
                        lDir.add(node.left);
                    }
                    if(isNodeNull(node.right)){
                        lDir.add(node.right);
                    }
                }

            }
            leftDir = !leftDir;
            ans.add(row);
        }

        return ans;
    }

    private static boolean isNodeNull(TreeNode node) {
        return node != null;
    }
}
