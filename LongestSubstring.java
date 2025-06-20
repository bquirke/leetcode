import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestSubstring {

    public static void main(String[] args) {
        new Solution().lengthOfLongestSubstring("abcabcbb");
    }

    static class Solution {
        public int lengthOfLongestSubstring(String s) {

            List<Character> chars = s.chars().mapToObj(e ->(char)e).collect(Collectors.toList());
            Set<String> subStrings = new HashSet<String>();

            for(Character c: chars){

            }
            return 0;
        }
    }
}
