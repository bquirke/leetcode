package leetcode.mockinterview.sean0209;

import leetcode.helpers.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class FizzBuzz {

    /**
     * FizzBuzz Progressive Challenge
     *
     * EASY:
     *  Implement the classic FizzBuzz:
     *   - Print numbers from 1 to n
     *   - For multiples of 3, print "Fizz"
     *   - For multiples of 5, print "Buzz"
     *   - For multiples of both, print "FizzBuzz"
     *
     * MEDIUM:
     *  Modify your solution so that:
     *   - Instead of printing directly, return a List<String> containing the results
     *   - For example, fizzBuzz(5) should return:
     *     ["1", "2", "Fizz", "4", "Buzz"]
     *
     * HARD:
     *  Generalize FizzBuzz:
     *   - Write a method fizzBuzzCustom(int n, Map<Integer, String> rules)
     *   - For each number 1..n:
     *       - If divisible by any key in rules, append the corresponding string(s)
     *       - If not divisible by any key, just use the number
     *   - Example:
     *       fizzBuzzCustom(15, {3 -> "Fizz", 5 -> "Buzz", 7 -> "Bazz"})
     *       Should produce:
     *       ["1", "2", "Fizz", "4", "Buzz", "Fizz", "Bazz", "8", "Fizz", "Buzz", "11", "Fizz", "13", "Bazz", "FizzBuzz"]
     *
     * Implement your solutions in the methods below.
     */


    @Test
    public void testFizzBuzz_Easy() {

//        List<String> expected = Arrays.asList("1", "2", "Fizz", "4", "Buzz");
//        assertEquals(expected, fizzBuzzMedium(5));

        Map<Integer, String> rules = new HashMap<>();
        rules.put(3, "Fizz");
        rules.put(5, "Buzz");
        rules.put(7, "Bazz");

        List<String> toTest = fizzBuzzCustom(15, rules);
        toTest.forEach(s -> System.out.println(s));

    }

    @Test
    public void testFizzBuzz_Easy2() {
        //fizzBuzz(30);
    }


    public void fizzBuzz(int n){
        for(int curr = 1; curr <= n; curr++){
            // check for 3
            if (curr % 3 == 0 && curr % 5== 0){
                System.out.println("FizzBuzz");
            }
            else if(curr % 5 == 0){
                System.out.println("Buzz");
            }
            else if(curr % 3 == 0){
                System.out.println("Fizz");
            }
            else {
                System.out.println(curr);
            }
        }
    }

    public List<String> fizzBuzzMedium(int n){
        List<String> ans = new ArrayList<>();

        for(int curr = 1; curr <= n; curr++){
            // check for 3
            if (curr % 3 == 0 && curr % 5== 0){
                ans.add("FizzBuzz");
            }
            else if(curr % 5 == 0){
                ans.add("Buzz");
            }
            else if(curr % 3 == 0){
                ans.add("Fizz");
            }
            else {
                ans.add(curr + "");
            }
        }

        return ans;
    }

    public List<String> fizzBuzzCustom(int n, Map<Integer, String> rules){
        List<String> ans  = new ArrayList<>();
        // iterate 1 to n
        for(int i = 1; i <= n ; i++){
            // iterate over keyset
            StringBuilder elem = new StringBuilder();


            for(Integer divisor : rules.keySet()){
               if(i % divisor == 0){
                   elem.append(rules.get(divisor));
               }
            }

            if(elem.length() != 0){
                ans.add(elem.toString());
            }
            else {
                ans.add(i + "");
            }

        }

        return ans;
    }
}
