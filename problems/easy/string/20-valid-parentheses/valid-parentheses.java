class Solution {

    private static final Map<Character, Character> maps = 
    Map.of('}', '{', 
           ']', '[', 
           ')', '(');    

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (maps.containsValue(ch)) { // if opening
                stack.push(ch);
            }
            else {
                if (stack.isEmpty() || stack.pop() != maps.get(ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}