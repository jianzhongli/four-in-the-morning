package fitm.ajax;

import fitm.model.Assistant;
import fitm.model.Class;
import fitm.model.User;
import fitm.util.SQLHelper;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

// 此 Servlet 专门用来处理 TA 的指定与删除，教学班信息的更改在其他 Servlet
public class AjaxAssistantServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        if (user != null) {
            if (user.isTeacher()) {
                Assistant ta = Utils.getGson().fromJson(Utils.getRequestBody(req), Assistant.class);
                if (Class.assignAssistantToClasses(ta.getId(), ta.getClass_list())) {
                    response = new Success(null);
                } else {
                    response = new Failure("数据库错误。");
                }
            } else {
                response = new Failure("权限不足。");
            }
        } else {
            response = new Failure("用户未登录。");
        }

        writer.write(Utils.getGson().toJson(response));
    }
}
