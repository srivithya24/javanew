class Solution {
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // If word2 is empty, delete all characters from word1
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        // If word1 is empty, delete all characters from word2
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {

                    // Characters are same
                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    // Delete from word1 OR delete from word2
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],
                        dp[i][j - 1]
                    );
                }
            }
        }

        return dp[m][n];
    }
}
