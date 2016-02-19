package fitm.controller;

import fitm.model.Class;
import fitm.model.HomeworkPost;
import fitm.model.HomeworkSubmission;
import fitm.model.User;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class HomeworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        User user = Utils.getCurrentUser(req);

        if (user != null) {
            String[] pathItems = req.getRequestURI().split("[/]");
            String homework_id = pathItems[pathItems.length-1];
            HomeworkPost homeworkPost = HomeworkPost.getHomeworkPostById(homework_id);
            if (homeworkPost != null) {
                req.setAttribute(Tags.TAG_HOMEWORK_POST, homeworkPost);
                req.setAttribute(Tags.TAG_HOMEWORK_SUBMISSION_MAP, HomeworkSubmission.getSubmissionMapByHomeworkId(homework_id));
                ArrayList<Class> classArrayList;
                if (user.isTeacher()) {
                    classArrayList = Class.getClassesList(homeworkPost.getCourse_id(), user);
                } else {
                    classArrayList = Class.getAssistantClassesList(homeworkPost.getCourse_id(), user);
                }
                req.setAttribute(Tags.TAG_CLASS_LIST, classArrayList);

                req.getRequestDispatcher(FitmPath.HOMEWORK_SUBMISSIONS_PAGE).forward(req, resp);
            } else {
                System.out.println("NULL");
            }
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}
