<html>
<head>
    <title>登录</title>
<#include "common/head.ftl">
</head>
<body>
<h2>Hello World!</h2>
<form>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>

    <button type="button" class="btn" onclick="login()">Log in</button>
</form>
<script src="${rc.contextPath}/static/js/lib/jquery/jquery.min.js"></script>
<script src="${rc.contextPath}/static/js/lib/tokenManager/tokenManager.js"></script>
<script>
    function login() {
        var data = $("form").serialize();
        $.ajax({
            url: "${rc.contextPath}/auth/login",
            data: data,
            type: 'post',
            dataType: 'json',
            success: function (re, status, res) {
                if (re.code == 'IN001') {
                    var authToken = res.getResponseHeader(tokenManager.tokenHeader);
                    tokenManager.setToken(authToken);
                    document.cookie = tokenManager.tokenHeader + "=" + authToken;
                    window.location.href = re.data;
                } else {
                    alert(re.rspMsg);
                }

            }
        });
    }
</script>
</body>
</html>
