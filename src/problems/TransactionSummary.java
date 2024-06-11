package problems;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionSummary {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User(1, "John Doe", "USA"),
                new User(2, "Jane Smith", "Canada"),
                new User(3, "Oliver Twist", "UK")
        );

        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, "2023-01-01 10:00:00", 1, 100.0),
                new Transaction(2, "2023-01-02 11:00:00", 1, 200.0),
                new Transaction(3, "2022-01-03 12:00:00", 2, 300.0),
                new Transaction(4, "2023-01-04 13:00:00", 2, 400.0),
                new Transaction(5, "2023-01-05 14:00:00", 3, 500.0)
        );


        // 1. Filter out transactions that occurred in the year 2023.
//        transactions.stream()
//                .filter(e -> e.timestamp.startsWith("2022"))
//                .collect(Collectors.toList()).forEach(e -> System.out.println(e.id));
        transactions.stream()
                .filter(
                        t -> {
                            LocalDateTime localDate = LocalDateTime.parse(t.timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            return localDate.getYear() == 2023;
                        }
                ).collect(Collectors.toList())
                .forEach(e -> System.out.println(e.id));

        // 2. Group the transactions by user.
        System.out.println("--------------------------------");
        Map<Integer, List<Transaction>> trans = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getUserId));
        trans.forEach((e, f) -> {
                    System.out.println(e);
                    f.forEach(g -> {
                        System.out.println(g.getId());
                        System.out.println(g.amount);
                    });
                }
        );

        System.out.println("//3. For each user, calculate the total transaction amount and the number of transactions they made.\n");
        Map<Integer, Double> amountByUsers = trans.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().mapToDouble(Transaction::getAmount).sum()
                ));
        Map<Integer, Long> transByUsers = trans.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().count()
                ));
        amountByUsers.forEach((e, f) -> {
            System.out.print(e + "| ");
            System.out.print(transByUsers.get(e) + "| ");
            System.out.println(f);
        });

        System.out.println("//4. Sort the users by the total transaction amount in descending order.");
        List<Map.Entry<Integer, Double>> sortedUsers = amountByUsers.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .collect(Collectors.toList());
        sortedUsers.forEach(e -> {
            System.out.println(e.getKey() + " | " + e.getValue());
        });

        System.out.println("//5. Transform the result into a list of UserTransactionSummary objects. ");
        List<UserTransactionSummary> summaries = users.stream()
                .map(u -> {
                    double totalAmount = amountByUsers.get(u.getId());
                    long totalTransactions = transByUsers.get(u.getId());
                    return new UserTransactionSummary(u.getName(), u.getCountry(), totalAmount, totalTransactions);
                }).collect(Collectors.toList());
        summaries.forEach(e ->
            System.out.println(e.name + " | " + e.getTotalAmount() + " | "
            + e.getTotalTransactions() + " | " + e.getCountry())
            );
    }//- main()
}

class UserTransactionSummary {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    // UserTransactionSummary should contain the user’s name, country, total transaction amount, and number of transactions.
    String name;

    public UserTransactionSummary(String name, String country, double totalAmount, long totalTransactions) {
        this.name = name;
        this.country = country;
        this.totalAmount = totalAmount;
        this.totalTransactions = totalTransactions;
    }

    String country;
    double totalAmount;
    long totalTransactions;

}

class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    int id;
    String name;
    String country;

    public User(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}

class Transaction {
    int id;
    String timestamp;
    int userId;
    double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Transaction(int id, String timestamp, int userId, double amount) {
        this.id = id;
        this.timestamp = timestamp;
        this.userId = userId;
        this.amount = amount;
    }
}
