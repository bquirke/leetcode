package app.src.test.java.leetcode.graphs.bfs;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class WordLadder {

    @Test
    public void wordLadder1(){
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));

        assertEquals(5, ladderLength("hit", "cog", wordList));
    }

    @Test
    public void wordLadder2(){
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log"));

        assertEquals(0, ladderLength("hit", "cog", wordList));
    }

    class WordState{
        String word;
        int steps;

        WordState(String word, int steps){
            this.word = word;
            this.steps =  steps;
        }
    }

    /*
    * Create a graph of words that are one cha apart from start word and wordList
    * BFS it
    * */

    /*
    * ********************** PERF UPDATE **********************
    *
    * Use intermediate words.... so store h*t
    *
    * Intermediate words are wordlist entries that have each of there chars replaced with * to form a new word
    *
    * */

//    Map<String, Set<String>> graph = new HashMap<>();
//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        if(!wordList.contains(endWord)){
//            return 0;
//        }
//
//        if(!wordList.contains(beginWord)){
//            wordList.add(beginWord);
//        }
//
//        // create the graph
//        for(int i = 0; i < wordList.size(); i++){
//            String lhs = wordList.get(i);
//
//            if(!graph.containsKey(lhs)){
//                graph.put(lhs, new HashSet<>());
//            }
//
//            for(int j = 0; j < wordList.size(); j++) {
//                String rhs = wordList.get(j);
//
//                if(!graph.containsKey(rhs)){
//                    graph.put(rhs, new HashSet<>());
//                }
//
//
//                int diff = 0;
//                int idx = 0;
//                // calc up to 1 diff for neighbours
//                while(diff <= 1 && idx < rhs.length()){
//                    if(lhs.charAt(idx) != rhs.charAt(idx)){
//                        diff++;
//                    }
//
//                    idx++;
//                }
//
//                if(diff <= 1){
//                    graph.get(lhs).add(rhs);
//                    graph.get(rhs).add(lhs);
//                }
//            }
//        }
//
//        Queue<WordState> queue = new LinkedList<>();
//        Set<String> seen = new HashSet<>();
//
//        queue.add(new WordState(beginWord, 1));
//        seen.add(beginWord);
//
//        while (!queue.isEmpty()){
//            WordState node = queue.remove();
//            String word = node.word;
//            int steps = node.steps;
//
//            if(word.equals(endWord)){
//                return steps;
//            }
//
//            for(String neighbours: graph.get(word)){
//                if(!seen.contains(neighbours)){
//                    queue.add(new WordState(neighbours, steps + 1));
//                    seen.add(neighbours);
//                }
//            }
//        }
//
//
//        return 0;
//    }


    /*
     * ********************** PERF UPDATE **********************
     *
     * Use intermediate words.... so store h*t
     *
     * Intermediate words are wordlist entries that have each of there chars replaced with * to form a new word
     *
     * */

    Map<String, Set<String>> graph = new HashMap<>();
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }

        if(!wordList.contains(beginWord)){
            wordList.add(beginWord);
        }

        // create the graph
        for(int i = 0; i < wordList.size(); i++){
            String lhs = wordList.get(i);

            if(!graph.containsKey(lhs)){
                graph.put(lhs, new HashSet<>());
            }

            for(int j = 0; j < wordList.size(); j++) {
                String rhs = wordList.get(j);

                if(!graph.containsKey(rhs)){
                    graph.put(rhs, new HashSet<>());
                }


                int diff = 0;
                int idx = 0;
                // calc up to 1 diff for neighbours
                while(diff <= 1 && idx < rhs.length()){
                    if(lhs.charAt(idx) != rhs.charAt(idx)){
                        diff++;
                    }

                    idx++;
                }

                if(diff <= 1){
                    graph.get(lhs).add(rhs);
                    graph.get(rhs).add(lhs);
                }
            }
        }

        Queue<WordState> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();

        queue.add(new WordState(beginWord, 1));
        seen.add(beginWord);

        while (!queue.isEmpty()){
            WordState node = queue.remove();
            String word = node.word;
            int steps = node.steps;

            if(word.equals(endWord)){
                return steps;
            }

            for(String neighbours: graph.get(word)){
                if(!seen.contains(neighbours)){
                    queue.add(new WordState(neighbours, steps + 1));
                    seen.add(neighbours);
                }
            }
        }


        return 0;
    }
}
