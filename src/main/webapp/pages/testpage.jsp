<%--@elvariable id="lang" type="java.lang.String"--%>
<html>
<head>
    <title>Test page</title>
</head>
<body>
    <p>lang: ${lang}</p>
    <p>${pageContext.response.contentType}</p>
    <p>${pageContext.request.requestURI}</p>
    <p>${pageContext.request.requestURL}</p>
</body>
</html>
