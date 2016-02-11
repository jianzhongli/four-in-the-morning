<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <span class="mdl-layout-title">凌晨四点线上作业提交系统</span>
        <div class="mdl-layout-spacer"></div>
        <nav class="mdl-navigation mdl-layout--large-screen-only">
            <a class="mdl-navigation__link" href="/">总览</a>
            <a class="mdl-navigation__link" href="/courses">课程</a>
            <a class="mdl-navigation__link" href="/homework">作业</a>
            <a class="mdl-navigation__link" href="/mailbox">信箱</a>
            <a class="mdl-navigation__link" href="/message">消息</a>
            <span class="mdl-navigation__link">${realname}（${userid}）</span>
            <div class="mdl-dropdown-menu">
                <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                    <i class="material-icons" role="presentation">arrow_drop_down</i>
                </button>
                <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
                    <li class="mdl-menu__item" onclick="logout()">退出</li>
                </ul>
            </div>
        </nav>
    </div>
</header>