package cracking_the_coding_interview.linked_list.remove_dupl;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    private static List<String> removeDuplicates(LinkedList<String> list) {
        var set = new LinkedList<String>();
        for (String s : list)
            if (!set.contains(s))
                set.add(s);

        return set;
    }
    public static void main(String[] args) {
        var list = new LinkedList<String>();
        list.add("5");
        list.add("2");
        list.add("5");
        list.add("2");
        list.add("7");
        list.add("1");
        list.add("2");
        list.add("5");
        list.add("0");
        var list1 = removeDuplicates(list);
//        list.forEach(System.out::println);
        list1.forEach(System.out::println);
    }
}
