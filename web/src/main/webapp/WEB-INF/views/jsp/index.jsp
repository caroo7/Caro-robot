<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="/resources/core/css/reset.css" var="coreCss" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Latest compiled Bootstrap -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Robot library checker</title>
</head>
<body>
<div class="logo">
    <h1>Robot library checker</h1>
</div>
<div class="panel">
    <div id="libraries">
        <ul>
            <li>Library</li>
            <hr>
            <li>Library</li>
            <hr>
            <li>Library</li>
            <hr>
            <li>Library</li>
        </ul>

    </div>
    <div id="books">
        Here we will see the list of books
        <c:if test="${not empty objects}">
             <table>
                <c:forEach var="b" items="${books}">
                <tr>
                    <td>${b.title}</td>
                    <td>${b.authors}</td>
                    <td>${b.description}</td>
                    <td>${b.discount}</td>
                    <td>${b.price}</td>
                    <td>${b.genres}</td>
                </tr>
                </c:forEach>
             </table>
        </c:if>
    </div>
</div>
</body>
</html>