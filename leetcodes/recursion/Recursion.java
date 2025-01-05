package leetcodes.recursion;

public class Recursion {

    static int sum(int[] arr) {
        if (arr.length == 1) return arr[0];

        int[] new_arr = new int[arr.length - 1];
        System.arraycopy(arr, 1, new_arr, 0, arr.length - 1);
        return arr[0] + sum(new_arr);
    }

    public static void main(String[] args) {
        int sum = sum(new int[] {2, 3, 4, 5, 10, 5});
        System.out.println("sum: " + sum);
    }
}
