class Solution {

    public int superpalindromesInRange(String left, String right) {
        long L = Long.parseLong(left);
        long R = Long.parseLong(right);

        int count = 0;

        // Generate palindromic roots with length 1 to 9
        for (int len = 1; len <= 9; len++) {

            int halfLen = (len + 1) / 2;
            int start = (int) Math.pow(10, halfLen - 1);
            int end = (int) Math.pow(10, halfLen);

            // For length 1, include 0
            if (len == 1) {
                start = 1;
            }

            for (int half = start; half < end; half++) {

                String first = String.valueOf(half);
                StringBuilder sb = new StringBuilder(first);

                // Create palindrome
                int startIndex;

                if (len % 2 == 0) {
                    startIndex = first.length() - 1;
                } else {
                    startIndex = first.length() - 2;
                }

                for (int i = startIndex; i >= 0; i--) {
                    sb.append(first.charAt(i));
                }

                long root = Long.parseLong(sb.toString());
                long square = root * root;

                // Check if square is inside [L, R]
                if (square < L) {
                    continue;
                }

                if (square > R) {
                    break;
                }

                // Check if square is palindrome
                if (isPalindrome(square)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isPalindrome(long num) {
        long original = num;
        long reversed = 0;

        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }

        return original == reversed;
    }
}
