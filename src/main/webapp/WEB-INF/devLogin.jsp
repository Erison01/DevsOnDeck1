<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/Css/login.css">
</head>
<body>
<div class="navbar">
    <h1>Devs On Deck</h1>
    <a href="/org/login">Org Login</a>
</div>
<div class="container">
    <form:form action="/dev/login" method="post" modelAttribute="logDev">
        <div>
            <h2>Welcome back, Developer</h2>
            <p>Let's Connect You To A Job!</p>
        </div>
        <div>
            <form:label path="email" >Email</form:label>
            <form:input path="email"/>
            <form:errors path="email" cssClass="error"/>
        </div>
        <div>
            <form:label path="password" >Password</form:label>
            <form:password path="password"/>
            <form:errors path="password" cssClass="error"/>
        </div>
        <input type="submit" value="Login"/>
        <h3>Or</h3>

        <a href="/dev/register" class="register-link"><span></span><span></span><span></span><span></span>Register</a>

    </form:form>
</div>
</body>
</html>
