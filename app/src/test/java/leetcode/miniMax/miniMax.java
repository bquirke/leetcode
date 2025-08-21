//package app.src.test.java.leetcode.miniMax;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Stream;
//
//import static java.util.stream.Collectors.toList;
//
//class Result {
//
//    /*
//     * Complete the 'miniMaxSum' function below.
//     *
//     * The function accepts INTEGER_ARRAY arr as parameter.
//     */
//
//    public static void miniMaxSum(List<Integer> arr) {
//        // Write your code here
//
//        List<Integer> mins = Collections.sort(arr, Collections.reverseOrder());
//        for(Integer i: arr){
//
//
//        }
//
//    }
//
//}
//
//public class app.src.test.java.leetcode.miniMax {
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//        {
//            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                    .map(Integer::parseInt)
//                    .collect(toList());
//
//
//            Result.miniMaxSum(arr);
//        }
//
//        bufferedReader.close();
//    }
//}
