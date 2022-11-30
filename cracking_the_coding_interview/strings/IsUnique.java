package cracking_the_coding_interview.strings;


import java.util.Arrays;

/*
* Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
* cannot use additional data structures?
* */
public class IsUnique {

    /*
    * Runtime O(nlogn)
    * Space complexity O(1)
    * */
    public static boolean isUnique(String str) {
        var arr = str.toCharArray();
        Arrays.sort(arr); // O(nlogn)
        for (int i = 0; i < arr.length; i++) { // O(n)
            char k = arr[i];
            int result = binarySearch0(arr, i + 1, arr.length, k); // O(logn)
            if (result >= 1) return false;
        }
        return true;
    }

    private static int binarySearch0(char[] a, int fromIndex, int toIndex, char key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            char midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static void main(String[] args) {
        System.out.println(isUnique("abedvoco"));
    }
}
