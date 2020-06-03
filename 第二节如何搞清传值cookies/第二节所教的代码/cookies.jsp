<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/3
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie[] cookies=request.getCookies();//最开始这个数组长度为0
    int vi=0;//访问标识符
    for (int i = 0; i <cookies.length ; i++) {
        if (cookies[i].getName().equals("admin")){
            //如果有个cookie的名字和 "admin"相同,那就把值赋给vi
            vi=Integer.parseInt(cookies[i].getValue());
            break;//一旦找到我们要的cookie就跳出循环
        }
    }
    vi++;
    Cookie c2=new Cookie("admin",vi+"");//vi+"" 字符串拼接
    Cookie c3=new Cookie("num","123");
    c2.setMaxAge(3*24*60*60);//设置cookies的生命周期
    response.addCookie(c2);//把c2添加进response
    response.addCookie(c3);
    response.sendRedirect("cookies2.jsp");
%>
</body>
</html>
