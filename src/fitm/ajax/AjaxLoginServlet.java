package fitm.ajax;

import fitm.model.Course;
import fitm.model.User;
import fitm.util.SQLHelper;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjaxLoginServlet extends HttpServlet {
    SQLHelper helper;

    @Override
    public void init() throws ServletException {
        helper = SQLHelper.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        Response response;

        String userid = req.getParameter(Tags.TAG_USERID);
        String passwd = req.getParameter(Tags.TAG_PASSWORD);
        User user = User.validate(userid, passwd);

        if (user != null) {
            session.setAttribute(Tags.TAG_USER, user);
            response = new Success(null);
        } else {
            response = new Failure("用户名或密码错误");
        }
        writer.write(Utils.getGson().toJson(response));

        // 因为 getCoursesList(user) 和 getAssistantCoursesList(user) 的实现效率不高，所以在这里先把资源准备好
        // 这样登录之后点开 Courses 就显得快一点，否则载入时间需要三秒
        // TODO: 优化这两个方法。
        if (user != null) {
            session.setAttribute(Tags.TAG_COURSE_LIST, Course.getCoursesList(user));
            if (user.isAssistant()) {
                session.setAttribute(Tags.TAG_ASSISTANT_COURSE_LIST, Course.getAssistantCoursesList(user));
            }
        }
    }
}
