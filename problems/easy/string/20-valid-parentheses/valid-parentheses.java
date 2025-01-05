class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> parentheses = new HashMap<>();
        parentheses.put('{', '}');
        parentheses.put('[', ']');
        parentheses.put('(', ')');

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (parentheses.containsKey(ch)) {
                stack.push(parentheses.get(ch));
            }
            if (parentheses.containsValue(ch)) {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}