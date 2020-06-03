<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/1
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <%
       request.setAttribute("aa",2);
        //送快递，aa是快递的盒子名称，2是快递箱里的东西
       request.setCharacterEncoding("utf-8");//设置字符编码
       String basePath=request.getScheme()+"://"+request.getServerName()
               +":"+request.getServerPort()+"/";
       //getScheme()-协议名  getServerName()-localhost getServerPort()-端口号
   %>
<form action="#" method="post">
    <input type="text" name="uName"><br>
    <input type="password" name="uPsw"><br>
    <input type="submit" value="提交"><br>
</form>
当前页面的URL：<%=basePath%><br>
请求方式：<%=request.getMethod()%><br>
路径:<%=request.getRequestURI()%><br>
协议名称:<%=request.getProtocol()%><br>
表单提交的属性值:<%=request.getParameter("uName")%><br>
设值与取值:<%=request.getAttribute("aa")%>
</body>
</html>
