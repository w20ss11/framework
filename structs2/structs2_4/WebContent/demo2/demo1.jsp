<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>OGNL在Structs2环境中的入门</h1>
<h3>调用对象的方法</h3>
<s:property value="'structs'.length()"/>


<h3>调用对象的静态方法</h3>
<!-- 静态方法访问在Struct2默认是关闭的 需要开启一个常量（default.properties） -->
<s:property value="@java.lang.Math@random()"/>
</body>
</html>