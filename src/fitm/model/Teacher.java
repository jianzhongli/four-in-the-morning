package fitm.model;

public class Teacher extends User {
    public Teacher(String id, String name, int userType) {
        super(id, name, userType);
    }

    public Teacher(User user) {
        super(user);
    }
}
