package graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfProvinces {

    public static void main(String[] args){
        int[][] isConnected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println(findCircleNum(isConnected));
    }

    static boolean[] seen;
    static Map<Integer, List<Integer>> graph = new HashMap<>();

    /*
    - Build a graph
    - Iterate thru nodes
        If a node is unsee we can say its a connected component (prov)
            after we do dfs on its neighbours
    */
    public static int findCircleNum(int[][] isConnected) {
        // build the graph
        int n = isConnected.length;

        for(int i = 0; i < n; i++){
            if(!graph.containsKey(i)){
                graph.put(i, new ArrayList<Integer>());
            }

            for(int j = i + 1; j < n; j++){
                if(!graph.containsKey(j)){
                    graph.put(j, new ArrayList<Integer>());
                }

                if(isConnected[i][j] == 1){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        // iterate thru seen and dfs each unseen node
        seen = new boolean[n];

        int ans = 0;

        for(int i = 0; i < n; i++){
            if(!seen[i]){
                ans++;
                seen[i] = true;
                dfs(i);
            }
        }

        return ans;

    }

    public static void dfs(int node){
        for(int neighbour: graph.get(node)){
            if(!seen[neighbour]){
                seen[neighbour] = true;
                dfs(neighbour);
            }
        }
    }


}
