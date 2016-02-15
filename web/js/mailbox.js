function validate_required(to, content) {
    if (to == null || to == "") {
        Materialize.toast('请输入收件人', 2000);
        return false;
    } else if (content == null || content == "") {
        Materialize.toast('请输入邮件正文', 2000);
        return false;
    }
    return true;
}

function send_mail() {
    var mailTo = document.getElementById("mail_to").value;
    var mailContent = document.getElementById("mail_content").value;
    if (validate_required(mailTo, mailContent)) {
        xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/ajax/mailbox/send_mail", true);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhttp.send("mail_to=" + mailTo + "&mail_content=" + mailContent);
        xhttp.onreadystatechange = function() {
            if (xhttp.readyState == 4) { // 响应已经完成
                if (xhttp.status == 200) { // 服务器成功返回内容
                    var response = JSON.parse(xhttp.responseText);
                    if (response.success) {
                        Materialize.toast('发送成功', 2000);
                        document.getElementById("mail_to").value = "";
                        document.getElementById("mail_content").value = "";
                    } else {
                        Materialize.toast(response.msg, 2000);
                    }
                }
            }
        }
    }
}

function set_mails_content(data) {

}

function get_mails(pageIndex) {
    Materialize.toast('Enter get mails...', 2000);
    var defaultPageSize = 6;
    if (pageIndex != null) {
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/ajax/mailbox/get_mails", true);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhttp.onreadystatechange = function() {
            if (xhttp.readyState == 4) { // 响应已经完成
                if (xhttp.status == 200) { // 服务器成功返回内容
                    var response = JSON.parse(xhttp.responseText);
                    if (response.success) {
                        Materialize.toast('接收成功', 2000);
                        set_mails_content(response.data);
                    } else {
                        Materialize.toast(response.msg, 2000);
                    }
                }
            }
        }
        xhttp.send("mail_page_index=" + pageIndex);
    }
}