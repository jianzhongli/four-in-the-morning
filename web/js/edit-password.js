function edit_password() {
    var old_password = document.getElementById("old-password").value;
    var new_password = document.getElementById("new-password").value;
    var confirm_password = document.getElementById("confirm-password").value;
    if (new_password != confirm_password) {
        Materialize.toast('密码不匹配！', 2000);
    } else if (new_password.length > 0 && old_password.length > 0) {
        xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/ajax/user/update", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("old-password="+old_password+"&password="+new_password);
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                var obj = JSON.parse(xhttp.responseText);
                if (!obj.success) {
                    Materialize.toast(obj.msg, 2000);
                    // TODO: 提示用户名或密码错误
                } else { // 如果登录成功，重定向至用户个人主页
                    Materialize.toast('修改成功！');
                }
            }
        }
    }
}

function login() {
    var userid = document.getElementById("userid").value;
    var password = document.getElementById("password").value;
    if (userid.length > 0 && password.length > 0) {
        xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/ajax/login", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("userid="+userid+"&password="+password);
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                var obj = JSON.parse(xhttp.responseText);
                if (!obj.success) {
                    //Materialize.toast('用户名或密码错误', 2000);
                    // TODO: 提示用户名或密码错误
                } else { // 如果登录成功，重定向至用户个人主页
                    window.location.href = '/';
                }
            }
        }
    }
}