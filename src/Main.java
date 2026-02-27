import java.util.*;

class Main {

    static HashMap<String, Integer> usernameMap = new HashMap<>();
    static HashMap<String, Integer> attemptCount = new HashMap<>();

    // Register user
    public static boolean registerUser(String username, int userId) {
        if (usernameMap.containsKey(username)) {
            return false;  // already taken
        }
        usernameMap.put(username, userId);
        return true;
    }

    // Check availability
    public static boolean checkAvailability(String username) {

        // Increase attempt count
        if (attemptCount.containsKey(username)) {
            attemptCount.put(username, attemptCount.get(username) + 1);
        } else {
            attemptCount.put(username, 1);
        }

        return !usernameMap.containsKey(username);
    }

    // Suggest alternatives
    public static List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String newName = username + i;
            if (!usernameMap.containsKey(newName)) {
                suggestions.add(newName);
            }
        }

        return suggestions;
    }

    // Get most attempted username
    public static String getMostAttempted() {
        String mostAttempted = "";
        int max = 0;

        for (String name : attemptCount.keySet()) {
            if (attemptCount.get(name) > max) {
                max = attemptCount.get(name);
                mostAttempted = name;
            }
        }

        return mostAttempted + " (" + max + " attempts)";
    }

    public static void main(String[] args) {

        registerUser("john_doe", 1);

        System.out.println(checkAvailability("john_doe"));   // false
        System.out.println(checkAvailability("jane_smith")); // true

        System.out.println(suggestAlternatives("john_doe"));
        System.out.println(getMostAttempted());
    }
}