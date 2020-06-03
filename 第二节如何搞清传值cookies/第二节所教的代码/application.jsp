<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/3
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>application</h1>
<%
    if (application.getAttribute("count")==null){
        //如果进入页面没有得到值，证明是第一次，所以要设置值
        application.setAttribute("count","1");
    }else{
        //如果不是第一次，那就要count要自增
        //此处的a就是上面的 放在count中的一个数字
       String a= application.getAttribute("count").toString();
       //Integer整型的包装类，调用parseInt(a)转换为整型
       int b=Integer.parseInt(a)+1;
       //自增完了之后再放回count中
       application.setAttribute("count",Integer.toString(b));
    }
%>
你是第<%=application.getAttribute("count")%>位访问者。
</body>
</html>
