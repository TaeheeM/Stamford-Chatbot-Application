public class User {
    private String name;
    private String role;
    private String password;
    private String id;
    private String schedule;

    public User(String name, String role, String id, String password, String schedule) {
        this.name = name;
        this.role = role;
        this.password = password;
        this.id = id;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
