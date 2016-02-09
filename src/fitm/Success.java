package fitm;

public class Success extends Response {
    Object data;
    Success(Object data) {
        this.success = 1;
        this.data = data;
    }
}
