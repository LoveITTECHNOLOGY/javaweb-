<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/3
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    session.putValue("name","tyy");//使用session存储值，键值对的形式
    //ValueNames和Value
    session.putValue("nn","zhang");
    for (int i = 0; i <session.getValueNames().length ; i++) {
        System.out.println(session.getValueNames()[i]);
        System.out.println(session.getValue(session.getValueNames()[i]));
    }
%>
<%
    session.setAttribute("name1","uzi");//存数据  把uzi存在name1中
    session.setAttribute("name2","clearlove");
    out.print(session.getAttribute("name1"));
%>
</body>
</html>
