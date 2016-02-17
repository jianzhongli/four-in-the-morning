function login_ing() {
    document.getElementById('login-card').style.opacity = 1 / 2;
    document.getElementById('loader').style.display = "block";
}

function login_false() {
    document.getElementById('loader').style.display = "none";
    document.getElementById('login-card').style.opacity = 1;
}

function login() {
    var userid = document.getElementById("userid").value;
    var password = document.getElementById("password").value;
    if (userid.length > 0 && password.length > 0) {
        login_ing();
        xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/ajax/login", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("userid="+userid+"&password="+password);
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                var obj = JSON.parse(xhttp.responseText);
                if (!obj.success) {
                    Materialize.toast('用户名或密码错误', 2000);
                    login_false();
                } else { // 如果登录成功，重定向
                    window.location.href = document.referrer;
                }
            }
        }
    } else {
        Materialize.toast('请填写用户名和密码', 2000);
    }
}

function keyDown() {
    if (event.keyCode == 13) { // 回车键键盘响应
        login();
    }
}

// TODO: 利用 API 帮助重设密码
function reset_password() {

}
