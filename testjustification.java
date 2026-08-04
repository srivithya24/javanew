class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i;
            int lineLength = 0;

            // Find how many words fit in the current line
            while (j < words.length &&
                   lineLength + words[j].length() + (j - i) <= maxWidth) {
                lineLength += words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder sb = new StringBuilder();

            // Last line or single word -> left justify
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k != j - 1)
                        sb.append(" ");
                }

                while (sb.length() < maxWidth)
                    sb.append(" ");
            } else {
                // Fully justify
                int totalSpaces = maxWidth - lineLength;
                int spaceEach = totalSpaces / gaps;
                int extra = totalSpaces % gaps;

                for (int k = i; k < j - 1; k++) {
                    sb.append(words[k]);

                    for (int s = 0; s < spaceEach; s++)
                        sb.append(" ");

                    if (extra > 0) {
                        sb.append(" ");
                        extra--;
                    }
                }

                sb.append(words[j - 1]);
            }

            result.add(sb.toString());
            i = j;
        }

        return result;
    }
}
