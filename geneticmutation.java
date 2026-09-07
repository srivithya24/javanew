import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {

        // Convert bank to Set for fast checking
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // If endGene is not in bank, mutation is impossible
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(startGene);
        visited.add(startGene);

        char[] genes = {'A', 'C', 'G', 'T'};
        int mutations = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process all genes at current mutation level
            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                // If target is reached
                if (current.equals(endGene)) {
                    return mutations;
                }

                char[] chars = current.toCharArray();

                // Change one character at a time
                for (int j = 0; j < chars.length; j++) {

                    char original = chars[j];

                    for (char gene : genes) {

                        if (gene == original)
                            continue;

                        chars[j] = gene;

                        String next = new String(chars);

                        // Mutation must be present in bank
                        if (bankSet.contains(next) && !visited.contains(next)) {

                            visited.add(next);
                            queue.offer(next);
                        }
                    }

                    // Restore original character
                    chars[j] = original;
                }
            }

            mutations++;
        }

        return -1;
    }
}
