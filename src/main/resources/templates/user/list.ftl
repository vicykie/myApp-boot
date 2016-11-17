<html lang="zh_CN">
<head>

    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>user list </title>
    <script src="//cdn.bootcss.com/jquery/2.2.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap-table/1.10.1/extensions/export/bootstrap-table-export.js"></script>
    <link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
    <form id="auth-logout" action="/auth/logout" method="post">

    <button type="button" id="btn-logout" class="btn btn-primary">退出</button>
    </form>
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
                <th data-field="roleInfo.roleName">角色</th>
                <th data-field="enabled" data-formatter="enabledFormatter">是否启用</th>
                <th data-field="id" data-formatter="oprFormatter">操作</th>
            </tr>
            </thead>
        </table>
    </div>
    <button class="btn btn-success" id="add-test">添加</button>
</div>
<script>
    $(function () {
        $("#table-users").bootstrapTable();
        $("#btn-logout").on("click",function () {
            $("#auth-logout").submit();
        });
        $("#add-test").on("click",function () {
           $.ajax({
               url:"/user/test",
               data:{username:"adb",id:"123",name:'name'},
               type:'post',
               success:function (res) {
                   console.log(res);
               }
           })
        });
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