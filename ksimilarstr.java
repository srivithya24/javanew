import java.util.*;

class Solution {

    public int kSimilarity(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s1);
        visited.add(s1);

        int swaps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int x = 0; x < size; x++) {
                String current = queue.poll();

                // Find the first position where current != s2
                int i = 0;
                while (current.charAt(i) == s2.charAt(i)) {
                    i++;
                }

                // Try swapping i with a suitable position j
                for (int j = i + 1; j < current.length(); j++) {

                    // The character at j must be what s2 needs at i
                    // Also avoid unnecessary swaps
                    if (current.charAt(j) == s2.charAt(i)
                            && current.charAt(j) != s2.charAt(j)) {

                        char[] chars = current.toCharArray();

                        // Swap
                        char temp = chars[i];
                        chars[i] = chars[j];
                        chars[j] = temp;

                        String next = new String(chars);

                        if (next.equals(s2)) {
                            return swaps + 1;
                        }

                        if (!visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                }
            }

            swaps++;
        }

        return -1;
    }
}
