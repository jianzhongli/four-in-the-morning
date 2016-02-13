package fitm.controller;

import fitm.ajax.Failure;
import fitm.ajax.Success;
import fitm.model.Course;
import fitm.model.User;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CourseDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getCurrentUser(req);
        if (user != null) {
            String[] pathItems = req.getRequestURI().split("[/]");
            String courseId = pathItems[pathItems.length-1];
            Course course = Course.getCourseDetail(courseId, Utils.getCurrentUser(req));
            if (course != null) {
                req.setAttribute(Tags.TAG_COURSE, course);
                req.getRequestDispatcher(Path.COURSE_DETAIL).forward(req, resp);
            } else {
                resp.setStatus(404);
            }
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }

}
