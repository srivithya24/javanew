class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        
        if (n > m) return false;
        
        int[] s1Count = new int[26];
        int[] windowCount = new int[26];
        
        // Initialize counts for s1 and the first window of s2
        for (int i = 0; i < n; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            windowCount[s2.charAt(i) - 'a']++;
        }
        
        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] == windowCount[i]) matches++;
        }
        
        if (matches == 26) return true;
        
        // Slide the window across the rest of s2
        for (int i = n; i < m; i++) {
            int addChar = s2.charAt(i) - 'a';
            int removeChar = s2.charAt(i - n) - 'a';
            
            // Add new character to window
            if (s1Count[addChar] == windowCount[addChar]) matches--;
            windowCount[addChar]++;
            if (s1Count[addChar] == windowCount[addChar]) matches++;
            
            // Remove character leaving the window
            if (s1Count[removeChar] == windowCount[removeChar]) matches--;
            windowCount[removeChar]--;
            if (s1Count[removeChar] == windowCount[removeChar]) matches++;
            
            if (matches == 26) return true;
        }
        
        return false;
    }
}
