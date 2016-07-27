<html lang="zh_CN" xmlns="http://www.w3.org/1999/html">
<head>

    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>se</title>

<#--<script data-main="${springMacroRequestContext.contextPath}/static/assets/js/main.js"-->
<#--src="${springMacroRequestContext.contextPath}/static/assets/js/require/require.js"></script>-->
<#--<script src="${springMacroRequestContext.contextPath}/static/assets/js/main.js"></script>-->
<#--注意：你在data-main中所设置的脚本是异步加载的。所以如果你在页面中配置了其它JS加载，则不能保证它们所依赖的JS已经加载成功。-->
    <script src="//cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-table/1.10.1/extensions/export/bootstrap-table-export.js"></script>
    <script src="${springMacroRequestContext.contextPath}/static/assets/js/tableExport.min.js"></script>
    <link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
<#list roleList as r >
    ${r.roleName}</br>
</#list>
    sss
</div>
<script>

</script>
</body>
</html>