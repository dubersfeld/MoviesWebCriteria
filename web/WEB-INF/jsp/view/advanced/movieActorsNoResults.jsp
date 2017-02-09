<spring:message code="title.noResultMovieActors" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:main htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
	<a href="<c:url value="${backPage}"/>"><spring:message code="backPage"/></a>
  </jsp:attribute>
  
  <jsp:attribute name="formContent"><br/>

	<spring:message code="advancedQueries.movieActorsNoResult">
		<spring:argument value="${title}"/>
	</spring:message>
	<fmt:formatDate value="${releaseDate}" pattern="${datePattern}"/>
	
  </jsp:attribute>
  
</template:main>