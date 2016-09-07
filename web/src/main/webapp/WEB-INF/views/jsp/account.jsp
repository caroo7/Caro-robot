<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <spring:url value="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrapCss" />

    <spring:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js" var="jQuery"/>
    <spring:url value="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" var="bootstrap"/>
    <link href="${bootstrapCss}" rel="stylesheet" />

    <script src="${jQuery}"></script>
    <script src="${bootstrap}"></script>
    <title>Account information page</title>
</head>
<body>
<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-5">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="/servlet/index" >Main page</a></h1>
	              </div>

	           </div>

	        </div>
	     </div>
	</div>

    <div class="page-content">
    	<div class="row">
		  <div class="col-md-2">
		  	<div class="sidebar content-box" style="display: block;">
                <ul class="nav">
                    <!-- Main menu -->
                    <li class="current"><a href="/servlet/account"><i class="glyphicon glyphicon-home"></i> Dashboard</a></li>

                    <li><a href="#"><i class="glyphicon glyphicon-pencil"></i> Edit account</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-tasks"></i> Preferences</a></li>
                    <li class="submenu">

                </ul>
             </div>
		  </div>
</body>
</html>