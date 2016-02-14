package fitm.model;

public class Admin extends User {
    public Admin(String id, String name, int userType) {
        super(id, name, userType);
    }

    public Admin(User user) {
        super(user);
    }
}
