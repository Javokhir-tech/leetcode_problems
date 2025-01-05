class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>(); 

        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            int currentCount = map.getOrDefault(c, 0);

            if (currentCount == 0) {
                return false;
            }
            map.put(c, currentCount - 1);
        }
        return true;
    }
}