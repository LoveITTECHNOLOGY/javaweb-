<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/3
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie[] co=request.getCookies();//得到浏览器中的cookie
    int vi=0;//访问标识符
    for (int i = 0; i <co.length ; i++) {
        if (co[i].getName().equals("admin")){
            vi=Integer.parseInt(co[i].getValue());
            break;
        }
    }
out.print("你是第"+vi+"次来此页面！");
%>
</body>
</html>
