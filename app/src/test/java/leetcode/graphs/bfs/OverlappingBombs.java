package app.src.test.java.leetcode.graphs.bfs;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class OverlappingBombs {

    @Test
    public void testSnakesAndLadder1(){
        int[][] bombs = {
                {1, 2, 3},
                {2, 3, 1},
                {3, 4, 2},
                {4, 5, 3},
                {5, 6, 4}
        };
        assertEquals(5, maximumDetonation(bombs));
    }

    // create graph of nodes being bombs and theres edges being if they detonate... i.e they overlap

    Map<Integer, List<Integer>> graph = new HashMap<>();

    public int maximumDetonation(int[][] bombs) {



        // Creat graph of bombs and neighbours of bombs they detonate
        //
        for(int i = 0; i < bombs.length; i++){
            if(!graph.containsKey(i)){
                graph.put(i, new ArrayList<>());
            }


            int[] vars = bombs[i];
            int x1 = vars[0], y1 = vars[1], r1 = vars[2];

            for(int j = 0; j < bombs.length; j++){
                if(!graph.containsKey(j)){
                    graph.put(j, new ArrayList<>());
                }

                int[] cords = bombs[j];
                int x2 = cords[0], y2 = cords[1], r2 = cords[2];

                // bomb 1
                double distanceBetween = Math.sqrt(
                        (Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2))
                );

                if(distanceBetween <= r1){
                    // BOOM
                    graph.get(i).add(j);
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < bombs.length; i++){
            answer = Math.max(answer, bfs(i));
        }

        return answer;
    }

    public int bfs(int node){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();

        queue.add(node);
        seen.add(node);

        while(!queue.isEmpty()){
            int bomb = queue.remove();

            for(int neighbour: graph.get(bomb)){
                if(!seen.contains(neighbour)){
                    queue.add(neighbour);
                    seen.add(neighbour);
                }
            }

        }

        return seen.size();

    }
}
