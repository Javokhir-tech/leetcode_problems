package cracking_the_coding_interview.strings.unique;


import java.util.HashSet;

/*
 * Is Unique: Implement an algorithm to determine if a string has all unique characters.
 * */
public class IsUnique2 {

    /*
    * Runtime O(n)
    * Space complexity O(n)
    * */
    public static boolean isUnique2(String str) {
        var set = new HashSet<Character>();
        for (char c : str.toCharArray()) {
            if (set.contains(c))
                return false;
            set.add(c);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUnique2("abedvoco"));
    }
}