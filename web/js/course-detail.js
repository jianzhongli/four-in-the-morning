function post_homework() {
    var course_id = document.getElementById("course-id").value;
    var homework_title = document.getElementById("homework_title").value;
    var homework_description = document.getElementById("homework_description").value;
    var ddl = new Date(document.getElementById("ddl").value).valueOf();

    var formData = new FormData();
    formData.append("course_id", course_id);
    formData.append("homework_title", homework_title);
    formData.append("homework_description", homework_description);
    formData.append("post_date", new Date().valueOf());
    formData.append("ddl", ddl);
    //formData.append("attach_file", document.getElementById("attach_file").files[0]);

    xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/ajax/homework", true);
    xhttp.send(formData);
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var homework = JSON.parse(xhttp.responseText);
            if (homework.success) {
                Materialize.toast('发布成功', 2000);
                window.location.href = '/courses/' + course_id + '#homework';
                location.reload();
            } else {
                Materialize.toast('发布失败：' + homework.msg, 2000);
            }
        }
    }
}

var homework_id;
function delete_confirm(homework_id) {
    window.homework_id = homework_id;
    $('#modal-delete-confirm').openModal();
}

function delete_homework() {
    xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/ajax/homework/"+window.homework_id, true);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var obj = JSON.parse(xhttp.responseText);
            if (obj.success) {
                Materialize.toast('删除成功', 2000);
                location.reload();
            } else {
                Materialize.toast('删除失败：' + obj.msg, 2000);
            }
        }
    }
}

function edit_homework(homework_id) {
    Materialize.toast('Not implemented yet!', 2000);
    //xhttp = new XMLHttpRequest();
    //xhttp.open("GET", "/ajax/homework/"+homework_id, true);
    //xhttp.send();
    //xhttp.onreadystatechange = function () {
    //    if (xhttp.readyState == 4 && xhttp.status == 200) {
    //        var obj = JSON.parse(xhttp.responseText);
    //        if (obj.success) {
    //            homework = obj.data;
    //            $('#modal-post-homework').openModal();
    //            $('#homework_title').val(homework.homework_title);
    //            $('#homework_title').trigger('autoresize');
    //            $('#homework_description').val(homework.homework_description);
    //            $('#homework_description').trigger('autoresize');
    //            $('#ddl').val(homework.ddl);
    //            $('#ddl').trigger('autoresize');
    //        } else {
    //            Materialize.toast(obj.msg, 2000);
    //        }
    //    }
    //}
}

$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();
});