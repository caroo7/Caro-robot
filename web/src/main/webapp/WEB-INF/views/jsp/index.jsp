<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/core/css/reset.css" var="coreCss" />
    <spring:url value="/resources/core/css/js/scripts.js" var="coreScripts" />
    <spring:url value="http://getbootstrap.com/examples/jumbotron/jumbotron.css" var="jumbotronCss" />
    <spring:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js" var="jQuery"/>
    <spring:url value="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" var="bootstrap"/>
    <spring:url value="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" var="jQueryTables"/>
    <spring:url value="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" var="tableCSS"/>
    <link href="${tableCSS}" rel="stylesheet"/>
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
    <link href="${jumbotronCss}" rel="stylesheet" />
    <script src="${jQuery}"></script>
    <script src="${jQueryTables}"></script>
    <script src="${bootstrap}"></script>
    <script src="${coreScripts}"></script>
    <title>Robot library checker</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="https://github.com/caroo7/Caro-robot">Github Repository</a>
        </div>
      </div>
    </nav>
<div class="container text-center">
 <div class="jumbotron">
      <div class="container">
        <h1>Robot library application</h1>
      </div>
    </div>
<div class="container">
    <div id="libraries" class="list-group">
        <a href="#" class="list-group-item active" onclick="replace('publioBooks','empikBooks')"> Empik</a>
        <a href="#" class="list-group-item active" onclick="replace('empikBooks','publioBooks')">Publio</a>
        <a href="#" class="list-group-item active">Library3</a>
        <a href="#" class="list-group-item active">Library4</a>
        <a href="#" class="list-group-item active">Library5</a>
</div>
    <div id="empikBooks">


           <table class="bookTable" class="display" cellspacing="0" width="100%">
            <thead>
             <tr id="heads">
             <th>Title</th>
             <th>Author</th>
             <th>Price</th>
             <th>Discount</th>
             <th>Description</th>
             <th>Tags</th>
             <th>Genre</th>
             </tr>
             </thead>
             <tbody>

                <c:forEach var="e" items="${empik}" varStatus="myIndex">
                <tr>
                    <td>${e.getTitle()}</td>
                    <td>${e.getAuthors()}</td>
                    <td>${e.getPrice()}</td>
                    <td>${e.getDiscount()}</td>
                    <td>
                    <script style="text/javascript">
                               $(function(){
                                    $('#toggler${myIndex.index}').click(function(e){
                                        e.preventDefault();
                                        $('#empikHiddenText${myIndex.index}').toggle();
                                    });
                               });
                    </script>
                    <a href="#" id="toggler${myIndex.index}">

                            ${e.getDescription().substring(0,40)}...


                    </a>
                    <div style="display: none;" id="empikHiddenText${myIndex.index}">${e.getDescription()}</div></td>
                    <td>${e.getTags()}</td>
                    <td>${e.getGenres().toString()}</td>
                </tr>
                </c:forEach>
                </tbody>


            </table>

    </div>

     <div id="publioBooks" style="display: none;">


               <table class="bookTable" class="display" cellspacing="0" width="100%">
                <thead>
                 <tr id="heads">
                 <th>Title</th>
                 <th>Author</th>
                 <th>Price</th>
                 <th>Discount</th>
                 <th>Description</th>
                 <th>Tags</th>
                 <th>Genre</th>
                 </tr>
                 </thead>
                 <tbody>

                    <c:forEach var="e" items="${publio}" varStatus="myIndex">
                    <tr>
                        <td>${e.getTitle()}</td>
                        <td>${e.getAuthors()}</td>
                        <td>${e.getPrice()}</td>
                        <td>${e.getDiscount()}</td>
                        <td>
                        <script style="text/javascript">
                                   $(function(){
                                        $('#toggler${myIndex.index}').click(function(e){
                                            e.preventDefault();
                                            $('#publioHiddenText${myIndex.index}').toggle();
                                        });
                                   });
                        </script>
                        <a href="#" id="toggler${myIndex.index}">
                        <c:choose>
                            <c:when test="${e.getDescription().isEmpty()}">
                                No description provided...
                                <br />
                            </c:when>
                            <c:otherwise>
                                ${e.getDescription().substring(0,40)}...
                                <br />
                            </c:otherwise>
                        </c:choose>

                        </a>
                        <div style="display: none;" id="publioHiddenText${myIndex.index}">${e.getDescription()}</div></td>
                        <td>${e.getTags()}</td>
                        <td>${e.getGenres().toString()}</td>
                    </tr>
                    </c:forEach>
                    </tbody>


                </table>

        </div>

</div>
   <div class="container text-center" align="center">
     <footer >
            <p>&copy; 2016 Yggdrasil Inc.</p>
     </footer>
     </div>
</div>
</body>
</html>