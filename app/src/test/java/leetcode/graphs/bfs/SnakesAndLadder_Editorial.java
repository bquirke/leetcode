package leetcode.graphs.bfs;


import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class SnakesAndLadder_Editorial {
//    @Test
//    public void testSnakesAndLadder1(){
//        int[][] board = {
//                {-1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, 35, -1, -1, 13, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, 15, -1, -1, -1, -1}
//        };
//        assertEquals(4, snakesAndLadders(board));
//    }
//
//    @Test
//    public void testSnakesAndLadder2(){
//        int[][] board = {
//                {-1, -1, -1},
//                {-1,  9,  8},
//                {-1,  8,  9}
//        };
//        assertEquals(1, snakesAndLadders(board));
//    }
//
//    @Test
//    public void testSnakesAndLadder3(){
//        int[][] board = {
//                {-1,  7, -1},
//                {-1,  6,  9},
//                {-1, -1,  2}
//        };
//
//        assertEquals(1, snakesAndLadders(board));
//    }
//
//    @Test
//    public void testSnakesAndLadder4(){
//        int[][] board = {
//                {-1, 11,  6, -1},
//                {-1, 15, 16, -1},
//                {-1,  7, -1,  8},
//                {-1, -1, -1,  8}
//        };
//
//
//        assertEquals(2, snakesAndLadders(board));
//    }
//
//    public int snakesAndLadders(int[][] board) {
//        int n = board.length;
//        Pair<Integer, Integer>[] cells = new Pair[n * n + 1];
//        int label = 1;
//        Integer[] columns = new Integer[n];
//        for (int i = 0; i < n; i++) {
//            columns[i] = i;
//        }
//        for (int row = n - 1; row >= 0; row--) {
//            for (int column : columns) {
//                cells[label++] = new Pair<>(row, column);
//            }
//            Collections.reverse(Arrays.asList(columns));
//        }
//        int[] dist = new int[n * n + 1];
//        Arrays.fill(dist, -1);
//        Queue<Integer> q = new LinkedList<Integer>();
//        dist[1] = 0;
//        q.add(1);
//        while (!q.isEmpty()) {
//            int curr = q.remove();
//            for (int next = curr + 1; next <= Math.min(curr + 6, n * n); next++) {
//                int row = cells[next].getKey(), column = cells[next].getValue();
//                int destination = board[row][column] != -1 ? board[row][column] : next;
//                if (dist[destination] == -1) {
//                    dist[destination] = dist[curr] + 1;
//                    q.add(destination);
//                }
//            }
//        }
//        return dist[n * n];
//    }
}
