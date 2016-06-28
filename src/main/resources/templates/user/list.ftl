<html lang="zh_CN">
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

        <div class="panel-body">
    <table class="table " id="table-users"
           data-url="/user/users"

    >
        <thead>
            <tr>
                <th data-field="username">用户名</th>
                <th data-field="password">密码</th>
                <th data-field="name">名字</th>
                <th data-field="age">年龄</th>
                <th data-field="address">地址</th>
                <th data-field="enabled">是否启用</th>
            </tr>
        </thead>
    </table>
    </div>
    </div>
<script>
    $(function () {
        $("#table-users").bootstrapTable({
            showExport:true,
            exportTypes:["excel"],
            exportOptions:{
                fileName:"users",
                displayTableName:true,

            }
        });
    })
</script>
</body>
</html>