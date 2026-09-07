class Solution {
    public int numTilePossibilities(String tiles) {
        int[] freq = new int[26];

        // Count frequency of each letter
        for (char c : tiles.toCharArray()) {
            freq[c - 'A']++;
        }

        return backtrack(freq);
    }

    private int backtrack(int[] freq) {
        int count = 0;

        for (int i = 0; i < 26; i++) {

            // If this letter is available
            if (freq[i] > 0) {

                // Use this letter
                freq[i]--;

                // Current sequence is one valid sequence
                count++;

                // Try adding more letters
                count += backtrack(freq);

                // Restore the letter
                freq[i]++;
            }
        }

        return count;
    }
}
