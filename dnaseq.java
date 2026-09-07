import java.util.*;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {

        HashMap<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();

        // If length is less than 10, no valid sequence exists
        if (s.length() < 10) {
            return result;
        }

        // Check every 10-letter substring
        for (int i = 0; i <= s.length() - 10; i++) {

            String sequence = s.substring(i, i + 10);

            map.put(sequence, map.getOrDefault(sequence, 0) + 1);
        }

        // Find sequences occurring more than once
        for (String sequence : map.keySet()) {
            if (map.get(sequence) > 1) {
                result.add(sequence);
            }
        }

        return result;
    }
}
