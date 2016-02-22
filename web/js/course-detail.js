// 当发布新作业或修改作业时，此函数会被调用，弹出编辑作业的 modal
function post_new_homework() {
    $('#homework_description').val('').trigger('autoresize');
    $('#ddl').val('').click();
    $('#homework_title').val('').click();
    $('#modal-post-homework').openModal();
    window.is_new = true;
}

// 弹出删除作业的确认 modal
function delete_confirm(homework_id) {
    window.homework_id = homework_id;
    $('#modal-delete-confirm').openModal();
}

// 向服务器发送请求，删除作业
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

// 将 modal 中编辑好的内容发送至服务器，根据语境进行修改或删除
function post_homework_to_server() {
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
    formData.append("attach_file", document.getElementById("attach_file").files[0]);

    xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/ajax/homework", true);
    if (!window.is_new) {
        formData.append("homework_id", window.homework_id)
    }

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

// 获取要编辑的作业的信息，填入 modal
function edit_homework(homework_id) {
    window.is_new = false;
    window.homework_id = homework_id;

    xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/ajax/homework/"+homework_id, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var obj = JSON.parse(xhttp.responseText);
            if (obj.success) {
                homework = obj.data;
                $('#homework_title').val(homework.homework_title).click();
                $('#homework_description').val(homework.homework_description).trigger('autoresize').click();
                $('#ddl').val(homework.ddl).click();
                $('#modal-post-homework').openModal();
            } else {
                Materialize.toast(obj.msg, 2000);
            }
        }
    }
}

function open_submit_homework_modal(homework_id) {
    window.homework_id = homework_id;
    $('#modal-submit-homework').openModal();
}

function submit_homework_to_server() {
    var formData = new FormData();
    formData.append("homework_id", window.homework_id);
    formData.append("attach_file", document.getElementById("submission_attach_file").files[0]);
    xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/ajax/submission", true);
    xhttp.send(formData);
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var obj = JSON.parse(xhttp.responseText);
            if (obj.success) {
                Materialize.toast('提交成功', 2000);
            } else {
                Materialize.toast('提交失败：' + obj.msg, 2000);
            }
        }
    }
}

function open_modal_edit_intro() {
    $('#modal-edit-course-intro').openModal();
}

function update_intro_text() {
    var course_id = $('#course-id').val();
    var intro_text = $('#intro-text').val();
    var formData = new FormData();
    formData.append("intro_text", intro_text);

    xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/ajax/courses/"+course_id, true);
    xhttp.send(formData);
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            var obj = JSON.parse(xhttp.responseText);
            if (obj.success) {
                window.location.reload();
            } else {
                Materialize.toast(obj.msg, 2000);
            }
        }
    }
}

function open_modal_assign_ta() {
    $('#modal-assign-ta').openModal();
}

function send_ta_assignment_to_server() {
    var ta_id = $('#ta_id').val();
    var class_list = [];
    $('[name=checkbox_classes]').each(function () {
        class_list.push(this.value);
    });
    var json = {"id": ta_id, "class_list": class_list};
    $.ajax({
        url: '/ajax/assistants',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(json),
        dataType: 'json',
        success: function(response) {
            if (!response.success) {
                Materialize.toast(response.msg, 2000);
            } else { // 如果登录成功，重定向至用户个人主页
                Materialize.toast('修改成功！', 2000);
            }
        }
    });
}

$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();
});