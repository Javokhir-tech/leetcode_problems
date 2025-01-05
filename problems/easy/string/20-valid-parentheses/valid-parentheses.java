class Solution {

    private final Map<Character, Character> maps = 
    Map.of('{', '}', '[', ']', '(', ')');    

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (maps.containsKey(ch)) {
                stack.push(maps.get(ch));
            }
            if (maps.containsValue(ch)) {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}