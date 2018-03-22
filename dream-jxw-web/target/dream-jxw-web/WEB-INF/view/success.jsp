<%@ page import="com.jxw.design.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="font-size: 40px;text-align: center;">

<h3>登陆成功！</h3>
success!
<% User user = (User) request.getAttribute("user");%>
<div>
    用户昵称：<%= user.getUserName() %>
</div>
<div>
    用户头像：<img src="<%= user.getUserHeadImg() %>" style="width: 100px;height: 100px;">
</div>
</body>
</html>
