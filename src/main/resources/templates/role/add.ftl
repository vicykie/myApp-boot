<html lang="zh_CN" xmlns="http://www.w3.org/1999/html">
<head>

    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>add user</title>
<#--   <script data-main="${springMacroRequestContext.contextPath}/static/assets/js/main.js"
           src="${springMacroRequestContext.contextPath}/static/assets/js/require/require.js"></script>-->
<#--<script src="${springMacroRequestContext.contextPath}/static/assets/js/main.js"></script>-->
<#--注意：你在data-main中所设置的脚本是异步加载的。所以如果你在页面中配置了其它JS加载，则不能保证它们所依赖的JS已经加载成功。-->

</head>
<body>
<form action="/role/add" method="post">
    <label>roleName:</label><input name="roleName"></br>
    <label>description:</label><input name="description"></br>
    <input type="submit" value="submit">
</form>
</body>
</html>