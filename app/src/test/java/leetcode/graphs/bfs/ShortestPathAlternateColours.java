package leetcode.graphs.bfs;

import leetcode.helpers.Colour;
import leetcode.helpers.ColourState;

import java.util.*;

import static leetcode.helpers.Colour.BLUE;
import static leetcode.helpers.Colour.RED;

public class ShortestPathAlternateColours {


    public static void main(String[] args){
        int[][] redEdges = {
                {0, 1}
        };

        int[][] blueEdges = {
                {2, 1}
        };
        int ans[] = shortestAlternatingPaths(3,redEdges, blueEdges);

        for(int i: ans){
            System.out.println(i);
        }
    }



    // this map will represent transversals (Colour : Node : Neighbours)
    static Map<Colour, HashMap<Integer, List<Integer>>> graph = new HashMap<>();

    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        // populate the (Colour : Node : Neighbours)
        graph.put(BLUE, new HashMap<Integer, List<Integer>>());
        graph.put(RED, new HashMap<Integer, List<Integer>>());

        addToGraph(n, BLUE, blueEdges);
        addToGraph(n, RED, redEdges);

        // seen
        boolean[][] seen = new boolean[n][2]; // need to add state to seen array
        seen[0][BLUE.value] = true;
        seen[0][RED.value] = true;

        // queue
        Queue<ColourState> queue = new LinkedList<>();
        queue.add(new ColourState(0, 0, BLUE));
        queue.add(new ColourState(0, 0, RED));

        // min dist ans
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        while(!queue.isEmpty()){
            ColourState state = queue.remove();
            int node = state.node;
            int steps = state.steps;
            Colour colour = state.colour;
            ans[node] = Math.min(ans[node], steps);
            for(int neighbour : graph.get(colour).get(node)){
                if(!seen[neighbour][colour.opposite().value]){
                    seen[neighbour][colour.opposite().value] = true;
                    queue.add(new ColourState(neighbour, steps + 1, colour.opposite()));
                }
            }
        }

        for(int i= 0; i < n; i++){
            if(ans[i] == Integer.MAX_VALUE){
                ans[i] = -1;
            }
        }
        return ans;
    }

    private static void addToGraph(int n, Colour colour, int[][] blueEdges) {
        for(int i = 0; i < n; i++){
            graph.get(colour).put(i, new ArrayList<>());
        }

        for(int[] neighbours: blueEdges){
            int x = neighbours[0];
            int y = neighbours[1];

            // directed so only one way
            graph.get(colour).get(x).add(y);
        }
    }
}
