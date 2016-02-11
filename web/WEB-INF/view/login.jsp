<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" href="../../static/mdl/material.min.css" type="text/css">
        <link rel="stylesheet" href="../../static/material-icons.css" type="text/css">
        <script src="../../static/mdl/material.min.js" type="application/javascript"></script>
        <script src="../../static/login.js" type="application/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <style>
            .mdl-layout {
                align-items: center;
                justify-content: center;
            }
            .mdl-layout__content {
                padding: 24px;
                flex: none;
            }
        </style>
        <title>登录</title>
    </head>
    <body>
        <div class="mdl-layout mdl-js-layout mdl-color--grey-100">
            <main class="mdl-layout__content">
                <div class="mdl-card mdl-shadow--6dp">
                    <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                        <h2 class="mdl-card__title-text">登录</h2>
                    </div>
                    <div class="mdl-card__supporting-text">
                        <form action="#">
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="text" id="userid" />
                                <label class="mdl-textfield__label" for="userid">用户名</label>
                            </div>
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="password" id="password" />
                                <label class="mdl-textfield__label" for="password">密码</label>
                            </div>
                        </form>
                    </div>
                    <div class="mdl-card__actions mdl-card--border">
                        <button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" onclick="login()">
                            登录
                        </button>
                        <button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" onclick="reset_password()">
                            忘记密码？
                        </button>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
