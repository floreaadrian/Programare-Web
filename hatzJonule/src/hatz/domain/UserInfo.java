package hatz.domain;

/**
 * @author adi
 */
public class UserInfo {
    private int id;
    private String email;
    private String picture;
    private int age;
    private String home_town;
    private int userId;

    public UserInfo() {
    }

    public UserInfo(int id, String email, String picture, int age, String home_town, int userId) {
        this.id = id;
        this.email = email;
        this.picture = picture;
        this.age = age;
        this.home_town = home_town;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public int getAge() {
        return age;
    }

    public String getHome_town() {
        return home_town;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", age=" + age +
                ", home_town='" + home_town + '\'' +
                ", userId=" + userId +
                '}';
    }
}
