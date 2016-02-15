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
    var div_mail_list = document.getElementById("mail_list");
    if (data != null) {
        div_mail_list.innerHTML = "";

        var ul_mail_container = document.createElement("ul");
        ul_mail_container.setAttribute("class", "collapsible popout");
        ul_mail_container.setAttribute("data-collapsible", "accordion");
        div_mail_list.appendChild(ul_mail_container);

        for (var i = 0; i < data.length; i++) {
            var mail = data[i];

            var li_mail_item = document.createElement("li");
            ul_mail_container.appendChild(li_mail_item);

            var div_item_header = document.createElement("div");
            div_item_header.setAttribute("class", "collapsible-header");
            li_mail_item.appendChild(div_item_header);

            var span_header_container = document.createElement("span");
            span_header_container.innerHTML = mail.from;
            div_item_header.appendChild(span_header_container);

            var i_header_icon = document.createElement("i");
            i_header_icon.setAttribute("class", "material-icons");
            i_header_icon.innerHTML = "perm_identity";
            span_header_container.appendChild(i_header_icon);

            var div_item_body = document.createElement("div");
            div_item_body.setAttribute("class", "collapsible-body");
            li_mail_item.appendChild(div_item_body);

            var p_body_container = document.createElement("p");
            p_body_container.innerHTML = mail.content;
            div_item_body.appendChild(p_body_container);
        }
        $('.collapsible').collapsible(); // Important! 重新加载jQuary
    }
}

function get_mails(pageIndex) {
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