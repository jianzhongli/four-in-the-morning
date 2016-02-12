package fitm.ajax;

import fitm.model.Course;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxCourseDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Response response;

        if (Utils.hasLogin(req)) {
            String[] pathItems = req.getRequestURI().split("[/]");
            String courseId = pathItems[pathItems.length-1];
            Course course = Course.getCourseDetail(courseId, Utils.getCurrentUser(req));
            if (course != null) {
                response = new Success(course);
            } else {
                response = new Failure("课程不存在。");
            }
        } else {
            response = new Failure("用户未登录。");
        }

        writer.write(Utils.getGson().toJson(response));
    }
}
