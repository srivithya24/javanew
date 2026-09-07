import java.util.*;

class Solution {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int m = key.length();
        
        // Map each character to all positions where it appears in ring
        Map<Character, List<Integer>> charPositions = new HashMap<>();
        for (int i = 0; i < n; i++) {
            charPositions.computeIfAbsent(ring.charAt(i), x -> new ArrayList<>()).add(i);
        }
        
        // dp: map from ring-position -> min steps to reach that position 
        // after spelling the characters processed so far
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0); // start at position 0 (12:00), 0 steps so far
        
        for (int i = 0; i < m; i++) {
            char c = key.charAt(i);
            List<Integer> positions = charPositions.get(c);
            Map<Integer, Integer> newDp = new HashMap<>();
            
            for (int p1 : positions) {
                int best = Integer.MAX_VALUE;
                for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                    int p0 = entry.getKey();
                    int prevSteps = entry.getValue();
                    int diff = Math.abs(p0 - p1);
                    int rotateSteps = Math.min(diff, n - diff);
                    int total = prevSteps + rotateSteps + 1; // +1 for button press
                    best = Math.min(best, total);
                }
                newDp.put(p1, best);
            }
            
            dp = newDp;
        }
        
        int result = Integer.MAX_VALUE;
        for (int steps : dp.values()) {
            result = Math.min(result, steps);
        }
        return result;
    }
}
