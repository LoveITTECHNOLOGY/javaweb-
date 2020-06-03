<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/3
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>pageContext</h1>
<%
session.setAttribute("name","sesstyy");
application.setAttribute("name","applityy");
request.setAttribute("name","reqtyy");
pageContext.setAttribute("name","pagetyy");
%>
取值<br>
<%
out.print(pageContext.getAttribute("name"));
//pageContext.getRequest()得到 request对象
out.print(pageContext.getRequest().getAttribute("name"));
//pageContext.getSession()得到 session对象
out.print(pageContext.getSession().getAttribute("name"));
out.print(pageContext.getServletContext().getAttribute("name"));
%>
<br>取值：按照设定的顺序(域的大小)
<%
out.print(pageContext.getAttribute("name",1));
out.print(pageContext.getAttribute("name",2));
out.print(pageContext.getAttribute("name",3));
out.print(pageContext.getAttribute("name",4));
%>
</body>
</html>
