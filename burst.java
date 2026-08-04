class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        // Create new array with virtual balloons
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        // Length of interval
        for (int len = 2; len <= n + 1; len++) {
            for (int left = 0; left + len <= n + 1; left++) {
                int right = left + len;

                for (int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.max(
                        dp[left][right],
                        dp[left][k]
                        + dp[k][right]
                        + arr[left] * arr[k] * arr[right]
                    );
                }
            }
        }

        return dp[0][n + 1];
    }
}
