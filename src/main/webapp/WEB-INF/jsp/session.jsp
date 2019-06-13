
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="/statics/css/main.css">
    <title>session的访问</title>
</head>
<body>
    session: ${sessionScope.get("sessionVal")}<br>
    request: ${requestScope.get("sessionVal")}


</body>
</html>
