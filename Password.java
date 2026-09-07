class Solution {
    public int strongPasswordChecker(String password) {

        int n = password.length();

        // Check required character types
        boolean lower = false;
        boolean upper = false;
        boolean digit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c))
                lower = true;
            else if (Character.isUpperCase(c))
                upper = true;
            else if (Character.isDigit(c))
                digit = true;
        }

        int missing = 0;

        if (!lower) missing++;
        if (!upper) missing++;
        if (!digit) missing++;

        // Find repeating sequences
        int replace = 0;

        // Number of groups where length % 3 == 0
        int mod0 = 0;

        // Number of groups where length % 3 == 1
        int mod1 = 0;

        for (int i = 0; i < n; ) {

            int j = i;

            while (j < n && password.charAt(j) == password.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (len >= 3) {
                replace += len / 3;

                if (len % 3 == 0) {
                    mod0++;
                } 
                else if (len % 3 == 1) {
                    mod1++;
                }
            }

            i = j;
        }

        // Case 1: Password is too short
        if (n < 6) {
            return Math.max(missing, 6 - n);
        }

        // Case 2: Password length is valid
        if (n <= 20) {
            return Math.max(missing, replace);
        }

        // Case 3: Password is too long
        int delete = n - 20;

        /*
         * First delete from groups where len % 3 == 0.
         * One deletion reduces one replacement.
         */
        int use = Math.min(delete, mod0);

        replace -= use;
        delete -= use;

        /*
         * Next delete 2 characters from groups where
         * len % 3 == 1.
         *
         * Every 2 deletions reduce one replacement.
         */
        use = Math.min(delete / 2, mod1);

        replace -= use;
        delete -= use * 2;

        /*
         * Remaining deletions:
         * Every 3 deletions reduce one replacement.
         */
        replace -= delete / 3;

        // Total deletions + remaining required changes
        return (n - 20) + Math.max(missing, replace);
    }
}
