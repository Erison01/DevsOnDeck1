<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Skills and Short Bio</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: auto;
            margin: 0;
            background-color: #cccccc;

        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #F4D4A5;
            padding: 10px;
            width: 95%;
            top: 0;
        }
        .navbar a {
            text-decoration: none;
            color: white;
            background-color: blue;
            border: 1px solid blue;
            padding: 7px;
            border-radius: 10px;
        }
        .navbar a:hover{
            background-color: #F99820;
            border: 1px solid #F99820;
        }
        .container {
            width: 90%;
            margin-top: 30px;
            padding: 20px;
            background-color: antiquewhite;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: blue;
        }
        label {
            display: block;
            margin-bottom: 10px;


        }
        .bio-text-col{
            color: blue;
        }
        textarea {
            width: 90%;
            height: 150px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }
        .skills-container {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            grid-gap: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 10px;
            width: 90%;
        }
        input[type="submit"] {
            background-color: blue;
            color: white;
            padding: 10px 20px;
            border: 1px solid blue;
            border-radius: 10px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #F99820;
            border: 1px solid #F99820;
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>Devs On Deck</h1>
    <a href="/logout">Logout</a>
</div>

<div class="container">
    <h1>Add Up To 5 Skills</h1>

    <form:form method="post" action="/dev/skills" modelAttribute="loggedInDeveloper">
        <!-- Other form fields and inputs -->
        <div class="skills-container">
            <c:forEach items="${allSkills}" var="skill">
                <label>
                    <form:checkbox path="skills" value="${skill.id}" />
                        ${skill.languages}
                </label>
            </c:forEach>
        </div>
        <br><br>

        <label class="bio-text-col" for="shortBio">Short Bio:</label><br>
        <textarea name="shortBio" id="shortBio">${loggedInDeveloper.shortBio}</textarea><br><br>
        <!-- Other form fields and inputs -->
        <input type="submit" value="Complete Profile" />
    </form:form>
</div>
</body>
</html>
