function edit_password(userid) {
    var old_password = document.getElementById("old-password").value;
    var new_password = document.getElementById("new-password").value;
    var confirm_password = document.getElementById("confirm-password").value;
    if (new_password != confirm_password) {
        Materialize.toast('密码不匹配！', 2000);
    } else if (new_password.length > 0 && old_password.length > 0) {
        var json = {"old-password": old_password, "password":new_password};
        $.ajax({
            url: '/ajax/user/' +userid,
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
}

function login() {
    var userid = document.getElementById("userid").value;
    var password = document.getElementById("password").value;
    if (userid.length > 0 && password.length > 0) {
        $.ajax({
            url: '/ajax/login',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: "userid="+userid+"&password="+password,
            dataType: 'json',
            success: function(response) {
                if (!response.success) {
                    Materialize.toast('用户名或密码错误', 2000);
                } else { // 如果登录成功，重定向至用户个人主页
                    window.location.href = '/';
                }
            }
        });
    }
}