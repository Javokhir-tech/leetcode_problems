package cracking_the_coding_interview.strings.string_compression;

public class Solution {

    private static String stringCompression(String str) {
        var sb = new StringBuilder();
        var counter = 0;
        int k = 0;
        for (int c = k; c < str.length(); c++) {
            if (c != k)
                continue;
            var s = str.charAt(c); // check if element already added
            for (int i = c + 1; i < str.length(); i++) {
                if (s == str.charAt(i)) {
                    counter++;
                }
                else {
                    k = i;
                    break;
                }
            }
            sb.append(s).append(counter + 1);
            counter = 0;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(stringCompression("aabcccccaaakbbxayyyy"));
    }
}
