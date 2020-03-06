<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:import url="/prebase.jsp" />
<div class="container">
    <form:form method="POST" modelAttribute="postForm" class="form-signin">
        <h2 class="form-signin-heading">Create your post</h2>
<div class="form-row">
        <spring:bind path="title">
            <div class="form-group col-md-6 ${status.error ? 'has-error' : ''}">
		<label>Title</label>
                <form:input type="text" path="title" class="form-control" placeholder="Title"
                            autofocus="true"></form:input>
                <form:errors path="title"></form:errors>
            </div>
        </spring:bind>
            <div class="form-group col-md-6">
		<label>Description</label>
                <form:textarea path="description" class="form-control" rows="5"
                            autofocus="true"></form:textarea>
            </div>
</div>
<div class="form-row">
<div class="form-group col-md-12">
		<label>Expiry Date</label>
<form:input type="date" path="dateexpired" class="form-control" placeholder="dateexpired"></form:input>
</div>
</div>
<div class="form-row">
<div class="form-group col-md-6">
		<label>Latitude</label>
<form:input type="number" step="any" path="latitude" class="form-control"></form:input>
</div>
<div class="form-group col-md-6">
		<label>Longitude</label>
<form:input type="number" step="any" path="longitude" class="form-control"></form:input>
</div>
</div>
<div class="form-row">
<div class="form-group col-md-12">
		<label>Radius</label>
<form:input type="number" path="radius" class="form-control"></form:input>
</div>
</div>
<div class="form-row">
	<div class="form-group col-md-12">
		<div class="form-check form-check-inline col-md-3">
  <form:checkbox path="requestPhotos" class="form-check-input"></form:checkbox>
  <label class="form-check-label">
  Request Photos
  </label>
		</div>
		<div class="form-check form-check-inline col-md-3">
  <form:checkbox path="requestVideos" class="form-check-input"></form:checkbox>
  <label class="form-check-label">
  Request Videos 
  </label>
		</div>
		<div class="form-check form-check-inline col-md-3">
  <form:checkbox path="requestAudio" class="form-check-input"></form:checkbox>
  <label class="form-check-label">
  Request Audio 
  </label>
		</div>
	</div>
</div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
    </form:form>
</div>
<c:import url="/postbase.jsp" />
