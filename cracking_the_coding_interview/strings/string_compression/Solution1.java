package cracking_the_coding_interview.strings.string_compression;

public class Solution1 {

    private static String stringCompression(String str) {
        var sb = new StringBuilder();
        var p1 = str.charAt(0);
        var p2 = p1;
        var counter = 0;

        while (p2 == p1) {
            counter++;
            p2 = str.charAt(counter);
        }


        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(stringCompression("aabcccccaaakbbxayyyy"));

        // window sliding approach
        // 1st pointer select 1st element of arr
        // 2nd pointer iterate through rest of the arr by counting occurences till it reaches another char
        // move 1st pointer to where 2nd pointer reached
        // repeat process til second pointer reaches the end
    }
}
