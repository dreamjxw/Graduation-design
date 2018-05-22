<%@ page import="com.jxw.design.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="font-size: 40px;text-align: center;">

<h3>登陆成功！</h3>
success!
<% User user = (User) request.getAttribute("user");%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%=basePath%>
<div>
    用户昵称：<%= user.getUserName() %>
</div>
<div>
    用户头像：<img src="<%= user.getUserHeadImg() %>" style="width: 100px;height: 100px;">
</div>
<a href='http://localhost:63342/Graduation-design/dream-jxw-web/src/main/webapp/WEB-INF/redwine/index.html?name= + <%=user.getUserId()%>'>跳转至首页</a>
</body>
</html>