<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:import url="/prebase.jsp" />

<div class="container-fluid">
	<div class="container">
		<a href="/posts/${singlepost.id}/application" class="float-right btn btn-success" role="button">Apply</a>
		<div class="media">
			<img class="mr-3 rounded-circle" src="/resources/img/back0.jpg" alt="Generic placeholder image" width="48" height="48">
			<div class="media-body">
				<a href="/users/" class="mt-0">${singlepost.title}</a><br>
				<p class="text-muted"><small>${singlepost.datecreated}</small></p>
			</div>
		</div>
		<div>
			<h5>${singlepost.title}</h5>
			<p>${singlepost.description}</p>
		</div>
<div class="media">
<img class="align-self-center mr-1 rounded-circle" src="${singleuser.getPhotourl()}" style="width:32px;height:32px;">
<div class="media-body my-auto">
<p class="m-0"><small><strong>Posted by <a href="/members/${singleuser.getUsername()}">${singleuser.getUsername()}</a></strong></small></p>
<p class="m-0 text-muted"><small>${singlepost.datecreated}
<i class="fa fa-comment ml-3 mr-1"></i>0</small></p>
</div>
</div>
		<div>
			<i class="fa fa-money align-self-center mr-3"></i>
			<p class="my-0 text-muted"><small>Offered compensation</small></p>
			<p class="my-0"><small>$42.123455</small></p>
		</div>
	</div>
</div>
<c:import url="/postbase.jsp" />
