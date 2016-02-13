package fitm.controller;

import fitm.ajax.Failure;
import fitm.ajax.Success;
import fitm.model.Course;
import fitm.model.HomeworkPost;
import fitm.model.User;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CourseDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getCurrentUser(req);
        if (user != null) {
            String[] pathItems = req.getRequestURI().split("[/]");
            String courseId = pathItems[pathItems.length-1];
            Course course = Course.getCourseDetail(courseId, Utils.getCurrentUser(req));
            ArrayList<HomeworkPost> homeworkPostArrayList = HomeworkPost.getHomeworkPost(courseId);
            if (course != null) {
                req.setAttribute(Tags.TAG_COURSE, course);
                req.setAttribute(Tags.TAG_HOMEWORK_LIST, homeworkPostArrayList);
                req.getRequestDispatcher(Path.COURSE_DETAIL).forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }

}
