<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="indigo">
    <div class="nav-wrapper container">
        <a href="/" class="brand-logo">凌晨四点</a>
        <a href="#" data-activates="mobile-side-nav" class="button-collapse"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="/">总览</a></li>
            <li><a href="/courses">课程</a></li>
            <li><a id="header_mailbox" href="/mailbox">信箱</a></li>
            <li><a href="/message">消息</a></li>
            <!-- Dropdown Trigger -->
            <li><a class="dropdown-button" data-activates="dropdown-main">${user.name} (${user.id})
                <i class="material-icons right">arrow_drop_down</i></a></li>
        </ul>

        <ul class="side-nav" id="mobile-side-nav">
            <li><a class="waves-effect" href="/">总览</a></li>
            <li><a class="waves-effect" href="/courses">课程</a></li>
            <li><a class="waves-effect" href="/mailbox">信箱</a></li>
            <li><a class="waves-effect" href="/message">消息</a></li>
            <li><a class="waves-effect" onclick="logout()">退出</a></li>
        </ul>
    </div>
</nav>
<!-- Dropdown Structure -->
<ul id="dropdown-main" class="dropdown-content">
    <li><a href="/edit_password">修改密码</a></li>
    <li><a onclick="logout()">退出</a></li>
</ul>