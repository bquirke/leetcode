import java.util.List;
import java.util.stream.Collectors;

public class LongestPrefix {

    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonPrefix(new String[]{"c","acc","ccc"}));
    }

    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 1) return strs[0];
            String longSub = "";
            for(int i = 1; i <= strs[0].length(); i++){
                String toCheck = strs[0].substring(0,i);
                for(int j = 0; j < strs.length; j++){
                    if(!strs[j].startsWith(toCheck)){
                        return longSub;
                    }
                }
                longSub = toCheck;
            }

            return longSub;
        }
    }
}
