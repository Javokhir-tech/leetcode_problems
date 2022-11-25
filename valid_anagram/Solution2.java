package valid_anagram;

import java.util.Arrays;

/*
* The same problem with sorting
* */
public class Solution2 {

    public static boolean isAnagram(String s, String t) {
        var arrS = Arrays.stream(s.split("")).sorted().toList();    // sorts string chars then compares
        var arrT = Arrays.stream(t.split("")).sorted().toList();
        return arrS.equals(arrT);
    }
}
