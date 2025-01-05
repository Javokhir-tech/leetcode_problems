public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append("#").append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int curr = 0;
        while (curr < s.length()) {
            int delimeterIdx = s.indexOf("#", curr);
            int strLength = Integer.parseInt(s.substring(curr, delimeterIdx));
            curr = delimeterIdx + 1; // step over delimeter
            res.add(s.substring(curr, curr + strLength));
            curr += strLength;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));