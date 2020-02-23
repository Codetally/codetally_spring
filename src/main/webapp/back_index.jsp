<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:import url="/prebase.jsp" />

<div class="container-fluid" style="background-image:url(${pageContext.request.contextPath}/resources/img/homepage-header-geo-background-opt.jpg);color:white;">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-5" id="headerArea">
				<h1 id="marginBox"><strong>200,000</strong> OPENSPECTORS</h1>
				<p>Access the largest online marketplace of inspetion talent.<br>Sign up free and find your ideal inspector!</p>
				<a class="standard-btn standard-white-btn" href="/registration">Sign Up Free!</a>
			</div>
			<div class="col-xs-12 col-sm-7" id="brand-microphone-man"><img alt="Guy at microphone" width="653" src="${pageContext.request.contextPath}/resources/img/homepage-header-guyatmic-desktop.png"></div>
		</div>
	</div>
</div>
<c:import url="/postbase.jsp" />
