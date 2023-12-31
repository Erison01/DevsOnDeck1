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
            width: 97%;
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
            border: 1px solid #F99820;
        }

        .main-container {
            width: 95%;
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }

        .job-container {
            width: 50%;
            padding: 20px;
            border: 1px solid white;
        }

        .job-list {
            list-style: none;
            padding: 0;
        }

        .job-list li {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px;

        }
        .job-list li p{
            font-weight: bold;
        }

        h2{
            color: blue;
        }

        .developer-container {
            width: 50%;
            padding: 20px;
            border: 1px solid white;
        }

        .developer-list {
            list-style: none;
            padding: 0;
        }

        .developer-list li {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            padding: 10px;
        }

        .developer-info {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .developer-name {
            font-size: 18px;
            font-weight: bold;
            margin-right: 10px;
        }

        .developer-last-name {
            font-size: 18px;
            font-weight: bold;
            margin-right: 10px;
        }

        .developer-skills {
            display: flex;
            margin-left: 15px;

        }
        .skill{
            margin-right: 10px;
        }

        .developer-skills li {
            margin-right: 5px;
            list-style: none;
            border: none;
            padding: 0;
        }

        .developer-bio {
            font-size: 14px;
        }
        .delete-btn {
            background-color: #CF553B;
            color: white;
            padding: 5px 10px;
            border: none;
            cursor: pointer;
            border-radius: 10px;

        }
        .delete-btn:hover{
            background-color: #9D0000;
            border: 1px solid #9D0000;
        }
        .new-job{
            display: inline-block;
            padding: 10px 20px;
            font-size: 12px;
            text-align: center;
            text-decoration: none;
            background-color: blue;
            color: white;
            border: 1px solid blue;
            border-radius: 20px;

        }

        .new-job:hover {
            background-color:#F4D4A5 ;
            border: 1px solid #F4D4A5;
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>${organization.orgName}</h1>
    <a href="/logout">Log out</a>
</div>

<div class="main-container">
    <div class="job-container">
        <h2>Add Position</h2>
        <a class="new-job" href="/org/jobs/new">List A New Position</a>
        <h2>Available Job Positions</h2>
        <c:if test="${not empty positions}">
            <ul class="job-list">
                <c:forEach items="${positions}" var="position">
                    <li><p>${position.name}</p>
                        <form:form action="/org/jobs/delete/${position.id}" method="delete" >
                            <button type="submit" class="delete-btn">Delete</button>
                        </form:form>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>

    <div class="developer-container">
        <h2>Available Developers</h2>
        <c:if test="${not empty developers}">
            <ul class="developer-list">
                <c:forEach items="${developers}" var="developer">
                    <li>
                        <div class="developer-info">
                            <div class="developer-name">${developer.firstName}</div>
                            <div class="developer-last-name">${developer.lastName}</div>
                            <div class="developer-skills">
                                <c:forEach items="${developer.skills}" var="skill">
                                    <div class="skill">${skill.languages}</div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="developer-bio">
                             ${developer.shortBio}
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</div>
</body>
</html>
