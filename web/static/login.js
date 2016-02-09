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
                    // TODO: 如果登录失败，提示用户错误信息
                } else { // 如果登录成功，重定向至用户个人主页
                    window.location.href = '/';
                }
            }
        }
    }
}

// TODO: 利用 API 帮助重设密码
function reset_password() {

}