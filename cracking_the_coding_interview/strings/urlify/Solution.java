package cracking_the_coding_interview.strings.urlify;

/*
* replace spaces with %20
* */
public class Solution {

    private static String URLify(String str) {
        var arr = str.split(" ");
        return String.join("%20", arr);
    }

    public static void main(String[] args) {
        System.out.println(URLify("Mr John Smith   "));
    }
}
