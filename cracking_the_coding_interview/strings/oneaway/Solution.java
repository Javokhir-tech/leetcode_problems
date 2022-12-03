package cracking_the_coding_interview.strings.oneaway;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  // runtime O(2a + b), space O(a + b)
  private static boolean oneAway(String a, String b) {
    var len = a.length() - b.length();
    if (len > 1 || len < -1) return false;

    var mapA = countLetters(a);
    var mapB = countLetters(b);

    var edit = 0;
    for (var entry : mapA.entrySet()) {  // O(a)
      if (mapB.containsKey(entry.getKey())) {
        var diff = mapB.get(entry.getKey()) - entry.getValue();
        if (diff < -1 || diff > 1) return false;
        else if (diff == 1 || diff == -1) edit++;
      }
      else {
        if (entry.getValue() != 1) return false;
        edit++;
      }
    }

    return edit >=0 && edit <= 1;
  }

  private static Map<Character, Integer> countLetters(String str) {
    var map = new HashMap<Character, Integer>(); // O(n) space

    for (char c : str.toCharArray()) { // O(str)
      map.put(c, map.get(c) == null ? 1 : map.get(c) + 1);
    }
    return map;
  }

  public static void main(String[] args) {
    System.out.println(oneAway("pales", "pabla"));
  }
}
