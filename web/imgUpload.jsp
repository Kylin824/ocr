<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>Image Upload In Struts2</title>
</head>

<body>
<s:form action="imgUpload" method="post" enctype="multipart/form-data">
    <s:file name="myFile" label="File" />
    <s:textfield name="username" label="Username" />
    <s:submit label="上传" />
</s:form>

</body>

</html>
