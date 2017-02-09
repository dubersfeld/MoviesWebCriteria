<spring:message code="title.movieActorsSuccess" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
  </jsp:attribute>
  
  <jsp:attribute name="displayContent"><br/>
	
	<h2><spring:message code="success.movieActors"/></h2>
	<spring:message code="advancedQueries.movieActorsSuccess">
		<spring:argument value="${title}"/>
	</spring:message>
	<fmt:formatDate value="${releaseDate}" pattern="${datePattern}" type="date" />:
	<br/><br/>

	<c:forEach items="${actors}" var="actor">
        <c:out value="${actor.firstName} ${actor.lastName}"/><br/>                                   
    </c:forEach>
	
  </jsp:attribute>
  
</template:main>