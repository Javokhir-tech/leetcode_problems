package leetcodes.recursion;

public class Recursion {

    static int sum(int[] arr, int len) {
        if (len <= 0) return 0;
        return arr[len - 1] + sum(arr, len - 1);
    }

    static int max(int[] arr, int len) {
        if (len == 1) return arr[0];
        return Math.max(arr[len - 1], max(arr, len - 1));
    }

    public static void main(String[] args) {
        int [] arr = {7, 2, 3, 8, 6, 4, 5};
        int sum = sum(arr, arr.length);
        int max = max(arr, arr.length);
        System.out.println("sum: " + sum + ", max: " + max);
    }
}
