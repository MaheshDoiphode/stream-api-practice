package problems;
import java.util.*;
import java.util.stream.Collectors;
public class OrderFilter {
    public static void main(String[] args){
        List<User3> users = Arrays.asList(
                new User3(1, "John Doe"),
                new User3(2, "Jane Smith"),
                new User3(3, "Oliver Twist")
        );
        List<Order> orders = Arrays.asList(
                new Order(1, 1, 100.0),
                new Order(2, 1, 200.0),
                new Order(3, 2, 300.0),
                new Order(4, 2, 400.0),
                new Order(5, 3, 500.0)
        );
        // Calculate the total order amount for each user.
        Map<Integer, Double> amounts = orders.stream()
                .collect(Collectors.groupingBy(Order::getUserId, Collectors.summingDouble(Order::getAmount)));
        System.out.println(amounts);
        Map<String, Double> amounts2 = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> users.stream()
                                .filter(e-> e.getId() == order.getUserId())
                                .findFirst().get().getName(),
                        Collectors.summingDouble(Order::getAmount)
                ));
        System.out.println(amounts2);
    }//-main
}
class User3{
private int id;
    private String name;

    public User3(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}

class Order{
    private int id;
    private int userId;
    private double amount;

    public Order(int id, int userId, double amount) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }
}