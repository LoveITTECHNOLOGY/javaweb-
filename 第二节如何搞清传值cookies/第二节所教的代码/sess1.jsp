<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/3
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>session</h1>
创建时间:<%=session.getCreationTime()%><br>
最后一次访问时间:<%=session.getLastAccessedTime()%><br>
id:<%=session.getId()%>
</body>
</html>
