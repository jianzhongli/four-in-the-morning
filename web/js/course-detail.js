function post_homework() {
    var course_id = document.getElementById("course-id").value;
    var homework_title = document.getElementById("homework_title").value;
    var homework_description = document.getElementById("homework_description").value;
    var ddl = document.getElementById("ddl").value;

    var formData = new FormData();
    formData.append("course_id", course_id);
    formData.append("homework_title", homework_title);
    formData.append("homework_description", homework_description);
    formData.append("post_date", new Date());
    formData.append("ddl", ddl);
    //formData.append("attach_file", document.getElementById("attach_file").files[0]);

    xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/ajax/homework", true);
    xhttp.send(formData);
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            alert(xhttp.responseText);
        }
    }
}