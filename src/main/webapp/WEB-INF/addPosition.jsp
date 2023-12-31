<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Position</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #cccccc;
            margin: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: auto;
        }
        .navbar {
            background-color: #F4D4A5;
            color: blue;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            margin-bottom: 10px;
            width: 95%;
            top: 0;
        }
        .navbar a {
            color: white;
            background-color: blue;
            text-decoration: none;
            margin: 0 10px;
            border: 1px solid blue;
            border-radius: 10px;
            display: inline-block;
            padding: 10px;
        }

        .navbar a:hover {
            background-color: #F99820;
            border: 1px solid #F99820;
        }
        .container {
            margin: 0 auto;
            width: 90%;
            padding: 20px;
            background-color: floralwhite;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            margin: 0;
            color: blue;
        }
        h2 {
            margin-top: 30px;
            color: blue;
        }

        .lab-check{
            display: block;
            margin-bottom: 10px;
            margin-left: 100px;
        }
        .lab-name{
            display: block;
            margin-bottom: 10px;
            text-align: center;
        }
        input[type="text"],
        textarea {
            width: 90%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }
        .checkbox-container {
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 5px;
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-gap: 10px;
            width: 90%;


        }
        .error {
            color: red;
        }
        input[type="submit"] {
            background-color: blue;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #F4D4A5;
            border: 1px solid #F4D4A5;
        }

    </style>
</head>
<body>
<div class="navbar">
    <h1>Devs On Deck</h1>
    <a href="/org/dashboard">Dashboard</a>
</div>

<div class="container">
    <h1>Add Position</h1>

    <form:form method="post" action="/org/jobs" modelAttribute="position">
        <label class="lab-name" for="name">Position Name:</label>
        <form:input type="text" path="name" required="true" />
        <form:errors path="name" cssClass="error" /><br><br>

        <label class="lab-name" for="description">Position Description:</label>
        <form:textarea path="description" required="true" />
        <form:errors path="description" cssClass="error" /><br><br>

        <h2>Add Skills</h2>
        <div class="checkbox-container">
            <c:forEach items="${allSkills}" var="skill" varStatus="status">
                <label class="lab-check">
                    <form:checkbox path="skill" value="${skill.id}" />
                        ${skill.languages}
                </label>
                <c:if test="${status.index % 2 == 1}"><br></c:if>
            </c:forEach>
        </div>
        <form:errors path="skill" cssClass="error" /><br><br>

        <input type="submit" value="Add Position" />
    </form:form>
</div>

</body>
</html>
