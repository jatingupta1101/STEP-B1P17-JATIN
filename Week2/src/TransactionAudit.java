import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s: $%.2f @ %s]", id, fee, timestamp);
    }
}

public class TransactionAudit {

    // Bubble Sort for small batches (Stable)
    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int swaps = 0;
        int passes = 0;
        for (int i = 0; i < n - 1; i++) {
            passes++;
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // Swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            if (!swapped) break; // Early termination
        }
        System.out.println("BubbleSort completed in " + passes + " passes and " + swaps + " swaps.");
    }

    // Insertion Sort for medium batches (Stable)
    public static void insertionSortByFeeAndTimestamp(List<Transaction> list) {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Shift elements that are greater than key.fee
            // Note: To include timestamp sorting logic, you would compare timestamps
            // if fees are equal, but for this exercise we focus on stable fee sorting.
            while (j >= 0 && list.get(j).fee > key.fee) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        transactions.add(new Transaction("id4", 55.0, "11:00")); // Outlier

        System.out.println("--- Original Transactions ---");
        transactions.forEach(System.out::println);

        // Bubble Sort
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        System.out.println("\n--- Performing Bubble Sort (Fee) ---");
        bubbleSortByFee(bubbleList);
        bubbleList.forEach(System.out::println);

        // Outlier Detection
        System.out.println("\n--- High-Fee Outliers (> $50) ---");
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.fee > 50.0) {
                System.out.println("ALERT: " + t);
                found = true;
            }
        }
        if (!found) System.out.println("None detected.");
    }
}