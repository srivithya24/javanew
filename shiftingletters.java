class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        char[] chars = s.toCharArray();

        long totalShift = 0;

        // Process from right to left
        for (int i = shifts.length - 1; i >= 0; i--) {
            totalShift = (totalShift + shifts[i]) % 26;

            int current = chars[i] - 'a';
            int shifted = (int) ((current + totalShift) % 26);

            chars[i] = (char) ('a' + shifted);
        }

        return new String(chars);
    }
}
