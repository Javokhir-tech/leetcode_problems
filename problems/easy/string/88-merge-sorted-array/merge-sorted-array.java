class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, w = m + n - 1;
        while (w >= 0) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[w] = nums1[p1--];
            } else {
                nums1[w] = nums2[p2--];
            }
            w--;
        }
    }
}