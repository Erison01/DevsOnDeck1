<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/Css/register.css">
</head>
<body>
<div class="navbar">
<h1>Devs On Deck</h1>
</div>
<h2>Organization Sign Up</h2>

<form:form method="post" action="/org/register" modelAttribute="newOrg">
    <div>
        <form:label path="orgName" >Org Name</form:label>
        <form:input path="orgName" />
        <form:errors path="orgName" cssClass="error"/>
    </div>
    <div class="d-flex justify-content-between">
        <div>
            <form:label path="firstName" >First Name</form:label>
            <form:input path="firstName" />
            <form:errors path="firstName" cssClass="error"/>
        </div>

        <div>
            <form:label path="lastName" >Last Name</form:label>
            <form:input path="lastName" />
            <form:errors path="lastName" cssClass="error"/>
        </div>
    </div>
    <div>
        <form:label path="email" >Contact Email</form:label>
        <form:input path="email" />
        <form:errors path="email" cssClass="error"/>
    </div>
    <div>
        <form:label path="address" >Org Address</form:label>
        <form:input path="address" />
        <form:errors path="address" cssClass="error"/>
    </div>
    <div class="d-flex justify-content-between">
        <div>
            <form:label path="city" >Org City</form:label>
            <form:input path="city" />
            <form:errors path="city" cssClass="error"/>
        </div>
        <div>
            <form:label path="state" >State</form:label>
            <form:select path="state" id="state">
                <form:option value="Alb">Alb</form:option>
                <form:option value="UK">UK</form:option>
            </form:select>
        </div>
    </div>
    <div>
        <form:label path="password" >Password</form:label>
        <form:password path="password" />
        <form:errors path="password" cssClass="error"/>
    </div>
    <div>
        <form:label path="confirm" >Confirm</form:label>
        <form:password path="confirm" />
        <form:errors path="confirm" cssClass="error"/>
    </div>
    <input type="submit" value="Register" />
</form:form>
</body>
</html>
