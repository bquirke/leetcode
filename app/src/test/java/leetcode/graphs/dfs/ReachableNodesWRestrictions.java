package leetcode.graphs.dfs;

import java.util.*;

public class ReachableNodesWRestrictions {

    public static void main(String[] args){
        int[][] edges = {
                {0, 1},
                {1, 2},
                {3, 1},
                {4, 0},
                {0, 5},
                {5, 6}
        };
        int[] res = {4,5};
        System.out.println(reachableNodes(7, edges, res));
    }

    // create an adajency list graph
    //iterate it and we should do a restricted check in the dfs
    /// OR we check restricted nodes when creating our graph and drop those if found
    static Set<Integer> seen = new HashSet<>();
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    int visited = 0;
    public static int reachableNodes(int n, int[][] edges, int[] restricted) {
        Set<Integer> res = new HashSet<>();

        for(int nogo: restricted){
            res.add(nogo);
        }
        for(int i = 0; i < n; i++){
            graph.put(i, new ArrayList<>());
        }

        // dont add restricted nodes to graph
        for(int i = 0; i < edges.length; i++){
            if(!res.contains(edges[i][0]) && !res.contains(edges[i][1])){
                graph.get(edges[i][0]).add(edges[i][1]);
                graph.get(edges[i][1]).add(edges[i][0]);
            }
        }

        seen.add(0);
        dfs(0);

        return seen.size();
    }

    static void dfs(int node){
        for(int neighbour : graph.get(node)){
            if(!seen.contains(neighbour)){
                seen.add(neighbour);

                dfs(neighbour);
            }
        }
    }
}
