import java.util.*;

class Solution {
    public int longestStrChain(String[] words) {
        
        // Sort words based on length
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        // dp[word] = longest chain ending with this word
        HashMap<String, Integer> dp = new HashMap<>();

        int answer = 1;

        for (String word : words) {
            int best = 1;

            // Remove one character from the word
            for (int i = 0; i < word.length(); i++) {
                
                String predecessor = word.substring(0, i) 
                                   + word.substring(i + 1);

                // Check if predecessor exists
                if (dp.containsKey(predecessor)) {
                    best = Math.max(best, dp.get(predecessor) + 1);
                }
            }

            dp.put(word, best);

            answer = Math.max(answer, best);
        }

        return answer;
    }
}
