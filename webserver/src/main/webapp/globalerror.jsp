<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:import url="/prebase.jsp" />
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a> | 
<a href="${contextPath}/posts/create">Create a post</a>
<a href="${contextPath}/members/${pageContext.request.userPrincipal.name}/posts">Openspections</a>
</h2>
    </c:if>
<h2>Message : ${message}</h2>
<button type="button" onclick="javascript:history.back()">Back</button>
</div>
<c:import url="/postbase.jsp" />
