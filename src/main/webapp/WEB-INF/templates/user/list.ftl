<html lang="zh_CN">
<head>

    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>user list </title>

<#--<script data-main="${springMacroRequestContext.contextPath}/static/assets/js/main.js"-->
<#--src="${springMacroRequestContext.contextPath}/static/assets/js/require/require.js"></script>-->
<#--<script src="${springMacroRequestContext.contextPath}/static/assets/js/main.js"></script>-->
<#--注意：你在data-main中所设置的脚本是异步加载的。所以如果你在页面中配置了其它JS加载，则不能保证它们所依赖的JS已经加载成功。-->
    <script src="//cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-table/1.10.1/extensions/export/bootstrap-table-export.js"></script>
    <script src="${springMacroRequestContext.contextPath}/assets/js/tableExport.js"></script>
    <link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
    <button class="btn btn-primary pull-right" id="btn-export">导出</button>
    <div class="panel-body">
        <table class="table " id="table-users"
               data-url="/user/users">
            <thead>
            <tr>
                <th data-field="username">用户名</th>
                <th data-field="password">密码</th>
                <th data-field="name">名字</th>
                <th data-field="age">年龄</th>
                <th data-field="address">地址</th>
                <th data-field="enabled" data-formatter="enabledFormatter">是否启用</th>
                <th data-field="id" data-formatter="oprFormatter">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script>
    $(function () {
        $("#table-users").bootstrapTable({
//            showExport: true,
//            exportTypes: ["excel"],
//            exportOptions: {
//                fileName: "users",
//                //  displayTableName: true,
//            }
        });
        $("#btn-export").click(function () {
            $("#table-users").tableExport({
                type: "excel",
                excelstyles: ['css', 'properties', 'to', 'export', 'to', 'excel'],
                fileName: 'tableExport'
//                theadSelector: 'th',
//                worksheetName: 'sssssss'
//                htmlContent: true
            });
        })
    });
    function enabledFormatter(value, row, index) {
        return str = value ? "<span style='color: red'>启用</span>" : "<span style='color: aqua'>未启用</span>"
    }
    function oprFormatter(value, row, index) {
        return "<a href='/user/" + value + "' class='btn btn-warning'>查看</a>";
    }
</script>
</body>
</html>