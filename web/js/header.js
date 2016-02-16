$( document ).ready(function(){
    $(".dropdown-button").dropdown({belowOrigin:true}); // for dropdown in navigation bar
    $(".button-collapse").sideNav(); // for side navigation drawer in small screen
    $('select').material_select(); // initialize select element
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
    }); // for  datepicker
});

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