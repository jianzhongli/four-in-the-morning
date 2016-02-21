package fitm.ajax;

import fitm.model.Course;
import fitm.model.User;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
public class AjaxCourseDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        if (user != null) {
            String[] pathItems = req.getRequestURI().split("[/]");
            String courseId = pathItems[pathItems.length-1];
            Course course = Course.getCourseDetail(courseId, user);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        String[] pathItems = req.getRequestURI().split("[/]");
        String courseId = pathItems[pathItems.length-1];

        if (user != null) {
            if (user.isTeacher() || user.isAssistantOfCourse(courseId)) {
                Course course = Course.getCourseDetail(courseId, user);
                if (course != null) {
                    course.setIntro_text(req.getParameter(Tags.TAG_INTRO_TEXT));
                    System.out.println(course.getHtmlIntroText());
                    if (Course.updateCourseDetail(course)) {
                        response = new Success(course);
                    } else {
                        response = new Failure("数据库错误。");
                    }
                } else {
                    response = new Failure("课程不存在。");
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
