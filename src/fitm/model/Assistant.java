package fitm.model;

public class Assistant extends Student {

    public Assistant(String id, String name, int userType) {
        super(id, name, userType);
    }

    public Assistant(User user) {
        super(user);
    }
}
