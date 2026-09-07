class Solution {

    public String findReplaceString(String s, int[] indices,
                                     String[] sources, String[] targets) {

        int n = s.length();

        // Store the replacement operation for each index
        int[] replace = new int[n];

        // -1 means no replacement at this index
        for (int i = 0; i < n; i++) {
            replace[i] = -1;
        }

        // Check which replacements are valid
        for (int i = 0; i < indices.length; i++) {

            int index = indices[i];

            // Check whether source occurs at this index
            if (s.startsWith(sources[i], index)) {
                replace[index] = i;
            }
        }

        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < n) {

            // There is a valid replacement at index i
            if (replace[i] != -1) {

                int operation = replace[i];

                result.append(targets[operation]);

                // Skip the original source substring
                i += sources[operation].length();

            } else {

                // No replacement, keep original character
                result.append(s.charAt(i));

                i++;
            }
        }

        return result.toString();
    }
}
