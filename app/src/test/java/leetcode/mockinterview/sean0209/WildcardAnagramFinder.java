package leetcode.mockinterview.sean0209;

import java.util.*;

/**
 * INTERVIEW QUESTION: Anagrams in a Dictionary
 *
 * Part 1 — Basic Version
 *   Given a word `target` and a dictionary of words, return all dictionary words
 *   that are anagrams of `target`.
 *
 * Example:
 *   target = "listen"
 *   dictionary = ["enlist", "google", "inlets", "banana", "silent"]
 *   Output = ["enlist", "inlets", "silent"]
 *
 *
 * Part 2 — Multiple Queries
 *   Extend your solution to handle multiple queries efficiently.
 *   Preprocess the dictionary so you can quickly answer queries.
 *
 * Example:
 *   dictionary = ["enlist", "google", "inlets", "banana", "silent", "stone", "tones", "notes"]
 *   queries = ["listen", "stone"]
 *
 *   Output:
 *   listen -> [enlist, inlets, silent]
 *   stone  -> [stone, tones, notes]
 *
 *
 * Part 3 — Wildcard Queries (Hard)
 *   Queries may contain wildcards `*` which match exactly one character.
 *   Return all dictionary words that are anagrams of the query and also match the wildcard pattern.
 *
 * Example:
 *   dictionary = ["enlist", "google", "inlets", "banana", "silent", "stone", "notes"]
 *
 *   query = "li*ten"  -> ["enlist", "silent", "inlets"]
 *   query = "sto*e"   -> ["stone", "notes"]
 *
 *   Notes:
 *   - Queries and words must be the same length.
 *   - `*` can appear multiple times.
 */

public class WildcardAnagramFinder {

    // -------- PART 1 --------
    public static List<String> findAnagrams(String target, List<String> dictionary) {
        // should I actually create a target freq hashmap
        // create a dict hashmap and compare the maps???? ... YES

        List<String> ans = new ArrayList<>();

        for(String dictWord : dictionary){ // o(D)
            // freq hashmap
            Map<Character, Integer> freq = new HashMap<>();

            for(Character c: dictWord.toCharArray()){ //O(c) // ClogC if sorted
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }

            // check for anagram
            for(Character c: target.toCharArray()){ // O(t)
                if(freq.containsKey(c)){
                    if(freq.get(c) == 1){
                        freq.remove(c);
                    }
                    else {
                        freq.put(c, freq.get(c) - 1);
                    }
                }
                else {
                    break;
                }
            }

            if(freq.isEmpty()){
                ans.add(dictWord);
            }

        }
        return ans;
    }

    // -------- PART 2 --------
    /**
     * - Sort the dict words
     * - Search the sorted word against dict to find list of anagrams
     * - create a map where sortedHashed word is key and list of anagrams is the value
     * **/
    static class AnagramIndex {

        Map<String, List<String>> sortedHashMap;
        public AnagramIndex(List<String> dictionary) {
            Objects.requireNonNull(dictionary, "Given dict is null"); // Use simple null checker... if in springboot framework could use @Nonnull

            this.sortedHashMap = new HashMap<>();

            for(String word : dictionary){
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                String sortedHash = String.copyValueOf(chars);

                if(!this.sortedHashMap.containsKey(sortedHash)){
                    List<String> anagrams = findAnagrams(sortedHash, dictionary); // uses now realised non-optimal part 1 algo
                    sortedHashMap.put(sortedHash, anagrams);
                }
            }

        }

        public List<String> query(String target) {
            Objects.requireNonNull(target, "Given target param must not be null");
            char[] word = target.toCharArray();
            Arrays.sort(word);
            return this.sortedHashMap.getOrDefault(String.copyValueOf(word), new ArrayList<>());
        }
    }

    // -------- PART 3 --------

