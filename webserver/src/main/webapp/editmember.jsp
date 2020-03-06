<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:import url="/prebase.jsp" />
<div class="container">
 <form:form method="POST" modelAttribute="user" class="form-signin" enctype="multipart/form-data">
        <h2 class="form-signin-heading">Edit your member profile</h2>
	<div class="form-row">
        <spring:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
		<label>Description</label>
                <form:textarea path="description" class="form-control" rows="5" 
                            autofocus="true"></form:textarea>
                <form:errors path="description"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="photourl">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
                <label>Photo</label>
		<form:hidden path="photourl" />
<input type="file" name="photofile" class="form-control"/>
<div class="form-check">
  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" name="removephoto">
  <label class="form-check-label" for="defaultCheck1">
   Remove photo 
  </label>
</div>
                <form:errors path="photourl"></form:errors>
            </div>
        </spring:bind>
	</div>

        <div class="form-row">
        <spring:bind path="websiteurl">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
                <label>Website</label>
                <form:input type="text" path="websiteurl" class="form-control"
                            autofocus="true"></form:input>
                <form:errors path="websiteurl"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="blogurl">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
                <label>Blog</label>
                <form:input type="text" path="blogurl" class="form-control" 
                            autofocus="true"></form:input>
                <form:errors path="blogurl"></form:errors>
            </div>
        </spring:bind>
        </div>

        <div class="form-row">
        <spring:bind path="linkedinurl">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
                <label>Linkedin</label>
                <form:input type="text" path="linkedinurl" class="form-control"
                            autofocus="true"></form:input>
                <form:errors path="linkedinurl"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="twitterurl">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
                <label>Twitter</label>
                <form:input type="text" path="twitterurl" class="form-control" 
                            autofocus="true"></form:input>
                <form:errors path="twitterurl"></form:errors>
            </div>
        </spring:bind>
        </div>

        <div class="form-row">
        <spring:bind path="githuburl">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
                <label>Github</label>
                <form:input type="text" path="githuburl" class="form-control"
                            autofocus="true"></form:input>
                <form:errors path="githuburl"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="facebookurl">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
                <label>Facebook</label>
                <form:input type="text" path="facebookurl" class="form-control"
                            autofocus="true"></form:input>
                <form:errors path="facebookurl"></form:errors>
            </div>
        </spring:bind>
        </div>

     <div class="form-row">
        <spring:bind path="bannerurl">
            <div class="form-group ${status.error ? 'has-error' : ''} col-md-6">
                <label>Banner</label>
		<form:hidden path="bannerurl" />
<input type="file" name="bannerfile" class="form-control"/>
<div class="form-check">
  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1" name="removebanner">
  <label class="form-check-label" for="defaultCheck1">
   Remove banner 
  </label>
</div>
                <form:errors path="bannerurl"></form:errors>
            </div>
        </spring:bind>
     </div>

	<button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
</form:form>
</div>
<c:import url="/postbase.jsp" />
