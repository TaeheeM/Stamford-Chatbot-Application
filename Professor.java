

public class Professor {
    private User user;

    public Professor(String name, String role, String password, String id) {
        this.user = new User(name, role, id, password, "");
    }

   
    public String getName() {
        return this.user.getName();
    }

    public String getRole() {
        return this.user.getRole();
    }
}
