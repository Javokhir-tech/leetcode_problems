class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int l = 0, r = 1, res = 1;
        String prev = "";
        while (r < arr.length) {
            if (arr[r - 1] > arr[r] && !">".equals(prev)) {
                res = Math.max(res, r - l + 1);
                r++;
                prev = ">";
            } else if (arr[r - 1] < arr[r] && !"<".equals(prev)) {
                res = Math.max(res, r - l + 1);
                r++;
                prev = "<";
            } else {
                r = (arr[r] == arr[r - 1]) ? r + 1 : r;
                l = r - 1;
                prev = "";
            }
        }
        return res;
    }
}
// [9 > 4 > 2 < 10 > 7 < 8 = 8 > 1 < 9]
//      |                |.   