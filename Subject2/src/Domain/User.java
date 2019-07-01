package Domain;

/**
 * @author adi
 */
public class User {
    private int userId;
    private String name;
    private int role;

    public User(int userId, String name, int role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Domain.User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
