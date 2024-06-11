package problems;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserFilter {
    public static void main(String[] args) {
        List<User2> users = Arrays.asList(
                new User2(1, "John Doe", "Admin"),
                new User2(2, "Jane Smith", "User2"),
                new User2(3, "Oliver Twist", "Admin"),
                new User2(4, "Nancy Drew", "User2"),
                new User2(5, "Harry Potter", "Admin"),
                new User2(6, "Hermione Granger", "User2"),
                new User2(7, "Ron Weasley", "Admin")
        );
        List<String> names = users.stream()
                .filter(e-> e.getRole().equals("User2"))
                .map(e-> e.getName())
                .collect(Collectors.toList());
        System.out.println(names);

        Map<Integer, String> map = users.stream()
                .filter(e -> e.getRole().equals("Admin"))
                .collect(Collectors.toMap(User2::getId, User2::getName));
        System.out.println(map);


    }
}

class User2{
    private int id;
    private String name;
    private String role;

    public User2(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
