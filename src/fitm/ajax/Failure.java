package fitm.ajax;

public class Failure extends Response {
    private String msg;

    Failure(String msg) {
        this.success = 0;
        this.msg = msg;
    }
}
