package graphs.dfs;

import helpers.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class EqualityEquations {

    @Test
    public void testEqualityEquations() {
        String[] equations = new String[]{"a==b","b!=a"};

        assertFalse(equationsPossible(equations));
    }

    @Test
    public void testEqualityEquations2() {
        String[] equations = new String[]{"b==a","a==b"};

        assertTrue(equationsPossible(equations));
    }

    @Test
    public void testEqualityEquations3() {
        String[] equations = new String[]{"c==c","b==d","x!=z"};

        assertTrue(equationsPossible(equations));
    }

    @Test
    public void testEqualityEquations4() {
        String[] equations = new String[]{"b==b","b==e","e==c","d!=e"};

        assertTrue(equationsPossible(equations));
    }


    /* ATTEMPT
        -letters are nodes
        - == is an outgoing edge
        - != is not an edge ....
        - create an undirected  graph and dfs each equation
        - lhs is start and rhs is target... verify if you can or cant reach node
     */

    Map<Character, List<Character>> graph = new HashMap<>();
    Set<Character> seen;
    public boolean equationsPossible(String[] equations) {

        // build undirected graph
        for(String eq: equations){
            Character lhs = eq.charAt(0);
            Character rhs = eq.charAt(3);

            if(!graph.containsKey(lhs)){
                graph.put(lhs, new ArrayList<>());
            }

            if(!graph.containsKey(rhs)){
                graph.put(rhs, new ArrayList<>());
            }

            // equal to
            if(eq.charAt(1) == '=') {
                graph.get(lhs).add(rhs);
                graph.get(rhs).add(lhs);
            }
        }

        // process each eq and dfs to evaluate
        for(String eq: equations){
            Character lhs = eq.charAt(0);
            Character rhs = eq.charAt(3);

            // reset seen for new dfs
            seen = new HashSet<>();
            seen.add(lhs);

            if(eq.charAt(1) == '=') {
                // should be able to find
                if(!dfs(lhs, rhs)){
                    return false;
                }
            }
            else {
                // should NOT be able to find
                if(dfs(lhs, rhs)){
                    return false;
                }
            }
        }

        // all eq's check out
        return true;
    }

    private boolean dfs(Character start, Character target) {
        if(start == target){
            return true;
        }
        boolean ans = false;
        for (Character neighbour : graph.get(start)){
            if(!seen.contains(neighbour)){
                seen.add(neighbour);
                ans = dfs(neighbour, target);
                if(ans) return true;
            }
        }

        return false;
    }
}
