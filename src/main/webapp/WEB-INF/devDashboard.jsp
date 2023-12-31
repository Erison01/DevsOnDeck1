<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
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

        .navbar h1 {
            margin: 0;
            color: blue;
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
            border:1px solid #F99820;
        }


        .main-container {
            width: 90%;
            padding: 20px;
            border: 1px solid white;
            margin-top: 30px;
        }


        .position-container {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }


        .position-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }

        .position-name {
            font-size: 16px;
            font-weight: bold;
        }


        .skill-list {
            list-style: none;
            padding: 0;
            display: flex;
        }

        .skill-list li {
            margin-right: 10px;
        }


        .description {
            font-size: 14px;
        }


        .organization-name {
            font-size: 18px;
            font-weight: bold;
        }
        .apply-btn{
            background-color: blue;
            color: white;
            border: 1px solid blue;
            padding: 7px;
            border-radius: 10px;
            width: 80px;
        }
        .apply-btn:hover{
            background-color: #F4D4A5;
            border: 1px solid#F4D4A5;
        }
        h2{
            color: blue;
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>${loggedInDeveloper.firstName} <span>${loggedInDeveloper.lastName}</span></h1>
    <a href="/logout">Log out</a>
</div>

<div class="main-container">
    <h2>Job Positions Based On Your Skills:</h2>
    <c:if test="${not empty matchingPositions}">
        <c:forEach items="${matchingPositions}" var="position">
            <div class="position-container">
                <div class="organization-name">${position.organization.orgName}</div>
                <div class="position-info">
                    <div class="position-name">${position.name}</div>
                    <ul class="skill-list">
                        <c:forEach items="${position.skill}" var="skill">
                            <li>${skill.languages}</li>
                        </c:forEach>
                    </ul>
                    <form:form action="/apply" method="post">
                        <input type="hidden" name="positionId" value="${position.id}" />
                        <button class="apply-btn" type="submit">Apply</button>
                    </form:form>
                </div>
                <div class="description">${position.description}</div>
            </div>
        </c:forEach>
    </c:if>
</div>
</body>
</html>
