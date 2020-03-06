<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${pageTitle}</title>

    <link href='http://fonts.googleapis.com/css?family=Lilita+One|Candal|Kaushan+Script|Courgette' rel='stylesheet' type='text/css'>
    <link href="https://fonts.googleapis.com/css?family=Alegreya+Sans:800" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type='text/css'>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="height:50px;">
		<div class="container">
				<a class="navbar-brand" href="/" style="font-family: 'Alegreya Sans', sans-serif;font-size:32px">openspection</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
		<li class="nav-item">
			<form class="form-inline navbar-nav" action="/users/search" method="GET">
				<input class="form-control mr-sm-2 " name="keywords" type="text" placeholder="Search for an Openspector" aria-label="Search for an Openspector" value="">
				<input type="submit" style="position: absolute; left: -9999px; width: 1px; height: 1px;" tabindex="-1" />
			</form>
		</li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a></li>
                    </c:when>
                </c:choose>
                        <li class="nav-item"><a class="btn btn-outline-light" href="${pageContext.request.contextPath}/posts/create">POST A JOB</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">HELP</a></li>
                </ul>
				</div>
		</div>
	</nav>
<c:if test="${pageContext.request.userPrincipal.name != null}">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
<div class="container">
<a class="navbar-brand" href="/welcome">My Home</a>
<div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item dropdown">
        <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Profile
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Contact Info</a>
          <a class="dropdown-item" href="#">Bio</a>
          <a class="dropdown-item" href="#">Reviews</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Favorites
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Favorite Demos</a>
          <a class="dropdown-item" href="#">Favorite Lists</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Jobs
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/${pageContext.request.userPrincipal.name}/posts">All Jobs</a>
          <a class="dropdown-item" href="#">Draft</a>
          <a class="dropdown-item" href="#">In Review</a>
          <a class="dropdown-item" href="#">Hiring</a>
          <a class="dropdown-item" href="#">Offering</a>
          <a class="dropdown-item" href="#">Working</a>
          <a class="dropdown-item" href="#">Done</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Payments
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">All Payments</a>
          <a class="dropdown-item" href="#">Held In Escrow</a>
          <a class="dropdown-item" href="#">Completed</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Messages
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Inbox</a>
          <a class="dropdown-item" href="#">Sent</a>
          <a class="dropdown-item" href="#">Deleted</a>
        </div>
      </li>
    </ul>
    <ul class="navbar-nav">
      <li class="nav-item dropdown">
        <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fa fa-user"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Account Settings</a>
          <a class="dropdown-item" href="#">Manage Team</a>
          <a class="dropdown-item" href="#">Billing Settings</a>
          <a class="dropdown-item" href="#">Billing History</a>
          <a class="dropdown-item" href="#">Change Password</a>
          <a class="dropdown-item bg-danger text-white" href="${pageContext.request.contextPath}/logout">Log Out</a>
        </div>
      </li>
    </ul>
  </div>
</div>
</nav>
</c:if>
