<html lang="zh_CN">
<head>

    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>user info </title>
    <script data-main="${rc.contextPath}/static/js/app.js"
            src="${rc.contextPath}/static/js/lib/requirejs/require.js"></script>
    <script src="${rc.contextPath}/static/js/app/user/main.js"></script>
<#--<script src="${springMacroRequestContext.contextPath}/static/assets/js/main.js"></script>-->
<#--注意：你在data-main中所设置的脚本是异步加载的。所以如果你在页面中配置了其它JS加载，则不能保证它们所依赖的JS已经加载成功。-->

</head>
<body>
<form action="/user/${user.id}" method="post">


    <div class="form-control">
        name:${user.name}
    </div>
    <div class="form-control">
        id:${user.id}
    </div>
    <div class="form-control">
        age:${user.age}
    </div>
    <div>
        <select name="roleId">
        <#list roleList as r>
            <option value="${r.id}">${r.description}</option>
        </#list>
        </select>
        <button type="submit">submit</button>
</form>
</div>
</body>
</html>