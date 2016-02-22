package fitm.model;

import java.util.ArrayList;

public class Assistant extends Student {
    private ArrayList<String> class_list;

    public Assistant(String id, String name) {
        super(id, name);
    }

    public Assistant(String id, String name, ArrayList<String> class_list) {
        super(id, name);
        this.class_list = class_list;
    }

    public Assistant(User user, ArrayList<String> class_list) {
        super(user);
        this.class_list = class_list;
    }

    public Assistant(User user) {
        super(user);
    }

    public ArrayList<String> getClass_list() {
        return class_list;
    }
}
