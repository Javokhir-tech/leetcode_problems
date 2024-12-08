class Solution {
/*
    public int numberOfSteps(int num) {
        int steps = 0;
        while (num > 0) {
            if ((num & 1) == 0) { // and operator, compare last digits
                num >>= 1; // shift by one to right, in other words, remove 1 digit from right
            } else {
                num--;
            }
            steps++;
        }
        return steps;   
    }
*/
    public int numberOfSteps(int num) {
        int steps = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num--;
            }
            steps++;
        }
        return steps;
        
    }
}