package fitm.controller;

import fitm.model.Course;
import fitm.model.User;
import fitm.util.Tags;
import fitm.util.Utils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.Tag;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownloadPermissionControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getCurrentUser(req);

        if (user != null) {
            String[] pathItems = req.getRequestURI().split("[/]");
            if (pathItems.length < 4) {
                resp.sendError(404);
            } else {
                String folder_name = pathItems[2];
                String file_name = pathItems[3];

                boolean flag;
                switch (folder_name) {
                    case Tags.TAG_FOLDER_HOMEWORK_SUBMISSION: {
                        String homework_id = file_name.split("[_]")[0];
                        String course_id = Course.getCourseIdFromHomeworkId(homework_id);
                        flag = user.isTeacher() || user.isAssistantOfCourse(course_id);
                        break;
                    }
                    default: flag = true;
                }
                if (flag) {
                    resp.setContentType(getServletContext().getMimeType(file_name));
                    resp.setHeader("Content-disposition","attachment; filename="+file_name);
                    String path = getServletContext().getRealPath("/") + Tags.TAG_FOLDER_DOWNLOAD
                            + File.separator + folder_name + File.separator + file_name;
                    try {
                        File file = new File(path);
                        IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DownloadPermissionControlServlet.class.getName()).log(Level.INFO, null, ex);
                        resp.sendError(404);
                    }
                } else {
                    resp.sendError(404);
                }
            }
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}
