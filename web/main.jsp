<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="stylesheet" href="./static/mdl/material.min.css" type="text/css">
        <link rel="stylesheet" href="./static/material-icons.css" type="text/css">
        <script src="./static/mdl/material.min.js" type="application/javascript"></script>
        <script src="./static/main.js" type="application/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>凌晨四点线上作业提交系统</title>
    </head>
    <body>
        <!-- Simple header with scrollable tabs. -->
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <header class="mdl-layout__header">
                <div class="mdl-layout__header-row">
                    <!-- Title -->
                    <span class="mdl-layout-title">凌晨四点线上作业提交系统</span>
                    <div class="mdl-layout-spacer"></div>
                    <div class="mdl-dropdown-menu">
                        <span>${realname}(${userid})</span>
                        <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                            <i class="material-icons" role="presentation">arrow_drop_down</i>
                        </button>
                        <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
                            <li class="mdl-menu__item" onclick="logout()">退出</li>
                        </ul>
                    </div>
                </div>
                <!-- Tabs -->
                <div class="mdl-layout__tab-bar mdl-js-ripple-effect">
                    <a href="#scroll-tab-1" class="mdl-layout__tab is-active">总览</a>
                    <a href="#scroll-tab-2" class="mdl-layout__tab">课程</a>
                    <a href="#scroll-tab-3" class="mdl-layout__tab">作业</a>
                </div>
            </header>
            <main class="mdl-layout__content">
                <section class="mdl-layout__tab-panel is-active" id="scroll-tab-1">
                    <div class="page-content"><!-- Your content goes here --></div>
                </section>
                <section class="mdl-layout__tab-panel" id="scroll-tab-2">
                    <div class="page-content"><!-- Your content goes here --></div>
                </section>
                <section class="mdl-layout__tab-panel" id="scroll-tab-3">
                    <div class="page-content"><!-- Your content goes here --></div>
                </section>
                <section class="mdl-layout__tab-panel" id="scroll-tab-4">
                    <div class="page-content"><!-- Your content goes here --></div>
                </section>
                <section class="mdl-layout__tab-panel" id="scroll-tab-5">
                    <div class="page-content"><!-- Your content goes here --></div>
                </section>
                <section class="mdl-layout__tab-panel" id="scroll-tab-6">
                    <div class="page-content"><!-- Your content goes here --></div>
                </section>
            </main>
        </div>
    </body>
</html>
