package leetcode.graphs.dfs;

import java.util.*;

public class AllRoadsToCapital {

    public static void main(String[] args){
        int[][] edges = {
                {0, 1},
                {1, 3},
                {2, 3},
                {4, 0},
                {4, 5}
        };

        System.out.println(minReorder(edges.length, edges));
    }


    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static HashSet<String> roads = new HashSet<>();
    static HashSet<Integer> seen = new HashSet<>();


    public static int minReorder(int n, int[][] connections) {
        // make a graph as if connections was indirect
        // then dfs and if any roads are facing away from 0, they need to be swapped
        for(int i = 0; i <= n; i++){
            graph.put(i, new ArrayList<>());
        }

        for(int[] connection: connections){
            int x = connection[0], y = connection[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
            roads.add(convertToHash(x, y));
        }

        seen.add(0);
        return dfs(0);
    }

    private static int dfs(int node) {
        int ans = 0;
        for(int neighbour: graph.get(0)){
            if(!seen.contains(neighbour)){
                if(roads.contains(convertToHash(node,neighbour))){
                    ans++; // road is leading away from root... due to prop of dfs
                }
                seen.add(neighbour);
                ans += dfs(neighbour);
            }

        }

        return ans;
    }

    public static String convertToHash(int row, int col) {
        return row + "," + col;
    }
}
