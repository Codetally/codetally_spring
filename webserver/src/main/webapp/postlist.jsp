<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:import url="/prebase.jsp" />
<c:import url="/searchsmall.jsp" />

<div class="container-fluid">
	<div class="container">
<div class="row">
    <div class="col-md-3">
                           <p class="my-4">Filter</p>
                   <hr class="my-4">
    <div class="form-group">
  <label for="sel1">Category</label>
  <select class="form-control" id="sel1">
    <option>Business</option>
    <option>People</option>
    <option>Dogs</option>
    <option>Other items not categorized as such</option>
  </select>
</div>
    <div class="form-check">
  <label class="form-check-label">
    <input type="checkbox" class="form-check-input" value="">Urgent
  </label>
</div>
    <div class="form-check">
  <label class="form-check-label">
    <input type="checkbox" class="form-check-input" value="">Verified
  </label>
</div>

</div>
    <div class="col-md-9">
                   <p class="my-4">Search Results</p>
                   <hr class="my-4">
                   <ul class="list-unstyled">
<c:forEach var="weather" items="${searchresults}">
                        <li class="media">
				<img class="mr-3 rounded-circle" src="/resources/img/back0.jpg" width="48" height="48">
                                <div class="media-body">
				<span class="float-right text-muted">4w</span>
                                <h5 class="mt-0 mb-1"><a href="${contextPath}/posts/${weather["id"]}">${weather["title"]}</a></h5>${weather["description"]}
                                <p>
                                        <a href="#" class="badge badge-primary"><i class="fa fa-tag mr-1"></i>Popular</a>
                                        <a href="#" class="badge badge-primary"><i class="fa fa-tag mr-1"></i>Location-based</a>
                                        <a href="#" class="badge badge-danger"><i class="fa fa-tag mr-1"></i>Urgent</a>
                                </p>
                                </div>
                        </li>
                        <hr class="my-4">
</c:forEach>
                </ul>
        </div>
</div>
</div>
<c:import url="/postbase.jsp" />
