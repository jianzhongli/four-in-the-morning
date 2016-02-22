<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../../css/style.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>修改密码</title>
</head>

<body class="grey lighten-5">
    <%@ include file="header.jsp" %>

    <div class="container">
        <div class="row">
            <div class="col l6 offset-l3 s10 offset-s1">
                <div class="card">
                    <div class="card-content">
                        <div class="card-title">
                            修改密码
                        </div>

                        <form>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="old-password" type="password">
                                    <label for="old-password">原密码</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="new-password" type="password">
                                    <label for="old-password">新密码</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="confirm-password" type="password">
                                    <label for="old-password">确认密码</label>
                                </div>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col s12">
                                <div class="divider"></div>
                            </div>
                        </div>
                        <a class="btn waves-light waves-effect" onclick="edit_password('${user.id}')">确认</a>
                        <a class="btn-flat waves-ripple waves-effect" href="/">取消</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="../../js/materialize.min.js"></script>
    <script src="../../js/header.js" type="application/javascript"></script>
    <script src="../../js/edit-password.js" type="application/javascript"></script>
</body>
</html>