class Solution {

    private static final Map<Character, Character> maps = Map.of('{', '}', '[', ']', '(', ')');    

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (maps.containsKey(ch)) {
                stack.push(maps.get(ch));
            }
            else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}