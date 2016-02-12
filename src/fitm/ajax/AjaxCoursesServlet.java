package fitm.ajax;

import fitm.model.Course;
import fitm.util.SQLHelper;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AjaxCoursesServlet extends HttpServlet {
    SQLHelper helper;

    @Override
    public void init() throws ServletException {
        helper = SQLHelper.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Response response;
        String courseid = req.getParameter(Tags.TAG_COURSEID);

        if (Utils.hasLogin(req)) {
            HttpSession session = req.getSession();
            String userid  = session.getAttribute(Tags.TAG_USERID).toString();
            String userType = session.getAttribute(Tags.TAG_USERTYPE).toString();
            if(courseid == null) {
                ArrayList<Course> courseArrayList = Course.getCoursesList(userid);
                response = new Success(courseArrayList);
            } else {
                Course course = Course.getCourseDetail(courseid, userid, userType);
                response = new Success(course);
            }
        } else {
            response = new Failure("用户未登录。");
        }
        writer.write(Utils.getGson().toJson(response));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