    /**
     * Improving on part 1's approach
     * - lets create a 1 time query(target) freq map
     * - iterate over the dict words
     *      - check dictWord len to query len ... early exit
     *      - create freq of them
     * - eval each dict word freq map vs query freqmap and allow for *.len amount of mismatches
     *
     * **/
    public static List<String> findWildcardAnagrams(String query, List<String> dictionary) {
        Objects.requireNonNull(query, "Query param cant be null");
        Objects.requireNonNull(dictionary, "Dict param cant be null");
        List<String> ans = new ArrayList<>();

        // query freqmap
        Map<Character, Integer> queryFreq = new HashMap<>();
        for(Character c: query.toCharArray()){
            queryFreq.put(c, queryFreq.getOrDefault(c, 0) + 1);
        }

        // iterate thru dict words and eval
        for(String word: dictionary){
            if(word.length() == query.length()) {
                Map<Character, Integer> dictFreq = new HashMap<>();
                for(Character c: word.toCharArray()){
                    dictFreq.put(c, dictFreq.getOrDefault(c, 0) + 1);
                }

                // evaluate against query freq
                for(Character key: queryFreq.keySet()){
                    if(dictFreq.containsKey(key)){
                        if(dictFreq.get(key).equals(queryFreq.get(key))){
                            dictFreq.remove(key);
                        }
                        else {
                            // subtracting the query char freq from the dict one.... allows the wildcard to represent a char already in query
                            dictFreq.put(key, dictFreq.get(key) - queryFreq.get(key));
                        }
                    }
                }

                // eval counts left in dictFreqMap
                if(dictionary.isEmpty()){
                    ans.add(word);
                }
                else {
                    // gather remaining counts
                    int charsLeft = 0;
                    for(int count: dictFreq.values()){
                        charsLeft += count;
                    }

                    if(queryFreq.containsKey('*')){
                        if (queryFreq.get('*').equals(charsLeft)){
                            ans.add(word);
                        }
                    }
                }
            }

        }

        return ans;
    }

    // ------------------ TEST CASES ------------------
    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList(
                "enlist", "google", "inlets", "banana", "silent", "stone", "tones", "notes"
        );

        // Part 1
        System.out.println(findAnagrams("listen", dictionary));
        // Expected: [enlist, inlets, silent]

        // Part 2
        AnagramIndex index = new AnagramIndex(dictionary);
        System.out.println("listen -> " + index.query("listen"));
        System.out.println("stone -> " + index.query("stone"));
        // Expected: listen -> [enlist, inlets, silent]
        //           stone -> [stone, tones, notes]

        // Bryans Test
        // No anagram returns empty list
        System.out.println("bryan -> " + index.query("bryan"));

        // Null checks
        System.out.println("Part 2 null checks");
        try{
            index = new AnagramIndex(null);
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            // Expected null check log output
        }

        try {
            System.out.println("bryan -> " + index.query(null));
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            // Expected null check log output
        }


        // Part 3
        System.out.println("PART 3");
        dictionary = Arrays.asList(
                "enlist", "google", "inlets", "banana", "silent", "stone", "notes"
        );
        System.out.println(findWildcardAnagrams("li*ten", dictionary));
        // Expected: [silent]

        System.out.println(findWildcardAnagrams("sto*e", dictionary));
        // Expected: [stone]

        System.out.println(findWildcardAnagrams("******", dictionary));
        // Expected: all 6-letter words in dictionary

        System.out.println(findWildcardAnagrams("goo***", dictionary));
        // Expected: [google]

        // Bryans Tests
        System.out.println("BRYANS TESTS \n");
        // testing the queryFreq - targetFreq counting as wildcard represents diff chars
        System.out.println(findWildcardAnagrams("go**le", dictionary));
        // Expected: [google]

        // Empty list check
        System.out.println(findWildcardAnagrams("bryan", dictionary));
        // Expected: []

        // Empty dict check
        System.out.println(findWildcardAnagrams("bryan", new ArrayList<>()));
        // Expected: []

        // Check for nulls
        try{
            System.out.println(findWildcardAnagrams("goo***", null));
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            // Expected null check log output
        }

        try{
            System.out.println(findWildcardAnagrams(null, dictionary));
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            // Expected null check log output
        }

    }
}