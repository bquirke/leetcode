public class Palindrome {

    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(12111));
    }
    static class Solution {
        public boolean isPalindrome(int x) {
            String str = Integer.toString(x);
            String reversed = new StringBuilder(str).reverse().toString();

            return str.equals(reversed);
        }
    }
}
