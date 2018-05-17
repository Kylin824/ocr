<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Login Test</title>
</head>
<body>
<%--<s:form action="login">--%>
    <%--<s:textfield name="username" key="user" />--%>
    <%--<s:submit key="login" />--%>
<%--</s:form>--%>

<form action="login" method="post">
    <label>Please enter your name</label><br/>
    <input type="text" name="username"/><br/>
    <input type="password" name="password"/><br/>
    <input type="submit" value="Login"/>
    <input type="reset" value="Reset"/>
</form>

</body>
</html>
