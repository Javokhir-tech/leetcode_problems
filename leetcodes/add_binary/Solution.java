package leetcodes.add_binary;

class Solution {
    public static String addBinary(String a, String b) {
        int index_a = a.length() - 1, index_b = b.length() - 1, carry = 0;
        var result = new StringBuilder();
        while (index_a >= 0 || index_b >= 0) {
            int sum = carry; // setting value of carry to sum. e.g. when 1+1=10 getting 1
            if (index_a >= 0)
                sum += a.charAt(index_a--) - '0'; // iterating over strings by subtracting '0' converting char to int
            if (index_b >= 0) sum += b.charAt(index_b--) - '0'; // then adding them to common var sum
            carry = sum / 2;    // calculating carry by dividing sum / 2 and getting whole part
            result.append(sum % 2); // calculating remainder then adding to result
        }
        if (carry != 0) result.append(carry); // add carry if 1
        return result.reverse().toString(); // reverse result
    }

    public static void main(String[] args) {
        /*
        * Add two binaries, then receive result in binary
        */
        System.out.println(addBinary("10", "111"));
    }
}
