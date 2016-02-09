<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="./static/mdl/material.min.css" type="text/css">
    <link rel="stylesheet" href="./static/material-icons.css" type="text/css">
    <script src="./static/mdl/material.min.js" type="application/javascript"></script>
    <script src="static/login.js" type="application/javascript"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>凌晨四点线上作业提交系统</title>
</head>
<body>
    <div class="demo-drawer mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
        <header class="mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
            <div class="mdl-layout__header-row">
                <div class="mdl-layout-title">个人主页</div>
                <div class="mdl-layout-spacer"></div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
                    <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
                        <i class="material-icons">search</i>
                    </label>
                    <div class="mdl-textfield__expandable-holder">
                        <input class="mdl-textfield__input" type="text" id="search">
                        <label class="mdl-textfield__label" for="search">搜索...</label>
                    </div>
                </div>
                <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
                    <i class="material-icons">more_vert</i>
                </button>
                <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                    <li class="mdl-menu__item">关于</li>
                    <li class="mdl-menu__item">联系我们</li>
                    <li class="mdl-menu__item">项目源码</li>
                </ul>
            </div>
        </header>
        <div class="mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
            <nav class="mdl-navigation mdl-color--blue-grey-800">
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>主页</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>信箱</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">report</i>作业</a>
            </nav>

                <div class="mdl-layout-spacer"></div>
        </div>
    </div>
</body>
</html>
