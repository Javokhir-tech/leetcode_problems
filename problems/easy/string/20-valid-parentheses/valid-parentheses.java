class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        String[] chars = s.split("");
        ArrayList<String> stack = new ArrayList<>();
        List<String> opening = List.of("(", "[", "{");
        List<String> closing = List.of(")", "]", "}");
        
        for (String ch : chars) {
            if (opening.contains(ch)) {
                stack.add(ch);
            }
            else if (closing.contains(ch)) {
                String o = (stack.size() == 0) ? "" : stack.get(stack.size() - 1);
                String parentheses = o + ch;
                if (parentheses.equals("{}") || parentheses.equals("[]") || parentheses.equals("()")) {
                    stack.remove(stack.size() - 1);
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }
}