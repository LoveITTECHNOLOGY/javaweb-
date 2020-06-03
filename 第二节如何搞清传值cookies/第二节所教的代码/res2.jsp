<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/3
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="" method="post">
    <span>你喜欢的编程语言有：</span><br>
    <input type="checkbox" name="ab" value="sess1">C++<br>
    <input type="checkbox" name="ab" value="sess2">JAVA<br>
    <input type="checkbox" name="ab" value="sess3">Python<br>
    <input type="submit" value="提交" name="sub">
</form>
<%
    //String str=request.getParameter(前端页面input框的name属性);
    if (request.getParameter("sub")!=null) {
        String[] arr = (String[]) request.getParameterValues("ab");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
%>

</body>
</html>
