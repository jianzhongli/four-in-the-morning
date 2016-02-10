function logout() {
    xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/ajax/logout", true)
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var obj = JSON.parse(xhttp.responseText);
            if (!obj.success) {
                // TODO: 如果注销失败，提示用户错误信息
            } else { // 如果注销成功，重定向至登录页面
                window.location.href = '/login';
            }
        }
    }
}

function retrieve_courses() {
    xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/ajax/courses", true)
    xhttp.send();
}

window.onload = function() {
    retrieve_courses();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var obj = JSON.parse(xhttp.responseText);
            if (!obj.success) {
                // TODO: 如果注销失败，提示用户错误信息
            } else { // 如果注销成功，重定向至登录页面
                var course_list = JSON.parse(xhttp.responseText).data;
                var course_list_node = document.getElementById("course-list");
                var list_item_html = "";
                for (var i = 0; i < course_list.length; ++i) {
                    var course = course_list[i];
                    list_item_html +=
                        "<li class=\"mdl-list__item course-list__item\"><span class=\"mdl-list__item-primary-content\"><h5>" +
                        course.course_name  +
                        "</h5></span></li>";
                }
                course_list_node.innerHTML = list_item_html;
            }
        }
    }
}

